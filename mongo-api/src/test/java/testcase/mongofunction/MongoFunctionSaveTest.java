package testcase.mongofunction;

import helper.TestEntity;

import java.io.IOException;
import java.util.ArrayList;

import junit.framework.TestCase;
import mongo.exception.MongoFileNotFoundException;
import mongo.exception.MongoIdMissingException;
import mongo.function.impl.MongoFunctionImpl;
import mongo.function.spi.MongoFunction;

import com.mongodb.MongoException;
import common.exception.EmptyPropertyException;
import common.exception.MissingPropertyException;
import common.exception.MultipleFileFoundException;

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