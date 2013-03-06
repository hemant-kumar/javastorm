package com.javastorm.testcase.mongofunction;

import java.io.IOException;

import junit.framework.TestCase;

import com.javastorm.mongoapi.common.exception.EmptyPropertyException;
import com.javastorm.mongoapi.common.exception.MissingPropertyException;
import com.javastorm.mongoapi.common.exception.MultipleFileFoundException;
import com.javastorm.mongoapi.mongo.exception.MongoFileNotFoundException;
import com.javastorm.mongoapi.mongo.function.impl.MongoFunctionImpl;
import com.mongodb.MongoException;

/**
 * This MongoFunctionConstructorTest class is for testing negative scenarios.
 * All the cases will run to success only in happy scenario. Any change like
 * mongo file at multiple location or missing property will lead to failure
 *  
 * @author Hemant Kumar
 * @version 1.0 Dated: 13/01/2013
 */
public class MongoFunctionConstructorTest extends TestCase {
  
  /**
   * This function tests for MultipleFileFoundException scenario 
   * @throws MongoException
   * @throws MongoFileNotFoundException
   * @throws IOException
   * @throws MissingPropertyException
   * @throws EmptyPropertyException
   */
  public void testMultipleFileFoundExceptionScenario() throws MongoException,MongoFileNotFoundException,
  IOException,MissingPropertyException,EmptyPropertyException {
	try {
	  new MongoFunctionImpl();
	  assertEquals(true,true);
	}
	catch(MultipleFileFoundException e) {
	  assertEquals(true,false);
	}
  }

  /**
   * This function tests for MongoException scenario
   * @throws MultipleFileFoundException
   * @throws MongoFileNotFoundException
   * @throws IOException
   * @throws MissingPropertyException
   * @throws EmptyPropertyException
   */
  public void testMongoExceptionScenario() throws MultipleFileFoundException,MongoFileNotFoundException,
  IOException,MissingPropertyException,EmptyPropertyException {
	try {
	  new MongoFunctionImpl();
	  assertEquals(true,true);
	}
	catch(MongoException e) {
      assertEquals(true,false);
	}
  }

  /**
   * This function tests for MongoFileNotFoundException scenario
   * @throws MongoException
   * @throws MultipleFileFoundException
   * @throws IOException
   * @throws MissingPropertyException
   * @throws EmptyPropertyException
   */
  public void testMongoFileNotFoundExceptionScenario() throws MongoException,MultipleFileFoundException,
  IOException,MissingPropertyException,EmptyPropertyException {
	try {
	  new MongoFunctionImpl();
	  assertEquals(true,true);
	}
	catch(MongoFileNotFoundException e) {
	  assertEquals(true,false);
	}
  }

  /**
   * This function tests for IOException scenario
   * @throws MongoException
   * @throws MongoFileNotFoundException
   * @throws MultipleFileFoundException
   * @throws MissingPropertyException
   * @throws EmptyPropertyException
   */
  public void testIOExceptionScenario() throws MongoException,MongoFileNotFoundException,
  MultipleFileFoundException,MissingPropertyException,EmptyPropertyException {
	try {
	  new MongoFunctionImpl();
	  assertEquals(true,true);
	}
	catch(IOException e) {
	  assertEquals(true,false);
	}
  }

  /**
   * This function tests for MissingPropertyException scenario
   * @throws MongoException
   * @throws MongoFileNotFoundException
   * @throws IOException
   * @throws MultipleFileFoundException
   * @throws EmptyPropertyException
   */
  public void testMissingPropertyExceptionScenario() throws MongoException,MongoFileNotFoundException,
  IOException,MultipleFileFoundException,EmptyPropertyException {
	try {
	  new MongoFunctionImpl();
	  assertEquals(true,true);
	}
	catch(MissingPropertyException e) {
	  assertEquals(true,false);
	}
  }

  /**
   * This function tests for EmptyPropertyException scenario
   * @throws MongoException
   * @throws MongoFileNotFoundException
   * @throws IOException
   * @throws MissingPropertyException
   * @throws MultipleFileFoundException
   */
  public void testEmptyPropertyExceptionScenario() throws MongoException,MongoFileNotFoundException,
  IOException,MissingPropertyException,MultipleFileFoundException {
	try {
	  new MongoFunctionImpl();
	  assertEquals(true,true);
	}
	catch(EmptyPropertyException e) {
		assertEquals(true,false);
	}
  }
}