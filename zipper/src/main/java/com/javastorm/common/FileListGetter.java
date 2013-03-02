package com.javastorm.common;

import java.io.File;
import java.util.ArrayList;

/**
 * This class is intended for getting the list of files from the specified path in a recursive manner
 * 
 * @author Hemant Kumar
 * @version 1.0 Dated: 01/03/2013
 */
public class FileListGetter
{
	/**
	 * This function performs recursive listing of files
	 * @param fileList 
	 * @param filePath
	 * @return
	 */
	public ArrayList<File> list(ArrayList<File> fileList, String filePath)
	{
		File file =new File(filePath);
		if(file.isDirectory() == true) {
			File tempList[] = file.listFiles();
			int i = 0;
			while(i<tempList.length) {
				if(tempList[i].isDirectory()==false) {
					fileList.add(tempList[i]);
				}
				else {
					list(fileList, tempList[i].toString());
				}
				i++;
			}
		}
		return fileList;
	}

	/**
	 * This function acts as a delegate between caller & actual listing function in order to perform cleaning of list
	 * @param fileList
	 * @param filePath
	 * @return
	 */
	public ArrayList<File> getFileList(ArrayList<File> fileList, String filePath) {
		fileList.clear();
		return list(fileList, filePath);
	}
}
