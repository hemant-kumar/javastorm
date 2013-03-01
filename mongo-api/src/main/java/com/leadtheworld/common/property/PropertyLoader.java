package com.leadtheworld.common.property;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import com.leadtheworld.common.exception.EmptyPropertyException;
import com.leadtheworld.common.exception.MissingPropertyException;

/**
 * The PropertyLoader class is used for loading properties from various files 
 * 
 * @author Hemant Kumar
 * @version 1.0 Dated: 13/01/2013 
 */
public class PropertyLoader 
{
  /**
   * This variable is used a local cache.	
   */
  private static Properties props_;
    
  static {
    props_ = new Properties();
  }

  /**
   * This method parse the file and loads the properties in cache
   * @param file : File to be parsed
   * @throws IOException
   */
  public static void loadAndStore(File file) throws IOException {
    props_.load(new FileInputStream(file));
  }
  
  /**
   * This method parse the file and loads the properties in a Map and return it
   * @param file : File to be parsed
   * @return Map containing all the properties of input file
   * @throws IOException
   */
  public static Map<String,String> loadAndReturn(File file) throws IOException {
    Properties properties_ = new Properties();
	properties_.load(new FileInputStream(file));
	Map<String,String> map_ = new HashMap<String,String>();
	Set<Object> keySet = properties_.keySet();
	for(Object object_ : keySet) {
	  map_.put(object_.toString(),properties_.getProperty(object_.toString()));
	}
	return map_;
  }

  /**
   * This method is used to get the value of property from local cache or 
   * system properties wherever available  
   * @param prop : Name of the property 
   * @return Value of the input property
   * @throws MissingPropertyException
   * @throws EmptyPropertyException
   */
  public static String getProperty(String prop) throws MissingPropertyException,EmptyPropertyException {
	String value_ = props_.getProperty(prop)!=null ? props_.getProperty(prop) : System.getProperty(prop);
	if(value_ == null)
	  throw new MissingPropertyException("Property named \"" + prop + "\" not found");
	value_ = value_.trim();
	if(value_.length() == 0)
	  throw new EmptyPropertyException("No value provided against property named \"" + prop + "\"");
    return value_;
  }
}