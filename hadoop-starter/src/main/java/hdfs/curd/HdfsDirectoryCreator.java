package hdfs.curd;

import hdfs.common.PathResolver;
import hdfs.common.PropertyLoader;

import java.io.IOException;
import java.util.Scanner;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

/**
 * This class is intended for creating a new directory in HDFS 
 * @author Hemant Kumar
 *
 */
public class HdfsDirectoryCreator {

	FileSystem fileSystem;

	public HdfsDirectoryCreator() throws IOException {
		Configuration conf = new Configuration();
		conf.addResource(new Path(PropertyLoader.getProperty("core-site-xml-path")));
		conf.addResource(new Path(PropertyLoader.getProperty("hdfs-site-xml-path")));
		fileSystem = FileSystem.get(conf);
	}

	public void createDirectory(String dir) throws IOException {
		Path path = new Path(dir);
		if (fileSystem.exists(path)) {
			System.out.println("Dir " + dir + " already not exists");
			return;
		}
		fileSystem.mkdirs(path);
		fileSystem.close();
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
		new HdfsDirectoryCreator().createDirectory(dir);
		System.out.println("Done");
	}
}