package com.javastorm.zip;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import com.javastorm.common.FileListGetter;

/**
 * This class is intended for zipping of files.
 * 
 * @author Hemant Kumar
 * @version 1.0 Dated: 01/03/2013
 */
public class Zipper 
{
	/**
	 * This function is intended for creating a zip
	 * @param file
	 * @param listFiles
	 * @throws Exception
	 */
	private void createZip(File srcFile,File[] listFiles) throws Exception {
		byte b[] = new byte[10240];
		FileOutputStream fout = new FileOutputStream(srcFile.toString() + ".zip");
		ZipOutputStream out = new ZipOutputStream(fout);
		for(File file : listFiles) {
			ZipEntry addFiles = new ZipEntry(file.toString().replace(srcFile.toString()+"\\",""));
			addFiles.setTime(file.lastModified());
			out.putNextEntry(addFiles);
			FileInputStream fin = new FileInputStream(file);
			while(true) {
				int len = fin.read(b, 0, b.length);
				if (len <= 0)
					break;
				out.write(b, 0, len);
			}
			fin.close();
		}
		out.close();
		fout.close();
	}
	
	/**
	 * This function acts as a delegate between caller & actual zipper function
	 * @param path
	 * @throws Exception
	 */
	public void zip(String path) throws Exception {
		File file = new File(path);
		ArrayList<File> fileList = new ArrayList<File>();
		fileList = new FileListGetter().getFileList(fileList, path);
		File[] lists = new File[fileList.size()];
		lists = fileList.toArray(lists);
		createZip(file,lists);
	}
}
