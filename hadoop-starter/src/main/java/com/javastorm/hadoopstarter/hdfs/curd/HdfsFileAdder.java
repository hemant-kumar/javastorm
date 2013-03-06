package com.javastorm.hadoopstarter.hdfs.curd;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import com.javastorm.hadoopstarter.hdfs.common.PathResolver;
import com.javastorm.hadoopstarter.hdfs.common.PropertyLoader;

/**
 * This class is intended for adding a new file to HDFS 
 * 
 * @author Hemant Kumar
 * @version 1.0 Dated: 01/03/2013
 */
public class HdfsFileAdder 
{
	FileSystem fileSystem;

	public HdfsFileAdder() throws IOException {
		Configuration conf = new Configuration();
		conf.addResource(new Path(PropertyLoader.getProperty("core-site-xml-path")));
		conf.addResource(new Path(PropertyLoader.getProperty("hdfs-site-xml-path")));
		fileSystem = FileSystem.get(conf);
	}

	public void addFile(String source, String dest) throws IOException {
		Path path = new Path(dest);
		if(fileSystem.exists(path)) {
			System.out.println("File already exists");
			return;
		}
		FSDataOutputStream out = fileSystem.create(path);
		FileInputStream in = new FileInputStream(new File(source));
		byte[] b = new byte[1024];
		int numBytes = 0;
		while ((numBytes = in.read(b)) > 0) {
			out.write(b, 0, numBytes);
		}
		in.close();
		out.close();
	}

	@Override
	protected void finalize() throws Throwable {
		fileSystem.close();
		super.finalize();
	}

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