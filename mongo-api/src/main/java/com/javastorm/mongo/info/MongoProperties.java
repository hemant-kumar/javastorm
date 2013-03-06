package com.javastorm.mongo.info;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.javastorm.common.exception.EmptyPropertyException;
import com.javastorm.common.exception.MissingPropertyException;
import com.javastorm.common.exception.MultipleFileFoundException;
import com.javastorm.common.file.FileFinder;
import com.javastorm.common.property.PropertyLoader;
import com.javastorm.mongo.config.MongoConfiguration;
import com.javastorm.mongo.exception.MongoFileNotFoundException;
import com.mongodb.ServerAddress;

/**
 * The MongoProperties class holds the properties defined in mongo.properties and pojo-collection.mappings file
 * 
 * @author Hemant Kumar
 * @version 1.0 Dated: 13/01/2013 
 */
public class MongoProperties {

  /**
   * This fields holds the list of available routers
   */
  private List<ServerAddress> mongoList_;

  /**
   * This fields holds the database name
   */
  private String dbName_;

  /**
   * This fields holds the default collection name
   */
  private String defaultCollection_;
  
  /**
   * This field is used for maintaining the singleton nature of this class
   */
  private static MongoProperties mongoProperties_;
  
  /**
   * This map holds the mapping of pojo to its collection
   */
  private Map<String,String> pojoToColletionMap_ = new HashMap<String, String>();

  /**
   * Private Constructor
   * @throws MongoFileNotFoundException
   * @throws IOException
   * @throws EmptyPropertyException
   * @throws MultipleFileFoundException
   * @throws MissingPropertyException
   */
  private MongoProperties() throws MongoFileNotFoundException,IOException,EmptyPropertyException, 
  					  			   MultipleFileFoundException,MissingPropertyException {
    File[] fileList_ = FileFinder.findFiles("mongo.properties");
	if(fileList_.length == 0)
	  throw new MongoFileNotFoundException("mongo.properties file not found");
	if(fileList_.length>1)
	  throw new MultipleFileFoundException("multiple mongo.properties file found");
	FileInputStream mongoStream_ = new FileInputStream(fileList_[0]);
	PropertyLoader.loadAndStore(mongoStream_);
	String routers_ = PropertyLoader.getProperty("routers");
	String[] list_ = routers_.split(",");
	mongoList_ = new ArrayList<ServerAddress>();
	for(String addr_ : list_)
	  mongoList_.add(new ServerAddress(addr_));
	dbName_ = PropertyLoader.getProperty("db");
	defaultCollection_ = PropertyLoader.getProperty("defaultCollection");
    fileList_ = FileFinder.findFiles("pojo-collection.mappings");
	if(fileList_.length>1)
	  throw new MultipleFileFoundException("pojo-collection.mappings");
	if(fileList_.length != 0) {
	  FileInputStream stream_ = new FileInputStream(fileList_[0]);
	  pojoToColletionMap_ = PropertyLoader.loadAndReturn(stream_);
	}
  }

  /**
   * Private Constructor
   * @param config
   * @throws MongoFileNotFoundException
   * @throws IOException
   * @throws EmptyPropertyException
   * @throws MultipleFileFoundException
   * @throws MissingPropertyException
   */
  private MongoProperties(MongoConfiguration config) throws MongoFileNotFoundException,IOException,
  						  EmptyPropertyException,MultipleFileFoundException,MissingPropertyException {
	PropertyLoader.loadAndStore(config.getMongoPropertiesStream());
	String routers_ = PropertyLoader.getProperty("routers");
	String[] list_ = routers_.split(",");
	mongoList_ = new ArrayList<ServerAddress>();
	for(String addr_ : list_)
	  mongoList_.add(new ServerAddress(addr_));
	dbName_ = PropertyLoader.getProperty("db");
	defaultCollection_ = PropertyLoader.getProperty("defaultCollection");
    pojoToColletionMap_ = PropertyLoader.loadAndReturn(config.getPojoCollectionMappingsStream());
  }
  
  /**
   * This method is to be called for getting the instance of MongoProperties
   * @return MongoProperties
   * @throws MongoFileNotFoundException
   * @throws IOException
   * @throws MultipleFileFoundException
   * @throws MissingPropertyException
   * @throws EmptyPropertyException
   */
  public static synchronized MongoProperties getInstance() throws MongoFileNotFoundException,IOException,
  							MultipleFileFoundException,	MissingPropertyException, EmptyPropertyException {
	if(mongoProperties_ == null)
	  mongoProperties_ = new MongoProperties();
	return mongoProperties_;
  }

  /**
   * This method is to be called for getting the instance of MongoProperties against provided Configuration
   * @param config
   * @return MongoProperties
   * @throws MongoFileNotFoundException
   * @throws IOException
   * @throws MultipleFileFoundException
   * @throws MissingPropertyException
   * @throws EmptyPropertyException
   */
  public static synchronized MongoProperties getInstance(MongoConfiguration config) throws MongoFileNotFoundException,
  				IOException,MultipleFileFoundException,	MissingPropertyException, EmptyPropertyException {
	if(mongoProperties_ == null)
	  mongoProperties_ = new MongoProperties(config);
	return mongoProperties_;
  }

  /**
   * This function returns a list of routers
   * @return List<ServerAddress>
   */
  public List<ServerAddress> getMongoList() {
    return mongoList_;
  }

  /**
   * This function returns the database name
   * @return String
   */
  public String getDbName() {
	return dbName_;
  }

  /**
   * This function returns the appropriate collection for the requested class name 
   * @param key
   * @return String
   */
  public String getColletion(String key) {
	String coll_ = pojoToColletionMap_.get(key);
	return coll_ != null ? coll_ : defaultCollection_;
  }
}