package com.javastorm.mongoapi.mongo.factory.impl;

import java.io.IOException;

import com.javastorm.mongoapi.common.exception.EmptyPropertyException;
import com.javastorm.mongoapi.common.exception.MissingPropertyException;
import com.javastorm.mongoapi.common.exception.MultipleFileFoundException;
import com.javastorm.mongoapi.mongo.exception.MongoFileNotFoundException;
import com.javastorm.mongoapi.mongo.factory.spi.MongoFactory;
import com.javastorm.mongoapi.mongo.info.MongoProperties;
import com.mongodb.DB;
import com.mongodb.Mongo;
import com.mongodb.MongoException;
import com.mongodb.ReadPreference;

/**
 * This SimpleMongoFactory interface is responsible for providing 
 * Mongo instance, DB instance as requested.
 * 
 * @author Hemant Kumar
 * @version 1.0 Dated: 13/01/2013
 */
public class SimpleMongoFactory implements MongoFactory
{
  /**
   * It holds the mongo instance as configured in mongo.properties file
   */
  private static Mongo mongo_;

  /**
   * It holds the db instance as configured in mongo.properties file
   */
  private static DB db_;
  
  /**
   * This field is used for maintaining the singleton nature of this class
   */
  private static SimpleMongoFactory mongoFactory_;

  /**
   * Private Constructor
   * @throws MultipleFileFoundException
   * @throws MongoFileNotFoundException
   * @throws IOException
   * @throws MissingPropertyException
   * @throws MongoException
   * @throws EmptyPropertyException
   */
  private SimpleMongoFactory() throws MultipleFileFoundException,MongoFileNotFoundException,IOException,
  								MissingPropertyException,MongoException, EmptyPropertyException {
	MongoProperties mongoProps = MongoProperties.getInstance();
  	if(mongo_ == null) 
  	  mongo_ = new Mongo(mongoProps.getMongoList());
  	mongo_.setReadPreference(ReadPreference.secondaryPreferred());
  	db_ = mongo_.getDB(mongoProps.getDbName());
  	db_.getCollectionNames(); // Only for testing the connection 
  }

  /**
   * This method returns the instance of SimpleMongoFactory
   * @return SimpleMongoFactory
   * @throws MultipleFileFoundException
   * @throws IOException
   * @throws MongoFileNotFoundException
   * @throws MissingPropertyException
   * @throws MongoException
   * @throws EmptyPropertyException
   */
  public static synchronized SimpleMongoFactory getInstance() throws MultipleFileFoundException,IOException,
            MongoFileNotFoundException,MissingPropertyException,MongoException, EmptyPropertyException {
    if(mongoFactory_ == null)
    	mongoFactory_ = new SimpleMongoFactory();
    return mongoFactory_;
  }

  @Override
  public Mongo getMongo() {
  	return mongo_;
  }

  @Override
  public DB getDB() {
  	return db_;
  }
}