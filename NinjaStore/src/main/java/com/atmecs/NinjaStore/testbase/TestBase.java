package com.atmecs.NinjaStore.testbase;

import java.util.Properties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeTest;

import com.atmecs.NinjaStore.constants.ConstantFilePaths;
import com.atmecs.NinjaStore.reports.LogReports;
import com.atmecs.NinjaStore.utils.ReadLocatorsFile;

	/**
	* Different browser setup(Chrome,Firefox,InternetExplorer).
	*/
public class TestBase 
{
	LogReports log = new LogReports();
	Properties baseProperty;
	String url;
	String browser;
	String browserTypeConnection;
	public WebDriver driver;
	GridTestBase testBase=new GridTestBase();
	
	@BeforeTest
	public void initializeBrowser() throws Exception {
		
		baseProperty = ReadLocatorsFile.loadProperty(ConstantFilePaths.CONFIG_FILE);
		browser = baseProperty.getProperty("browserType");
		browserTypeConnection=baseProperty.getProperty("connectionType");
		
		if(browserTypeConnection.equalsIgnoreCase("normal"))
		{
		if (browser.equalsIgnoreCase("chrome")) 
		{
			System.setProperty("webdriver.chrome.driver", ConstantFilePaths.CHROME_FILE);
			driver = new ChromeDriver();
			log.info("Chrome browser is started...");
		} 
		else if (browser.equalsIgnoreCase("firefox")) 
		{
			System.setProperty("webdriver.gecko.driver", ConstantFilePaths.FIREFOX_FILE);
			driver = new FirefoxDriver();
			log.info("Firefox browser is started...");
		} 
		else if (browser.equalsIgnoreCase("IE")) 
		{
			System.setProperty("webdriver.ie.driver", ConstantFilePaths.IE_FILE);
			driver = new InternetExplorerDriver();
			log.info("IE browser is started...");
		}
		}
		else if(browser.equalsIgnoreCase("gridBrowserConnection"))
		{
			testBase.initializeBrowser();
		}
	}
	
	 @AfterSuite public void quitDriver() { driver.quit(); } 
	 
}
