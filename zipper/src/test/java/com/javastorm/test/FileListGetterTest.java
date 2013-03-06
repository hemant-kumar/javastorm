package com.javastorm.test;

import java.io.File;
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Scanner;

import com.javastorm.zipper.common.FileListGetter;

/**
 * This class is intended for depicting the usage of FileListGetter class
 * 
 * @author Hemant Kumar
 * @version 1.0 Dated: 01/03/2013
 */
public class FileListGetterTest 
{
	public static void main(String[] args) {
		System.out.print("Enter Dir Path : ");
    	Scanner scanner = new Scanner(System.in);
    	String dir = scanner.next();
		FileListGetter fileListGetter = new FileListGetter();
		ArrayList<File> list = new ArrayList<File>(); 
		list = fileListGetter.getFileList(list,dir);
		ListIterator<File> lt = list.listIterator();
		System.out.println("File List");
		while (lt.hasNext()) {
			System.out.println(lt.next());
		}
	}
}
