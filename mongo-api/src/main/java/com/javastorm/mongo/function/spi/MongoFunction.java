package com.leadtheworld.mongo.function.spi;

import java.util.Collection;

import com.leadtheworld.mongo.domain.MongoEntity;
import com.leadtheworld.mongo.exception.MongoDeleteException;
import com.leadtheworld.mongo.exception.MongoIdMissingException;
import com.leadtheworld.mongo.exception.MongoUpdateException;


/**
 * This MongoFunction interface is responsible for all the database layer interaction
 * with MongoDB. It provides functions for performing CURD operations on MongoDB.
 * 
 * @author hemant singh
 * @version 1.0 Dated: 13/01/2013
 */
public interface MongoFunction {

  /**
   * This function is used to save a document in a collection 	
   * @param mongoEntity
   * @return MongoEntity
   * @throws MongoIdMissingException
   */
  public MongoEntity save(MongoEntity mongoEntity) throws MongoIdMissingException;

  /**
   * This function is responsible for finding the documents from a collection
   * @param mongoEntity
   * @return Collection<Object>
   */
  @SuppressWarnings("rawtypes")
  public Collection find(MongoEntity mongoEntity);

  /**
   * This function is responsible for deleting any document from a collection  
   * @param mongoEntity
   * @throws MongoDeleteException
   */
  public void delete(MongoEntity mongoEntity) throws MongoDeleteException;

  /**
   * This function is responsible for updating any document in a collection 
   * @param findmongoEntity
   * @param updatemongoEntity
   * @return int
   * @throws MongoUpdateException
   */
  public int update(MongoEntity findMongoEntity, MongoEntity updateMongoEntity) throws MongoUpdateException;
}