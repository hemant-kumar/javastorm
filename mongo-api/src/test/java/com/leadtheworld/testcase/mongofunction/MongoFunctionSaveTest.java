package com.leadtheworld.testcase.mongofunction;


import java.io.IOException;
import java.util.ArrayList;

import junit.framework.TestCase;

import com.leadtheworld.common.exception.EmptyPropertyException;
import com.leadtheworld.common.exception.MissingPropertyException;
import com.leadtheworld.common.exception.MultipleFileFoundException;
import com.leadtheworld.helper.TestEntity;
import com.leadtheworld.mongo.exception.MongoFileNotFoundException;
import com.leadtheworld.mongo.exception.MongoIdMissingException;
import com.leadtheworld.mongo.function.impl.MongoFunctionImpl;
import com.leadtheworld.mongo.function.spi.MongoFunction;
import com.mongodb.MongoException;

/**
 * This MongoFunctionSaveTest class is for testing of save scenario of MongoFunction
 * 
 * @author hemant singh
 * @version 1.0 Dated: 13/01/2013
 */
public class MongoFunctionSaveTest extends TestCase {

  /**
   * This function tests for the happy scenario	
   * @throws MongoException
   * @throws MultipleFileFoundException
   * @throws IOException
   * @throws MongoFileNotFoundException
   * @throws MissingPropertyException
   * @throws EmptyPropertyException
   * @throws MongoIdMissingException
   */
  public void testHappyScenario() throws MongoException,MultipleFileFoundException,IOException,
  MongoFileNotFoundException,MissingPropertyException,EmptyPropertyException,MongoIdMissingException {
    MongoFunction mongoFunction_ = new MongoFunctionImpl();
    TestEntity testEntity_ = new TestEntity("12345");
    testEntity_.setInfo("Testing for save");
    ArrayList<String> myList_ = new ArrayList<String>();
    myList_.add("one");
    myList_.add("two");
    myList_.add("three");
    myList_.add("four");
    testEntity_.setMyList(myList_);
    mongoFunction_.save(testEntity_);
  }
  
  /**
   * This function tests for the MongoIdMissingException scenario
   * @throws MongoException
   * @throws MultipleFileFoundException
   * @throws IOException
   * @throws MongoFileNotFoundException
   * @throws MissingPropertyException
   * @throws EmptyPropertyException
   */
  public void testMongoIdMissingExceptionScenario() throws MongoException,MultipleFileFoundException,
  IOException,MongoFileNotFoundException,MissingPropertyException,EmptyPropertyException  {
    MongoFunction mongoFunction_ = new MongoFunctionImpl();
    TestEntity testEntity_ = new TestEntity();
    testEntity_.setInfo("Testing for save");
    ArrayList<String> myList_ = new ArrayList<String>();
    myList_.add("one");
    myList_.add("two");
    myList_.add("three");
    myList_.add("four");
    testEntity_.setMyList(myList_);
    try {
	  mongoFunction_.save(testEntity_);
	  assertEquals(true,false);
	}
    catch(MongoIdMissingException e) {
  	  assertEquals(true,true);
	}
  }
}