package com.javastorm.hadoopstarter.hdfs.curd;


import java.io.IOException;
import java.util.Scanner;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import com.javastorm.hadoopstarter.hdfs.common.PathResolver;
import com.javastorm.hadoopstarter.hdfs.common.PropertyLoader;

/**
 * This class is intended for listing all files in the specified HDFS dir 
 * 
 * @author Hemant Kumar
 * @version 1.0 Dated: 01/03/2013
 */
public class HdfsFileLister 
{
	private FileSystem fileSystem;

	public HdfsFileLister() throws IOException {
		Configuration conf = new Configuration();
		conf.addResource(new Path(PropertyLoader.getProperty("core-site-xml-path")));
		conf.addResource(new Path(PropertyLoader.getProperty("hdfs-site-xml-path")));
		fileSystem = FileSystem.get(conf);
	}

	public void list(String path) throws IOException {
		Path dirPath = new Path(path);
		FileStatus[] fileStatus = fileSystem.listStatus(dirPath);
		if(fileStatus != null) {
			for (FileStatus fs : fileStatus) {
				path = dirPath.toString() + "/" + fs.getPath().getName();
				if(fs.isDir()) {
					list(path);
				}
				else {
					System.out.println(path);
				}
			}
		}
	}

	@Override
	protected void finalize() throws Throwable {
		fileSystem.close();
		super.finalize();
	}

	public static void main(String[] args) throws IOException {
		System.out.print("Enter Dir Path : ");
    	Scanner scanner = new Scanner(System.in);
    	String dir = scanner.next();
    	dir = PathResolver.resolveHdfsPath(dir);
		new HdfsFileLister().list(dir);
	}
}