package com.javastorm.hadoopstarter.test.hdfs;


import java.io.IOException;
import java.util.Scanner;

import com.javastorm.hadoopstarter.hdfs.common.PathResolver;
import com.javastorm.hadoopstarter.hdfs.curd.HdfsDirectoryCreator;

/**
 * This class is intended for depicting the usage of HdfsDirectoryCreator 
 * 
 * @author Hemant Kumar
 * @version 1.0 Dated: 01/03/2013
 */
public class HdfsDirectoryCreatorTest 
{
	public static void main(String[] args) throws IOException {
		System.out.print("Enter Dir Path : ");
    	Scanner scanner = new Scanner(System.in);
    	String dir = scanner.next();
    	dir = PathResolver.resolveHdfsPath(dir);
		new HdfsDirectoryCreator().createDirectory(dir);
		System.out.println("Done");
	}
}