package testcase.mongofunction;

import helper.TestEntity;

import java.io.IOException;
import java.util.List;

import com.mongodb.MongoException;
import common.exception.EmptyPropertyException;
import common.exception.MissingPropertyException;
import common.exception.MultipleFileFoundException;

import junit.framework.TestCase;
import mongo.domain.MongoQueryInfo;
import mongo.exception.MongoFileNotFoundException;
import mongo.exception.MongoUpdateException;
import mongo.function.impl.MongoFunctionImpl;
import mongo.function.spi.MongoFunction;

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