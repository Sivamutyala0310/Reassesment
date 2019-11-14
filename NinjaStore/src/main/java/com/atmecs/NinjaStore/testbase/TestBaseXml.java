package com.atmecs.NinjaStore.testbase;

import java.util.Properties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.atmecs.NinjaStore.constants.ConstantFilePaths;
import com.atmecs.NinjaStore.reports.LogReports;
import com.atmecs.NinjaStore.utils.ReadLocatorsFile;

	/**
	* Different browser setup(Chrome,FireFox,InternetExplorer) through XML.
	*/
public class TestBaseXml {

	public WebDriver driver;
	LogReports log = new LogReports();
	Properties baseProperty;
	//String url;

	@BeforeTest
	@Parameters("browsername" )
	public void initializeBrowser(String selectBrowser) throws Exception {

		baseProperty = ReadLocatorsFile.loadProperty(ConstantFilePaths.CONFIG_FILE);

	//	url = baseProperty.getProperty("url");

		if (selectBrowser.equalsIgnoreCase("chrome")) {

			System.setProperty("webdriver.chrome.driver", ConstantFilePaths.CHROME_FILE);
			driver = new ChromeDriver();
			log.info("Chrome browser  started");
		}

		else if (selectBrowser.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver", ConstantFilePaths.FIREFOX_FILE);
			driver = new FirefoxDriver();
			log.info("Firefox browser  started");
		}

		else if (selectBrowser.equalsIgnoreCase("IE")) {
			System.setProperty("webdriver.ie.driver", ConstantFilePaths.IE_FILE);
			driver = new InternetExplorerDriver();
			log.info("IE browser  started");
		}

		/*
		 * driver.get(url); driver.manage().window().maximize();
		 * driver.manage().timeouts().pageLoadTimeout(TimeOuts.requiredPageLoadTime,
		 * TimeUnit.MINUTES);
		 * driver.manage().timeouts().implicitlyWait(TimeOuts.requiredImplicitWaitTime,
		 * TimeUnit.SECONDS);
		 */
	}
	@AfterSuite
	public void closeBrowser()
	{
		driver.close();
	}

}
