package com.atmecs.NinjaStore.testbase;

import java.net.URL;
import java.util.Properties;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeTest;

import com.atmecs.NinjaStore.constants.ConstantFilePaths;
import com.atmecs.NinjaStore.reports.LogReports;
import com.atmecs.NinjaStore.utils.ReadLocatorsFile;

	/**
	* Different browser setup(Chrome,FireFox,InternetExplorer) through Grid connection.
	*/
public class GridTestBase 
{
	static LogReports log = new LogReports();
	static Properties baseProperty;
	//static String websiteUrl;
	static String browser;
	public  WebDriver driver;

	@BeforeTest
	public  void  initializeBrowser() throws Exception 
	{
		baseProperty = ReadLocatorsFile.loadProperty(ConstantFilePaths.CONFIG_FILE);
		//websiteUrl = baseProperty.getProperty("url");
		browser = baseProperty.getProperty("browserType");
		if (browser.equalsIgnoreCase("chrome"))
		{
			DesiredCapabilities desiredcapality = DesiredCapabilities.chrome();
			desiredcapality.setPlatform(Platform.WINDOWS);
			desiredcapality.setCapability(CapabilityType.BROWSER_NAME, "chrome");
			URL url = new URL("http://localhost:4444/wd/hub");
			 driver = new RemoteWebDriver(url, desiredcapality);
			log.info("Chrome browser is started...");
		}
		else if (browser.equalsIgnoreCase("firefox")) 
		{	
			DesiredCapabilities desiredcapality = DesiredCapabilities.firefox();
			desiredcapality.setPlatform(Platform.WINDOWS);
			desiredcapality.setCapability(CapabilityType.BROWSER_NAME, "firefox");
			URL url = new URL("http://localhost:4444/wd/hub");
			 driver = new RemoteWebDriver(url, desiredcapality);
			log.info("Firefox browser is started...");
		}
		
		else if(browser.equalsIgnoreCase("IE"))
		{
		
		DesiredCapabilities desiredcapality = DesiredCapabilities.internetExplorer();
		desiredcapality.setPlatform(Platform.WINDOWS);
		desiredcapality.setCapability(CapabilityType.BROWSER_NAME, "internetExplorer");
		URL url = new URL("http://localhost:4444/wd/hub");
		 driver = new RemoteWebDriver(url, desiredcapality);
		log.info("IE browser is started...");
		}
		/*
		 * driver.get(websiteUrl); driver.manage().window().maximize();
		 * driver.manage().timeouts().pageLoadTimeout(TimeOuts.requiredPageLoadTime,
		 * TimeUnit.MINUTES);
		 * driver.manage().timeouts().implicitlyWait(TimeOuts.requiredImplicitWaitTime,
		 * TimeUnit.SECONDS);
		 */
	}
	@AfterSuite
	public void endTest() {
		driver.quit();
	}

}