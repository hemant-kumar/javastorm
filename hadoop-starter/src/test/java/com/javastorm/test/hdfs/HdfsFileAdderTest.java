package com.javastorm.test.hdfs;


import java.io.IOException;
import java.util.Scanner;

import com.javastorm.hdfs.common.PathResolver;
import com.javastorm.hdfs.curd.HdfsFileAdder;

/**
 * This class is intended for depicting the usage of HdfsFileAdder 
 * 
 * @author Hemant Kumar
 * @version 1.0 Dated: 01/03/2013
 */
public class HdfsFileAdderTest
{
	public static void main(String[] args) throws IOException {
		System.out.print("Enter Source(local) File Path : ");
		Scanner scanner = new Scanner(System.in);
		String source = scanner.next();
		System.out.println("Enter Destination(hdfs) File Path :");
		String destination = scanner.next();
		destination = PathResolver.resolveHdfsPath(destination);
		new HdfsFileAdder().addFile(source, destination);
		System.out.println("Done");
	}
}