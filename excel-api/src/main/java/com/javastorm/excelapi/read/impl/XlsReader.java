package com.javastorm.excelapi.read.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import com.javastorm.excelapi.read.spi.Reader;

/**
 * This class is intended for providing the functions for reading a xls file.
 *  
 * @author Hemant Kumar
 * @version 1.0 Dated: 11/03/2013
 */
public class XlsReader implements Reader 
{
	@Override
	public String[][] read(File file) throws Exception {
		return readFirstSheet(file);
	}

	@Override
	public String[][] read(String filepath) throws Exception {
		return readFirstSheet(filepath);
	}

	@Override
	public String[][] read(InputStream stream) throws Exception {
		return readFirstSheet(stream);
	}

	@Override
	public String[][] read(File file, char ch) throws Exception {
		throw new Exception("Not Supported");
	}

	@Override
	public String[][] read(String filepath, char ch) throws Exception { 
		throw new Exception("Not Supported");
	}

	@Override
	public String[][] read(InputStream stream, char ch) throws Exception { 
		throw new Exception("Not Supported");
	}

	@Override
	public String[][] read(File file, char delimiter, char rowdelimiter) throws Exception {
		throw new Exception("Not Supported");
	}

	@Override
	public String[][] read(String filepath, char delimiter, char rowdelimiter) throws Exception {
		throw new Exception("Not Supported");
	}

	@Override
	public String[][] read(InputStream stream, char delimiter, char rowdelimiter) throws Exception {
		throw new Exception("Not Supported");
	}
	
	@Override
	public Map<String,String[][]> readAllSheets(File file) throws Exception { 
		return readAllSheets(new FileInputStream(file));	
	}

	@Override
	public Map<String,String[][]> readAllSheets(String filePath) throws Exception {
		return readAllSheets(new FileInputStream(filePath));
	}

	@Override
	public Map<String,String[][]> readAllSheets(InputStream stream) throws Exception {
		HSSFWorkbook workbook = new HSSFWorkbook(stream); 
		int sheetCount = (workbook).getNumberOfSheets();
		Map<String,String[][]> resultMap = new HashMap<String, String[][]>();
		for(int i=0; i<sheetCount; i++) {
			resultMap.put(workbook.getSheetName(i), readSheet(stream, i));
		}
		return resultMap;
	}

	@Override
	public String[][] readSheet(File file, int sheetNo) throws Exception {
		FileInputStream fis = new FileInputStream(file);
		return readSheet(fis, sheetNo);
	}

	@Override
	public String[][] readSheet(String filePath, int sheetNo) throws Exception {
		FileInputStream fis = new FileInputStream(filePath);
		return readSheet(fis, sheetNo);
	}

	@Override
	public String[][] readSheet(InputStream stream, int sheetNo) throws Exception {
		String[][] result = null;
		try {
			HSSFWorkbook workbook = new HSSFWorkbook(stream);
			HSSFSheet sheet = workbook.getSheetAt(sheetNo);
			Iterator<Row> iterator = sheet.iterator();
			ArrayList<Map<Integer,String>> recordList = new ArrayList<Map<Integer,String>>();
			int maxCol = 0;
	        while(iterator.hasNext()){
	            Row row = iterator.next();
	            Iterator<Cell> cellIterator = row.cellIterator();
	            Map<Integer,String> recordMap = new HashMap<Integer,String>();
	            while(cellIterator.hasNext()){
	            	Cell cell = cellIterator.next();
	            	if(cell.getCellType() == Cell.CELL_TYPE_BOOLEAN) {
	            		recordMap.put(cell.getColumnIndex(), String.valueOf(cell.getBooleanCellValue()));
	            	}
	            	else if(cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
	            		if(cell.getNumericCellValue() == (long)cell.getNumericCellValue()) {
	            			recordMap.put(cell.getColumnIndex(), String.valueOf((long)cell.getNumericCellValue()));
	            		}
	            		else {
	            			recordMap.put(cell.getColumnIndex(), String.valueOf(cell.getNumericCellValue()));
	            		}
	            	}
	            	else {
	            		recordMap.put(cell.getColumnIndex(), cell.getStringCellValue());
	            	}
                    if(maxCol < cell.getColumnIndex())
                    	maxCol = cell.getColumnIndex();
	            }
	            recordList.add(recordMap); 
	        }
	        result = new String[recordList.size()][maxCol+1];
	        for(int j = 0; j < recordList.size(); j++) {
	        	Map<Integer,String> recordMap = recordList.get(j);
	        	Iterator<Integer> it= recordMap.keySet().iterator();
	        	while(it.hasNext()) {
	        		int pos = it.next();
	        		result[j][pos] = recordMap.get(pos);
	        	}
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	@Override
	public String[][] readFirstSheet(String filePath) throws Exception {
		return readSheet(filePath, 0);
	}

	@Override
	public String[][] readFirstSheet(File file) throws Exception {
		return readSheet(file, 0);
	}

	@Override
	public String[][] readFirstSheet(InputStream stream) throws Exception {
		return readSheet(stream, 0);
	}

	@Override
	public String[][] readLastSheet(File file) throws Exception {
		return readSheet(file, (new HSSFWorkbook(new FileInputStream(file))).getNumberOfSheets()-1);
	}

	@Override
	public String[][] readLastSheet(String filePath) throws Exception {
		return readSheet(filePath, (new HSSFWorkbook(new FileInputStream(filePath))).getNumberOfSheets()-1);
	}

	@Override
	public String[][] readLastSheet(InputStream stream) throws Exception {
		return readSheet(stream, (new HSSFWorkbook(stream)).getNumberOfSheets()-1);
	}
}
