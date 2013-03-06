package com.javastorm.hadoopstarter.test.hdfs;


import java.io.IOException;
import java.util.Scanner;

import com.javastorm.hadoopstarter.hdfs.common.PathResolver;
import com.javastorm.hadoopstarter.hdfs.curd.HdfsFileRemover;

/**
 * This class is intended for depicting the usage of HdfsFileRemover 
 * 
 * @author Hemant Kumar
 * @version 1.0 Dated: 01/03/2013
 */
public class HdfsFileRemoverTest
{
    public static void main(String[] args) throws IOException {
    	Scanner scanner = new Scanner(System.in);
		System.out.println("Enter File Path :");
		String path = scanner.next();
		path = PathResolver.resolveHdfsPath(path);
    	new HdfsFileRemover().removeFile(path);
    	System.out.println("Done");
    }
}