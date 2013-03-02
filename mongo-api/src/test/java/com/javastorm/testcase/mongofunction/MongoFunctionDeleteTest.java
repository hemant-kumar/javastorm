package com.javastorm.testcase.mongofunction;

import java.io.IOException;
import java.util.List;

import junit.framework.TestCase;

import com.javastorm.common.exception.EmptyPropertyException;
import com.javastorm.common.exception.MissingPropertyException;
import com.javastorm.common.exception.MultipleFileFoundException;
import com.javastorm.helper.TestEntity;
import com.javastorm.mongo.domain.MongoQueryInfo;
import com.javastorm.mongo.exception.MongoDeleteException;
import com.javastorm.mongo.exception.MongoFileNotFoundException;
import com.javastorm.mongo.function.impl.MongoFunctionImpl;
import com.javastorm.mongo.function.spi.MongoFunction;
import com.mongodb.MongoException;

/**
 * This MongoFunctionDeleteTest class is for the testing of deletion scenario of MongoFunction
 * 
 * @author Hemant Kumar
 * @version 1.0 Dated: 13/01/2013
 */
public class MongoFunctionDeleteTest extends TestCase {

  /**
   * This function is for testing the happy scenario	
   * @throws MongoException
   * @throws MultipleFileFoundException
   * @throws MongoFileNotFoundException
   * @throws IOException
   * @throws MissingPropertyException
   * @throws EmptyPropertyException
   * @throws MongoDeleteException
   */
  public void testHappyScenario() throws MongoException,MultipleFileFoundException,
  					MongoFileNotFoundException,IOException,MissingPropertyException,
  					EmptyPropertyException, MongoDeleteException {
    MongoFunction mongoFunction_ = new MongoFunctionImpl();
    MongoQueryInfo queryInfo_ = new MongoQueryInfo();
	queryInfo_.setQueryString("{ \"_id\" : \"1234\"}");
    TestEntity testEntity_ = new TestEntity();
	testEntity_.setQueryInfo(queryInfo_);
	mongoFunction_.delete(testEntity_);
	@SuppressWarnings("rawtypes")
	List findlist_ = (List)mongoFunction_.find(testEntity_);
	if(findlist_.size()!=0)
	  assertEquals(true,false);
  }

  /**
   * This function is for testing the MongoDeleteException scenario
   * @throws MongoException
   * @throws MultipleFileFoundException
   * @throws MongoFileNotFoundException
   * @throws IOException
   * @throws MissingPropertyException
   * @throws EmptyPropertyException
   */
  public void testMongoDeleteExceptionScenario() throws MongoException,MultipleFileFoundException,
  		MongoFileNotFoundException,IOException,MissingPropertyException,EmptyPropertyException  {
    MongoFunction mongoFunction_ = new MongoFunctionImpl();
    MongoQueryInfo queryInfo_ = new MongoQueryInfo();
    queryInfo_.setQueryString("{ \"_id\" : \"1234\"}");
    TestEntity testEntity_ = new TestEntity();
    testEntity_.setQueryInfo(queryInfo_);
    try {
	  mongoFunction_.delete(testEntity_);
	  assertEquals(true,true);
    }
    catch (MongoDeleteException e) {
	  assertEquals(true,false);
    }
  }
}