package com.javastorm.hadoopstarter.test.hdfs;

import com.javastorm.hadoopstarter.hdfs.common.PropertyLoader;

/**
 * This class is intended for depicting the usage of PropertyLoader
 * 
 * @author Hemant Kumar
 * @version 1.0 Dated: 01/03/2013
 */
public class PropertyLoaderTest
{
	public static void main(String[] args) {
		System.out.println(PropertyLoader.getProperty("core-site-xml-path"));
	}
}
