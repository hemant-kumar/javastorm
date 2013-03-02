package com.javastorm.mongo.domain;

/**
 * The MongoEntity class is required to be extended by all 
 * pojo classes that need to interact with MongoDB
 * 
 * @author Hemant Kumar
 * @version 1.0 Dated: 13/01/2013 
 */
public class MongoEntity {
	
  /**
   * It is the mandatory field for any MongoDB record and this field 
   * needs to be set before calling the save() else exception will be thrown  
   */
  private String _id;

  /**
   * This field contains all the info related to any mongo query.
   * This field is added in the ignored field list hence it is not saved in db.  
   */
  private MongoQueryInfo queryInfo;

  public MongoEntity() {
  }
  
  public MongoEntity(String id) {
	_id = id;
  }
  
  public String get_id() {
	return _id;
  }
  
  public void set_id(String id) {
    _id = id;
  }

  public MongoQueryInfo getQueryInfo() {
	return queryInfo;
  }

  public void setQueryInfo(MongoQueryInfo queryInfo) {
	this.queryInfo = queryInfo;
  }
}