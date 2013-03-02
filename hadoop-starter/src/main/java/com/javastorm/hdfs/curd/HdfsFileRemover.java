package com.javastorm.hdfs.curd;


import java.io.IOException;
import java.util.Scanner;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import com.javastorm.hdfs.common.PathResolver;
import com.javastorm.hdfs.common.PropertyLoader;

/**
 * This class is intended for removing a file from HDFS 
 * 
 * @author Hemant Kumar
 * @version 1.0 Dated: 01/03/2013
 */
public class HdfsFileRemover {

	FileSystem fileSystem;

	public HdfsFileRemover() throws IOException {
		Configuration conf = new Configuration();
		conf.addResource(new Path(PropertyLoader.getProperty("core-site-xml-path")));
		conf.addResource(new Path(PropertyLoader.getProperty("hdfs-site-xml-path")));
		fileSystem = FileSystem.get(conf);
	}
	
    public void removeFile(String path) throws IOException {
        Path filePath = new Path(path);
        if(!fileSystem.exists(filePath)) {
            System.out.println("File " + filePath + " does not exists");
            return;
        }
        fileSystem.delete(filePath, true);
        fileSystem.close();
    }

	@Override
	protected void finalize() throws Throwable {
		fileSystem.close();
		super.finalize();
	}
	
    public static void main(String[] args) throws IOException {
    	Scanner scanner = new Scanner(System.in);
		System.out.println("Enter File Path :");
		String path = scanner.next();
		path = PathResolver.resolveHdfsPath(path);
    	new HdfsFileRemover().removeFile(path);
    	System.out.println("Done");
    }
}