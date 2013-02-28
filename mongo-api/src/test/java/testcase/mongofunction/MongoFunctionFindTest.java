package testcase.mongofunction;

import helper.TestEntity;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mongodb.MongoException;
import common.exception.EmptyPropertyException;
import common.exception.MissingPropertyException;
import common.exception.MultipleFileFoundException;

import junit.framework.TestCase;
import mongo.domain.MongoQueryInfo;
import mongo.exception.MongoFileNotFoundException;
import mongo.function.impl.MongoFunctionImpl;
import mongo.function.spi.MongoFunction;

/**
 * This MongoFunctionFindTest class is for testing of find scenario of MongoFunction
 * 
 * @author hemant singh
 * @version 1.0 Dated: 13/01/2013
 */
public class MongoFunctionFindTest extends TestCase {

  /**
   * This function is used for testing of query string scenario	
   * @throws MongoException
   * @throws MultipleFileFoundException
   * @throws MongoFileNotFoundException
   * @throws IOException
   * @throws MissingPropertyException
   * @throws EmptyPropertyException
   */
  public void testQueryStringScenario() throws MongoException,MultipleFileFoundException,
  		 MongoFileNotFoundException,IOException,MissingPropertyException,EmptyPropertyException {
    MongoFunction mongoFunction_ = new MongoFunctionImpl();
    MongoQueryInfo queryInfo_ = new MongoQueryInfo();
	queryInfo_.setQueryString("{ \"_id\" : { \"$gt\" : \"123\"}}");
    queryInfo_.setFindAsJson(true);
    TestEntity testEntity_ = new TestEntity();
	testEntity_.setQueryInfo(queryInfo_);
    @SuppressWarnings("rawtypes")
	List resultList_ = (List) mongoFunction_.find(testEntity_);
	for(Object record_ : resultList_)
	  System.out.println(record_);
  }
  
  /**
   * This function is for testing of query map scenario
   * @throws MongoException
   * @throws MultipleFileFoundException
   * @throws MongoFileNotFoundException
   * @throws IOException
   * @throws MissingPropertyException
   * @throws EmptyPropertyException
   */
  public void testQueryMapScenario() throws MongoException,MultipleFileFoundException,
	MongoFileNotFoundException,IOException,MissingPropertyException,EmptyPropertyException {
    MongoFunction mongoFunction_ = new MongoFunctionImpl();
    MongoQueryInfo queryInfo_ = new MongoQueryInfo();
    Map<Object,Object> queryMap_ = new HashMap<Object,Object>();
    Map<Object,Object> subMap_ = new HashMap<Object,Object>();
    subMap_.put("$size",4);
    queryMap_.put("myList",subMap_);	
    queryInfo_.setQueryMap(queryMap_);
    queryInfo_.setFindAsJson(true);
    TestEntity testEntity_ = new TestEntity();
    testEntity_.setQueryInfo(queryInfo_);
    @SuppressWarnings("rawtypes")
	List resultList_ = (List) mongoFunction_.find(testEntity_);
	for(Object record_ : resultList_)
	  System.out.println(record_);
  }
  
  /**
   * This function is for testing of combined query string and map scenario
   * @throws MongoException
   * @throws MultipleFileFoundException
   * @throws MongoFileNotFoundException
   * @throws IOException
   * @throws MissingPropertyException
   * @throws EmptyPropertyException
   */
  public void testCombinedQueryStringAndQueryMapScenario() throws MongoException,MultipleFileFoundException,
	 MongoFileNotFoundException,IOException,MissingPropertyException,EmptyPropertyException {
    MongoFunction mongoFunction_ = new MongoFunctionImpl();
    MongoQueryInfo queryInfo_ = new MongoQueryInfo();
    queryInfo_.setQueryString("{ \"_id\" : { \"$gt\" : \"123\"}}");
    Map<Object,Object> queryMap_ = new HashMap<Object,Object>();
    Map<Object,Object> subMap_ = new HashMap<Object,Object>();
    subMap_.put("$size",4);
    queryMap_.put("myList",subMap_);
    queryInfo_.setQueryMap(queryMap_);
    queryInfo_.setFindAsJson(true);
    TestEntity testEntity_ = new TestEntity();
    testEntity_.setQueryInfo(queryInfo_);
    @SuppressWarnings("rawtypes")
	List resultList_ = (List) mongoFunction_.find(testEntity_);
	for(Object record_ : resultList_)
	  System.out.println(record_);
  }
}