package com.leadtheworld.testcase.mongofunction;


import java.io.IOException;
import java.util.List;

import com.leadtheworld.common.exception.EmptyPropertyException;
import com.leadtheworld.common.exception.MissingPropertyException;
import com.leadtheworld.common.exception.MultipleFileFoundException;
import com.leadtheworld.helper.TestEntity;
import com.leadtheworld.mongo.domain.MongoQueryInfo;
import com.leadtheworld.mongo.exception.MongoFileNotFoundException;
import com.leadtheworld.mongo.exception.MongoUpdateException;
import com.leadtheworld.mongo.function.impl.MongoFunctionImpl;
import com.leadtheworld.mongo.function.spi.MongoFunction;
import com.mongodb.MongoException;

import junit.framework.TestCase;

/**
 * This MongoFunctionUpdateTest class is responsible for testing of update scenario of MongoFunction
 * 
 * @author hemant singh
 * @version 1.0 Dated: 13/01/2013
 */
public class MongoFunctionUpdateTest extends TestCase {

  /**
   * This function is responsible for testing the update scenario 	
   * @throws MongoException
   * @throws MultipleFileFoundException
   * @throws IOException
   * @throws MongoUpdateException
   * @throws MongoFileNotFoundException
   * @throws MissingPropertyException
   * @throws EmptyPropertyException
   */
  @SuppressWarnings("rawtypes")	
  public void testUpdate() throws MongoException,MultipleFileFoundException,IOException,MongoUpdateException,
  			MongoFileNotFoundException, MissingPropertyException, EmptyPropertyException {
    MongoFunction mongoFunction_ = new MongoFunctionImpl();
    MongoQueryInfo queryInfo_ = new MongoQueryInfo();
	queryInfo_.setQueryString("{ \"_id\" : \"12345\"}");
    TestEntity findEntity_ = new TestEntity();
	findEntity_.setQueryInfo(queryInfo_);
  	List resultList_ = (List) mongoFunction_.find(findEntity_);
  	TestEntity initialEntity_ = (TestEntity)resultList_.get(0);
    System.out.println("Initial value --> " + initialEntity_.getInfo());
    TestEntity updateEntity_ = new TestEntity();
    MongoQueryInfo updateQueryInfo_ = new MongoQueryInfo();
    updateQueryInfo_.setQueryString("{ \"$set\" : { \"info\" : \"Testing for update\"}}");
    updateEntity_.setQueryInfo(updateQueryInfo_);
    mongoFunction_.update(findEntity_,updateEntity_);
	resultList_ = (List) mongoFunction_.find(findEntity_);
  	TestEntity updatedEntity_ = (TestEntity)resultList_.get(0);
    System.out.println("Updated value --> " + updatedEntity_.getInfo());
  }
}