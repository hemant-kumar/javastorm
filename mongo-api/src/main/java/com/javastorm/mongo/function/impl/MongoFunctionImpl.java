package com.javastorm.mongo.function.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;

import com.javastorm.common.exception.EmptyPropertyException;
import com.javastorm.common.exception.MissingPropertyException;
import com.javastorm.common.exception.MultipleFileFoundException;
import com.javastorm.mongo.domain.MongoEntity;
import com.javastorm.mongo.domain.MongoQueryInfo;
import com.javastorm.mongo.exception.MongoDeleteException;
import com.javastorm.mongo.exception.MongoFileNotFoundException;
import com.javastorm.mongo.exception.MongoIdMissingException;
import com.javastorm.mongo.exception.MongoUpdateException;
import com.javastorm.mongo.factory.impl.SimpleMongoFactory;
import com.javastorm.mongo.function.spi.MongoFunction;
import com.javastorm.mongo.info.MongoProperties;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoException;
import com.mongodb.ReadPreference;
import com.mongodb.util.JSON;

/**
 * This MongoFunctionImpl class is responsible for all the database layer interaction
 * with MongoDB. It provides functions for performing CURD operations on MongoDB.
 * 
 * @author Hemant Kumar
 * @version 1.0 Dated: 13/01/2013
 */
public class MongoFunctionImpl implements MongoFunction 
{

  /**
   * It holds the MongoDB instance
   */
  private DB db_;
  
  /**
   * It holds all properties which are configured in mongo.properties and mongoEntity-collection.mappings file
   */
  private MongoProperties mongoProps_;

  /**
   * It holds the fields which are to be ignored while parsing an object to json string
   */
  private ArrayList<String> ignoreList_ = new ArrayList<String>();

  public MongoFunctionImpl() throws MultipleFileFoundException,MongoFileNotFoundException,IOException,
  								MissingPropertyException,MongoException, EmptyPropertyException {
	mongoProps_ = MongoProperties.getInstance();
	db_ = SimpleMongoFactory.getInstance().getDB();
	if(!ignoreList_.contains("queryInfo"))
	  ignoreList_.add("queryInfo");
  }
  
  @Override
  public MongoEntity save(MongoEntity mongoEntity) throws MongoIdMissingException {
	if(mongoEntity.get_id() == null)
	  throw new MongoIdMissingException("_id not set for " + mongoEntity.getClass().getName());
	String jsonStr_ = JSONObject.fromObject(mongoEntity).toString();
	DBObject dbObject_ = removeIgnoredFields((DBObject) JSON.parse(jsonStr_));
	getSuitableCollection(mongoEntity.getClass()).insert(dbObject_);
	return mongoEntity;
  }

  @SuppressWarnings("all")
  @Override
  public Collection find(MongoEntity mongoEntity) {
	List resultList_ = new ArrayList();
	DBObject selectFields_ = new BasicDBObject();
	MongoQueryInfo queryInfo_ = mongoEntity.getQueryInfo();
	if(queryInfo_ != null) {
	  List<String> fields_ = queryInfo_.getSelectFields();
	  if(fields_ != null && fields_.size() > 0)
	    for(String field_ : fields_)
		  selectFields_.put(field_,1);
	}
	DBCursor dbCursor_ = null;
	DBObject findObj_ = parseQueryInfo(queryInfo_);
	if(findObj_ != null) {
	  dbCursor_ = getSuitableCollection(mongoEntity.getClass()).find(findObj_,
			  					  selectFields_).limit(queryInfo_.getMaxNoOfRecords());
	  if(dbCursor_ != null) {
	    if(queryInfo_.isFindAsJson()) {
		  for(DBObject record_ : dbCursor_)
 		    resultList_.add(record_.toString());
	    } 
	    else {
		  for(DBObject record_ : dbCursor_)
		    resultList_.add(JSONObject.toBean(JSONObject.fromObject(record_.toString()),mongoEntity.getClass()));
	    }
	  }
	}
	return resultList_;
  }

  @Override
  public void delete(MongoEntity mongoEntity) throws MongoDeleteException {
    try {
    	DBObject deleteObj_ = parseQueryInfo(mongoEntity.getQueryInfo());
    	if(deleteObj_ == null) {
    	  if(StringUtils.isEmpty(mongoEntity.get_id()))
    	    throw new MongoDeleteException("No information provided for delete");	
    	  deleteObj_ = new BasicDBObject("_id",mongoEntity.get_id());
    	}
      getSuitableCollection(mongoEntity.getClass()).remove(deleteObj_);
    }
    catch(Exception e) {
	  throw new MongoDeleteException(e.getMessage());
	}
  }

  @Override
  public int update(MongoEntity findmongoEntity, MongoEntity updatemongoEntity) throws MongoUpdateException {
	if(!mongoProps_.getColletion(findmongoEntity.getClass().getName()).equals(
		mongoProps_.getColletion(updatemongoEntity.getClass().getName())))
	  throw new MongoUpdateException("Different collection for find and update mongoEntity");
	try {
 	  getSuitableCollection(findmongoEntity.getClass()).update(parseQueryInfo(findmongoEntity.getQueryInfo()),
						  parseQueryInfo(updatemongoEntity.getQueryInfo()));
	}
	catch(Exception e) {
	  throw new MongoUpdateException(e.getMessage());
	}
	return 1;
  }

  /**
   * This function parses the mongo query string and map into DBObject
   * @param queryInfo
   * @return DBObject
   */
  private DBObject parseQueryInfo(MongoQueryInfo queryInfo) {
 	DBObject parsedObj_ = null;
 	if(queryInfo != null) {
 	  String queryString_ = queryInfo.getQueryString(); 
  	  if(queryString_ != null )
  	    parsedObj_ = (DBObject) JSON.parse(queryString_);
	  Map<Object,Object> queryMap_ = queryInfo.getQueryMap();
  	  if(queryMap_ != null && queryMap_.size() > 0) {
  	    if(parsedObj_ == null)
  	      parsedObj_ = new BasicDBObject(queryMap_);
  	    else
  		  parsedObj_.putAll(queryMap_);
      }
 	}
  	return parsedObj_;
  }

  /**
   * This function is used for adding a field which is to be ignored while parsing
   * Note: Only 1 level skipping supported
   * @param field
   */
  public void addIgnoreField(String field) {
	if(!ignoreList_.contains(field))
	  ignoreList_.add(field);
  }

  /**
   * This function is used for removing an already added field from ignored List
   * Note: Removal of queryInfo field from list is not supported 
   * @param field
   */
  public void removeIgnoreField(String field) {
    if(!field.equals("queryInfo") && ignoreList_.contains(field))
	  ignoreList_.remove(field);
  }
  
  /**
   * This function removes all the ignored fields from input object and then return back the object
   * @param obj
   * @return DBObject
   */
  private DBObject removeIgnoredFields(DBObject obj) {
	for(String field : ignoreList_)
	  obj.removeField(field);
    return obj;
  }

  /**
   * This function is responsible for return in suitable collection against class provided
   * @param clazz
   * @return DBCollection
   */
  private DBCollection getSuitableCollection(Class<? extends MongoEntity> clazz) {
    DBCollection dbColl_ = db_.getCollection(mongoProps_.getColletion(clazz.getName()));
    dbColl_.setReadPreference(ReadPreference.secondaryPreferred());
    return dbColl_;
  }
}