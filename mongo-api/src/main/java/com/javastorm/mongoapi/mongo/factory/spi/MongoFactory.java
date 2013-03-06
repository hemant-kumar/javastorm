package com.javastorm.mongoapi.mongo.factory.spi;

import com.mongodb.DB;
import com.mongodb.Mongo;

/**
 * This MongoFactory interface is responsible for providing Mongo 
 * instance, DB instance as requested.
 * 
 * @author Hemant Kumar
 * @version 1.0 Dated: 13/01/2013
 */
public interface MongoFactory
{
  
  /**
   * This function is responsible for returning the instance of Mongo
   * @return Mongo
   */
  public Mongo getMongo();

  /**
   * This Function is responsible for returning the instance of DB
   */
  public DB getDB();
}