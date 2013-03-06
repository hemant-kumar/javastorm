package com.javastorm.mongoapi.mongo.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import com.javastorm.mongoapi.common.exception.MultipleFileFoundException;
import com.javastorm.mongoapi.common.file.FileFinder;
import com.javastorm.mongoapi.mongo.exception.MongoFileNotFoundException;

/**
 * This MongoConfiguration class holds the mongo.properties & pojo-collection.mappings file.
 * Using this configuration files can be loaded from user-defined locations. 
 * 
 * @author Hemant Kumar
 * @version 1.0 Dated: 13/01/2013
 */
public class MongoConfiguration {

  private InputStream mongoPropertiesStream_;
  private InputStream pojoCollectionMappingsStream_;
  
  /**
   * Picks up files from FILE_REPO or class path
   * @throws MongoFileNotFoundException
   * @throws MultipleFileFoundException
   * @throws FileNotFoundException
   */
  public MongoConfiguration() throws MongoFileNotFoundException, MultipleFileFoundException, FileNotFoundException {
      File[] fileList_ = FileFinder.findFiles("mongo.properties");
    if(fileList_.length == 0)
      throw new MongoFileNotFoundException("mongo.properties file not found");
    if(fileList_.length>1)
      throw new MultipleFileFoundException("multiple mongo.properties file found");
    mongoPropertiesStream_ = new FileInputStream(fileList_[0]);
      fileList_ = FileFinder.findFiles("pojo-collection.mappings");
    if(fileList_.length>1)
      throw new MultipleFileFoundException("pojo-collection.mappings");
    if(fileList_.length != 0)
      pojoCollectionMappingsStream_ = new FileInputStream(fileList_[0]);
  }

  /**
   * Picks up files from streams provided if not null else from FILE_REPO or class path
   * @param mongoPropertiesStream
   * @param pojoCollectionMappingsStream
   * @throws MongoFileNotFoundException
   * @throws MultipleFileFoundException
   * @throws FileNotFoundException
   */
  public MongoConfiguration(InputStream mongoPropertiesStream, InputStream pojoCollectionMappingsStream) 
                throws MongoFileNotFoundException, MultipleFileFoundException, FileNotFoundException {
    if(mongoPropertiesStream != null) {
    	mongoPropertiesStream_ = mongoPropertiesStream;
    }
    else {
      File[] fileList_ = FileFinder.findFiles("mongo.properties");
      if(fileList_.length == 0)
        throw new MongoFileNotFoundException("mongo.properties file not found");
      if(fileList_.length>1)
        throw new MultipleFileFoundException("multiple mongo.properties file found");
      mongoPropertiesStream_ = new FileInputStream(fileList_[0]);
    }
    if(pojoCollectionMappingsStream != null) {
    	pojoCollectionMappingsStream_ = pojoCollectionMappingsStream;
    }
    else {
      File[] fileList_ = FileFinder.findFiles("pojo-collection.mappings");
      if(fileList_.length>1)
        throw new MultipleFileFoundException("pojo-collection.mappings");
      if(fileList_.length != 0)
    	  pojoCollectionMappingsStream_ = new FileInputStream(fileList_[0]);
    }
  }

  public InputStream getMongoPropertiesStream() {
    return mongoPropertiesStream_;
  }

  public void setMongoPropertiesStream(InputStream mongoPropertiesStream) {
    this.mongoPropertiesStream_ = mongoPropertiesStream;
  }

  public InputStream getPojoCollectionMappingsStream() {
    return pojoCollectionMappingsStream_;
  }

  public void setPojoCollectionMappingsStream(InputStream pojoCollectionMappingsStream) {
    this.pojoCollectionMappingsStream_ = pojoCollectionMappingsStream;
  }
}
