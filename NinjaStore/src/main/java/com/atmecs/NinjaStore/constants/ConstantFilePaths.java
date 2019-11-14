package com.atmecs.NinjaStore.constants;

import java.io.File;

	/** 
	 * all the required paths of the files are mentioned here.
	 * 
	 *
	 */
public class ConstantFilePaths 
{
	public final static String homeDir = System.getProperty("user.dir") + File.separator;
	public final static String driverPath = homeDir + "lib" + File.separator;
	public final static String resource_Home = homeDir + "src\\test\\resources" + File.separator ;
	
	public final static String CHROME_FILE = driverPath + "chromedriver.exe";
	
	public final static String FIREFOX_FILE = driverPath + "geckodriver.exe";
	
	public final static String IE_FILE      = driverPath + "IEDriverServer.exe";

	public final static String CONFIG_FILE = homeDir  + "config.properties";
	
	public final static String LOG4J_FILE = resource_Home +"log4j" + File.separator+ "log4j.properties";
	
	
	public final static String LOCATORS_FILE = resource_Home +"locators" +File.separator+"project_locators.properties";


	public final static String TESTDATA_FILE = resource_Home +"testdata" + File.separator+ "project_testdata.xlsx";

	
	public final static String VALIDATIONTESTDATA_FILE = resource_Home + "testdata"+ File.separator+ "project_validationdata.xlsx";
	
	public final static String VALIDATIONTESTDATA_FILE1 = resource_Home + "testdata"+ File.separator+ "HeatClinic_validationdata.xlsx";
	
	public final static String  TESTDATA_FILE1 = resource_Home + "testdata"+ File.separator+ "HeatClinic_testdata.xlsx";
	
	
}
