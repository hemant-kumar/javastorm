package com.leadtheworld.test.hdfs;

import java.util.Scanner;

import com.leadtheworld.hdfs.common.PathResolver;

/**
 * This class is intended for depicting the usage of PathResolver  
 * 
 * @author Hemant Kumar
 * @version 1.0 Dated: 01/03/2013
 */
public class PathResolverTest 
{
	public static void main(String[] args) {
		System.out.print("Enter Path : ");
		Scanner scanner = new Scanner(System.in);
		String path = scanner.next();
		System.out.println("Resolved Path --> " + PathResolver.resolveHdfsPath(path));
	}

}
