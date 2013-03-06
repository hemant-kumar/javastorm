package com.javastorm.hadoopstarter.hdfs.common;

import java.io.IOException;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;

/**
 * This class is intended for loading the hadoop-properties file
 * 
 * @author Hemant Kumar
 * @version 1.0 Dated: 01/03/2013
 */
public class PropertyLoader
{
	private static Properties props;
	
	static {
		props = new Properties();
		try {
			props.load(PropertyLoader.class.getClassLoader().getResourceAsStream("hadoop.properties"));
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static String getProperty(String propName) {
		return StringUtils.isEmpty(props.getProperty(propName)) ? System.getProperty(propName) : props.getProperty(propName);
	}
}
