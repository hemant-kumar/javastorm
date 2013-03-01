package com.leadtheworld.hdfs.common;

import java.io.IOException;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;

/**
 * This class is intended for loading the hadoop-properties file
 * @author hemant
 *
 */
public class PropertyLoader {

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
	
	public static String getProperty(String propertyName) {
		return StringUtils.isEmpty(props.getProperty(propertyName)) ? System.getProperty(propertyName) :
			props.getProperty(propertyName);
	}
}
