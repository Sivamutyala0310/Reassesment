package com.atmecs.NinjaStore.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
	
	/**
	 * interacting with excel file and reads the entire cell data from it.
	 * 
	 *
	 */

public class ExcelFileReader
{
	static XSSFWorkbook workbook;
	static XSSFSheet sheet1;

	public ExcelFileReader(String filePath) {
		try {
			File file = new File(filePath);
			FileInputStream fileInput = new FileInputStream(file);
			try {
				workbook = new XSSFWorkbook(fileInput);
			} catch (IOException e) {
				e.printStackTrace();
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public  String getData(int index, int rowNum, int cellNum) {

		sheet1 = workbook.getSheetAt(index);
		String data = sheet1.getRow(rowNum).getCell(cellNum).getStringCellValue();
		return data;
	}
	public int totalRowsinSheet(int sheet) {
		int rowCount = workbook.getSheetAt(0).getLastRowNum();
	
		return rowCount;
	}
	
	public int totalColsinSheet(int sheetIndex) {
		int colCount = workbook.getSheetAt(sheetIndex).getRow(1).getLastCellNum();
		return colCount;
	}
	/*
	 * public static void main(String[] args) { ExcelFileReader read= new
	 * ExcelFileReader(ConstantFilePaths.TESTDATA_FILE); read.totalRowsinSheet(0);
	 * 
	 * }
	 */
}

	