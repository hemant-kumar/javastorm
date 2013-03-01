package com.leadtheworld.test;

import java.util.Scanner;

import com.leadtheworld.zip.Zipper;

/**
 * This class is intended for depicting the usage of Zipper class
 * 
 * @author Hemant Kumar
 * @version 1.0 Dated: 01/03/2013
 */
public class ZipperTest 
{
	public static void main(String[] args) throws Exception {
		System.out.print("Enter Dir Path : ");
    	Scanner scanner = new Scanner(System.in);
    	String dir = scanner.next();
		new Zipper().zip(dir);
	}

}
