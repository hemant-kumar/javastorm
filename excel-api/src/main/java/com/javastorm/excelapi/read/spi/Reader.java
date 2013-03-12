package com.javastorm.excelapi.read.spi;

import java.io.File;
import java.io.InputStream;
import java.util.Map;

/**
 * This interface is intended for providing the functions for reading a CSV file.
 * 
 * @author Hemant Kumar
 * @version 1.0 Dated: 11/03/2013
 */
public interface Reader
{
	/**
	 * <b>CSV</b>  : Read whole file.<br>
	 * <b>XLS</b>  : Read first sheet.<br>
	 * <b>XLSX</b> : Read first sheet.
	 * @param file
	 * @return String[][]
	 * @throws Exception
	 */
	public String[][] read(File file) throws Exception;

	/**
	 * <b>CSV</b>  : Read whole file.<br>
	 * <b>XLS</b>  : Read first sheet.<br>
	 * <b>XLSX</b> : Read first sheet.
	 * @param filepath
	 * @return String[][]
	 * @throws Exception
	 */
	public String[][] read(String filepath) throws Exception;

	/**
	 * <b>CSV</b>  : Read whole file.<br>
	 * <b>XLS</b>  : Read first sheet.<br>
	 * <b>XLSX</b> : Read first sheet.
	 * @param stream
	 * @return String[][]
	 * @throws Exception
	 */
	public String[][] read(InputStream stream) throws Exception;

	/**
	 * <b>CSV</b>  : Read whole file using specified delimiter.<br>
	 * @param file
	 * @param delimiter
	 * @return String[][]
	 * @throws Exception
	 */
	public String[][] read(File file, char delimiter) throws Exception;

	/**
	 * <b>CSV</b>  : Read whole file using specified delimiter.<br>
	 * @param filepath
	 * @param delimiter
	 * @return String[][]
	 * @throws Exception
	 */
	public String[][] read(String filepath, char delimiter) throws Exception;

	/**
	 * <b>CSV</b>  : Read whole file using specified delimiter.<br>
	 * @param stream
	 * @param delimiter
	 * @return String[][]
	 * @throws Exception
	 */
	public String[][] read(InputStream stream, char delimiter) throws Exception;

	/**
	 * <b>CSV</b>  : Read whole file using specified delimiter & row delimiter.<br>
	 * @param file
	 * @param delimiter
	 * @param rowdelimiter
	 * @return String[][]
	 * @throws Exception
	 */
	public String[][] read(File file, char delimiter, char rowdelimiter) throws Exception;

	/**
	 * <b>CSV</b>  : Read whole file using specified delimiter & row delimiter.<br>
	 * @param filepath
	 * @param delimiter
	 * @param rowdelimiter
	 * @return String[][]
	 * @throws Exception
	 */
	public String[][] read(String filepath, char delimiter, char rowdelimiter) throws Exception;

	/**
	 * <b>CSV</b>  : Read whole file using specified delimiter & row delimiter.<br>
	 * @param stream
	 * @param delimiter
	 * @param rowdelimiter
	 * @return String[][]
	 * @throws Exception
	 */
	public String[][] read(InputStream stream, char delimiter, char rowdelimiter) throws Exception;

	/**
	 * <b>XLS</b> : Read all sheets.<br>
	 * <b>XLSX</b>: Read all sheets.
	 * @param file
	 * @return Map<String,String[][]>
	 * @throws Exception
	 */
	public Map<String,String[][]> readAllSheets(File file) throws Exception;

	/**
	 * <b>XLS</b> : Read all sheets.<br>
	 * <b>XLSX</b>: Read all sheets.
	 * @param file
	 * @return Map<String,String[][]>
	 * @throws Exception
	 */
	public Map<String,String[][]> readAllSheets(String filePath) throws Exception;

	/**
	 * <b>XLS</b> : Read all sheets.<br>
	 * <b>XLSX</b>: Read all sheets.
	 * @param file
	 * @return Map<String,String[][]>
	 * @throws Exception
	 */
	public Map<String,String[][]> readAllSheets(InputStream stream) throws Exception;

	/**
	 * <b>XLS</b> : Read the specified sheet.<br>
	 * <b>XLSX</b>: Read the specified sheet.
	 * @param file
	 * @param sheetNo
	 * @return String[][]
	 * @throws Exception
	 */
	public String[][] readSheet(File file, int sheetNo) throws Exception;

	/**
	 * <b>XLS</b> : Read the specified sheet.<br>
	 * <b>XLSX</b>: Read the specified sheet.
	 * @param filePath
	 * @param sheetNo
	 * @return String[][]
	 * @throws Exception
	 */
	public String[][] readSheet(String filePath, int sheetNo) throws Exception;

	/**
	 * <b>XLS</b> : Read the specified sheet.<br>
	 * <b>XLSX</b>: Read the specified sheet.
	 * @param stream
	 * @param sheetNo
	 * @return String[][]
	 * @throws Exception
	 */
	public String[][] readSheet(InputStream stream, int sheetNo) throws Exception;
	
	/**
	 * <b>XLS</b> : Read first sheet.<br>
	 * <b>XLSX</b>: Read first sheet.
	 * @param filePath
	 * @return String[][]
	 * @throws Exception
	 */
	public String[][] readFirstSheet(String filePath) throws Exception;

	/**
	 * <b>XLS</b> : Read first sheet.<br>
	 * <b>XLSX</b>: Read first sheet.
	 * @param file
	 * @return String[][]
	 * @throws Exception
	 */
	public String[][] readFirstSheet(File file) throws Exception;

	/**
	 * <b>XLS</b> : Read first sheet.<br>
	 * <b>XLSX</b>: Read first sheet.
	 * @param stream
	 * @return String[][]
	 * @throws Exception
	 */
	public String[][] readFirstSheet(InputStream stream) throws Exception;

	/**
	 * <b>XLS</b> : Read last sheet.<br>
	 * <b>XLSX</b>: Read last sheet.
	 * @param file
	 * @return String[][]
	 * @throws Exception
	 */
	public String[][] readLastSheet(File file) throws Exception;

	/**
	 * <b>XLS</b> : Read last sheet.<br>
	 * <b>XLSX</b>: Read last sheet.
	 * @param filePath
	 * @return String[][]
	 * @throws Exception
	 */
	public String[][] readLastSheet(String filePath) throws Exception;

	/**
	 * <b>XLS</b> : Read last sheet.<br>
	 * <b>XLSX</b>: Read last sheet.
	 * @param stream
	 * @return String[][]
	 * @throws Exception
	 */
	public String[][] readLastSheet(InputStream stream) throws Exception;

}
