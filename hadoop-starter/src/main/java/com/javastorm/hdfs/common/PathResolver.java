package com.leadtheworld.hdfs.common;

/**
 * This class is intended for resolving the hdfs path to fully qualified hdfs path 
 * 
 * @author Hemant Kumar
 * @version 1.0 Dated: 01/03/2013
 */
public class PathResolver 
{
	public static String resolveHdfsPath(String path) {
    	String fsDefault = PropertyLoader.getProperty("fs-default-name") ;
    	if(!path.startsWith(fsDefault)) {
    		if(path.startsWith("/")) {
    			path = fsDefault + path;
    		}
    		else {
    			path = fsDefault + "/" + path;
    		}
    	}
    	return path;
	}
}
