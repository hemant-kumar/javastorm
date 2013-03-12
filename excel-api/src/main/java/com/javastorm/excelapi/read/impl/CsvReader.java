package com.javastorm.excelapi.read.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.javastorm.excelapi.read.spi.Reader;

/**
 * This interface is intended for providing the functions for reading a *.csv file.
 * 
 * @author Hemant Kumar
 * @version 1.0 Dated: 11/03/2013
 */
public class CsvReader implements Reader {

	@Override
	public String[][] read(File file) throws Exception {
		return read(new FileInputStream(file), ',', '\n');
	}

	@Override
	public String[][] read(String filepath) throws Exception {
		return read(new FileInputStream(filepath), ',', '\n');
	}

	@Override
	public String[][] read(InputStream stream) throws Exception {
		return read(stream, ',', '\n');
	}

	@Override
	public String[][] read(File file, char delimiter) throws Exception {
		return read(new FileInputStream(file), delimiter, '\n');
	}

	@Override
	public String[][] read(String filepath, char delimiter) throws Exception {
		return read(new FileInputStream(filepath), delimiter, '\n');
	}

	@Override
	public String[][] read(InputStream stream, char delimiter) throws Exception {
		return read(stream, delimiter, '\n');
	}

	@Override
	public String[][] read(File file, char delimiter, char rowdelimiter) throws Exception {
		return read(new FileInputStream(file), delimiter, rowdelimiter);
	}

	@Override
	public String[][] read(String filepath, char delimiter, char rowdelimiter) throws Exception {
		return read(new FileInputStream(filepath), delimiter, rowdelimiter);
	}

	@Override
	public String[][] read(InputStream stream, char delimiter, char rowdelimiter) throws Exception {
		Scanner scanner = new Scanner(stream);
		scanner.useDelimiter(String.valueOf(rowdelimiter));
		String line = scanner.next();
		String[] headers = line.split(String.valueOf(delimiter));
		List<String[]> lt = new ArrayList<String[]>();
		lt.add(headers);
		while(scanner.hasNext()) {
			line = scanner.next();
			String[] value = line.split(String.valueOf(delimiter));
			if(headers.length == value.length) {
				lt.add(value);
			}
			else {
				throw new Exception("Invalid formatting in CSV");
			}
		}
		String[][] result = new String[lt.size()][headers.length];
		result = lt.toArray(result);
		return result;
	}

	@Override
	public Map<String, String[][]> readAllSheets(File file) throws Exception {
		throw new Exception("Not Supported");
	}

	@Override
	public Map<String, String[][]> readAllSheets(String filePath) throws Exception {
		throw new Exception("Not Supported");
	}

	@Override
	public Map<String, String[][]> readAllSheets(InputStream stream) throws Exception {
		throw new Exception("Not Supported");
	}

	@Override
	public String[][] readSheet(File file, int sheetNo) throws Exception {
		throw new Exception("Not Supported");
	}

	@Override
	public String[][] readSheet(String filePath, int sheetNo) throws Exception {
		throw new Exception("Not Supported");
	}

	@Override
	public String[][] readSheet(InputStream stream, int sheetNo) throws Exception {
		throw new Exception("Not Supported");
	}

	@Override
	public String[][] readFirstSheet(String filePath) throws Exception {
		throw new Exception("Not Supported");
	}

	@Override
	public String[][] readFirstSheet(File file) throws Exception {
		throw new Exception("Not Supported");
	}

	@Override
	public String[][] readFirstSheet(InputStream stream) throws Exception {
		throw new Exception("Not Supported");
	}

	@Override
	public String[][] readLastSheet(File file) throws Exception {
		throw new Exception("Not Supported");
	}

	@Override
	public String[][] readLastSheet(String filePath) throws Exception {
		throw new Exception("Not Supported");
	}

	@Override
	public String[][] readLastSheet(InputStream stream) throws Exception {
		throw new Exception("Not Supported");
	}
}
