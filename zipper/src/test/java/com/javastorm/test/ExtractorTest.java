package com.javastorm.test;

import java.io.File;
import java.util.Scanner;

import com.javastorm.zip.Extractor;

/**
 * This class is intended for depicting the usage of Extractor class
 * 
 * @author Hemant Kumar
 * @version 1.0 Dated: 01/03/2013
 */
public class ExtractorTest
{	
	public static void main(String[] args) throws Exception {
		System.out.print("Enter Zip File Path : ");
    	Scanner scanner = new Scanner(System.in);
    	String filepath = scanner.next();
		new Extractor().extractZip(new File(filepath), false, true);
	}
}
