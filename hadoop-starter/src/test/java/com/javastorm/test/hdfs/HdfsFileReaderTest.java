package com.javastorm.test.hdfs;


import java.io.IOException;
import java.util.Scanner;

import com.javastorm.hdfs.common.PathResolver;
import com.javastorm.hdfs.curd.HdfsFileReader;

/**
 * This class is intended for depicting the usage of HdfsFileReader
 * 
 * @author Hemant Kumar
 * @version 1.0 Dated: 01/03/2013
 */
public class HdfsFileReaderTest
{
	public static void main(String[] args) throws IOException {
		System.out.print("Enter Source(hdfs) File Path : ");
		Scanner scanner = new Scanner(System.in);
		String source = scanner.next();
		source = PathResolver.resolveHdfsPath(source);
		System.out.println("Enter Destination(local) File Path :");
		String destination = scanner.next();
		new HdfsFileReader().readFile(source, destination);
		System.out.println("Done");
	}
}