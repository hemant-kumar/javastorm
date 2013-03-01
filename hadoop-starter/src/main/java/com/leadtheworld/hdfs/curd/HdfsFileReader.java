package com.leadtheworld.hdfs.curd;


import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Scanner;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import com.leadtheworld.hdfs.common.PathResolver;
import com.leadtheworld.hdfs.common.PropertyLoader;

/**
 * This class is intended for reading a file from HDFS and writing it to local file system
 * 
 * @author Hemant Kumar
 * @version 1.0 Dated: 01/03/2013
 */
public class HdfsFileReader 
{
	FileSystem fileSystem;

	public HdfsFileReader() throws IOException {
		Configuration conf = new Configuration();
		conf.addResource(new Path(PropertyLoader.getProperty("core-site-xml-path")));
		conf.addResource(new Path(PropertyLoader.getProperty("hdfs-site-xml-path")));
		fileSystem = FileSystem.get(conf);
	}

	public void readFile(String source, String destination) throws IOException {
		Path path = new Path(source);
		if (!fileSystem.exists(path)) {
			System.out.println("File " + source + " does not exists");
			return;
		}
		FSDataInputStream in = fileSystem.open(path);
		OutputStream out = new BufferedOutputStream(new FileOutputStream(new File(destination)));
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
		System.out.print("Enter Source(hdfs) File Path : ");
		Scanner scanner = new Scanner(System.in);
		String source = scanner.next();
		source = PathResolver.resolveHdfsPath(source);
		System.out.println("Enter Destination(local) File Path :");
		String destination = scanner.next();
		new HdfsFileReader().readFile(source, destination);
		System.out.println("Done");
	}
}