package com.atmecs.NinjaStore.utils;

import org.testng.annotations.DataProvider;

import com.atmecs.NinjaStore.constants.ConstantFilePaths;

/**
 * 
 * able to provide entire data to the test script which is getting from excel file
 *
 */

public class TestDataProvider 
{
	@DataProvider(name = "testdata")
	public Object[][] testData() {
		ExcelFileReader file = new ExcelFileReader(ConstantFilePaths.VALIDATIONTESTDATA_FILE);
		int sheetIndex = 0;
		int rowCount = file.totalRowsinSheet(sheetIndex);
		int colCount = file.totalColsinSheet(sheetIndex);
		
		Object[][] data = new Object[rowCount][colCount];
		for (int rowIndex = 0; rowIndex < rowCount; rowIndex++) {
			for (int colIndex = 0; colIndex < colCount; colIndex++) {

				data[rowIndex][colIndex] = file.getData(sheetIndex, rowIndex + 1, colIndex);
			}
		}
		return data;
	}
	@DataProvider(name = "validationdata")
	public Object[][] validationData() {
		ExcelFileReader file = new ExcelFileReader(ConstantFilePaths.VALIDATIONTESTDATA_FILE1);
		int sheetIndex = 0;
		int rowCount = file.totalRowsinSheet(sheetIndex);
		int colCount = file.totalColsinSheet(sheetIndex);
		
		Object[][] data = new Object[rowCount][colCount];
		for (int rowIndex = 0; rowIndex < rowCount; rowIndex++) {
			for (int colIndex = 0; colIndex < colCount; colIndex++) {

				data[rowIndex][colIndex] = file.getData(sheetIndex, rowIndex + 1, colIndex);
			}
		}
		return data;
	}
}
