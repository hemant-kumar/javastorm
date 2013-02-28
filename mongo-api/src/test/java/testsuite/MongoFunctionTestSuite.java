package testsuite;

import testcase.mongofunction.MongoFunctionConstructorTest;
import testcase.mongofunction.MongoFunctionDeleteTest;
import testcase.mongofunction.MongoFunctionFindTest;
import testcase.mongofunction.MongoFunctionSaveTest;
import testcase.mongofunction.MongoFunctionUpdateTest;
import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * This MongoFunctionTestSuite class bundles all the Test classes
 * 
 * @author hemant singh
 * @version 1.0 Dated: 13/01/2013
 */
public class MongoFunctionTestSuite 
{
  /**
   * This function is responsible for executing all the test cases
   * @return TestSuite
   */
  public static Test suite() {
    TestSuite suite = new TestSuite("Mongo Function Test Suite");
	//$JUnit-BEGIN$
	suite.addTestSuite(MongoFunctionConstructorTest.class);
	suite.addTestSuite(MongoFunctionSaveTest.class);
	suite.addTestSuite(MongoFunctionFindTest.class);
	suite.addTestSuite(MongoFunctionDeleteTest.class);
	suite.addTestSuite(MongoFunctionUpdateTest.class);
	//$JUnit-END$
	return suite;
  }
}