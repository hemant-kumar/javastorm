package com.javastorm.test.hdfs;

import java.io.IOException;
import java.util.Scanner;

import com.javastorm.hdfs.common.PathResolver;
import com.javastorm.hdfs.curd.HdfsFileLister;

/**
 * This class is intended for depicting the usage of HdfsFileLister 
 * 
 * @author Hemant Kumar
 * @version 1.0 Dated: 01/03/2013
 */
public class HdfsFileListerTest
{
	public static void main(String[] args) throws IOException {
		System.out.print("Enter Dir Path : ");
    	Scanner scanner = new Scanner(System.in);
    	String dir = scanner.next();
    	dir = PathResolver.resolveHdfsPath(dir);
		new HdfsFileLister().list(dir);
	}
}