package com.atmecs.NinjaStore.testscripts;

import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.atmecs.NinjaStore.constants.ConstantFilePaths;
import com.atmecs.NinjaStore.constants.TimeOuts;
import com.atmecs.NinjaStore.helperpages.ScenarioHelper;
import com.atmecs.NinjaStore.pageactions.PageActionSendKeys;
import com.atmecs.NinjaStore.pageactions.PageActions;
import com.atmecs.NinjaStore.pageactions.PageActionsClick;
import com.atmecs.NinjaStore.pageactions.PageActionsGetText;
import com.atmecs.NinjaStore.pageactions.PageActionsScrollDown;
import com.atmecs.NinjaStore.reports.LogReports;
import com.atmecs.NinjaStore.testbase.TestBase;
import com.atmecs.NinjaStore.utils.ExcelFileReader;
import com.atmecs.NinjaStore.utils.ReadLocatorsFile;
import com.atmecs.NinjaStore.utils.TestDataProvider;
import com.atmecs.NinjaStore.validatetest.ValidateResult;

	public class NinjaStoreRetailApplication extends TestBase {
		LogReports log = new LogReports();
		String browserUrl;
		Properties properties;
		
	/**
	 * launching browser url	
	 * @throws IOException
	 */
		@BeforeClass
		public void launchingUrl() throws IOException {
		browserUrl = ReadLocatorsFile.getData(ConstantFilePaths.CONFIG_FILE,"url");
		driver.get(browserUrl);
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(TimeOuts.requiredPageLoadTime, TimeUnit.MINUTES);
		driver.manage().timeouts().implicitlyWait(TimeOuts.requiredImplicitWaitTime, TimeUnit.SECONDS);
		}
		
	/**
	 * 
	 * providing test data from excel to data provider
	 */
		
		@Test(dataProvider = "testdata", dataProviderClass = TestDataProvider.class)
		public void testcase1(String homePageTitle ,String expectedProductAvailability,String expectedProductPrice,String expectedProductTax,String expectedIphoneDescription,String  expectedTotalAvailability,
				String macBookPrice,String macBookDescription,String expectedCartValueAfterRemoving,String macBookTax) throws IOException, Exception
		{
		properties = ReadLocatorsFile.loadProperty(ConstantFilePaths.LOCATORS_FILE);
		ExcelFileReader readExcelData = new ExcelFileReader(ConstantFilePaths.TESTDATA_FILE);

		// Creating the JavascriptExecutor interface object by Type casting
		JavascriptExecutor execute = (JavascriptExecutor) driver;

		// Fetching the Domain Name of the site.
		String DomainName = execute.executeScript("return document.domain;").toString();
		log.info("Domain name of the site = " + DomainName);

		// Fetching the URL of the site.
		String url = execute.executeScript("return document.URL;").toString(); // Tostring() change object to name.
		log.info("URL of the site = " + url);

		// Method document.title fetch the Title name of the site.
		String TitleName = execute.executeScript("return document.title;").toString();
		log.info("Title of the page = " + TitleName);

	      String homePageText= PageActionsGetText.fetchAttributeText(driver, properties.getProperty("loc.getHomepageText"));
	      ValidateResult.validateData(homePageText, homePageTitle, "validating Homepage");
	     
	      PageActionSendKeys.sendKeys(driver, properties.getProperty("loc.sendSearchText"),readExcelData.getData(0, 1, 0));
	      PageActions.performActionEnter(driver);
	 
	      PageActionsScrollDown.PageScrollDown(driver);
	      PageActionsClick.clickElement(driver, properties.getProperty("loc.clickAddtoCart"));
	      
	      PageActions.clearText(driver, properties.getProperty("loc.sendRetext"));
	      PageActionSendKeys.sendKeys(driver, properties.getProperty("loc.sendRetext"),readExcelData.getData(0, 1, 0));
	      PageActionsScrollDown.PageScrollDown(driver);
	      PageActionsClick.clickElement(driver, properties.getProperty("loc.clickAddtoCart"));
	      log.info("2 IPHONES ARE SUCCESSFULLY ADDED TO THE CART");
	      
	      PageActionsClick.clickElement(driver, properties.getProperty("loc.clickProduct"));
	      String actualAvailability=PageActionsGetText.fetchAttributeText(driver,properties.getProperty("loc.getStockAvailability"));
	      ValidateResult.validateData(actualAvailability, expectedProductAvailability, "validated availability of product");
	      
		
		  String actualProductPrice= PageActionsGetText.fetchAttributeText(driver,  properties.getProperty("loc.getProductPrice"));
		  ValidateResult.validateData(actualProductPrice, expectedProductPrice, "validated product price");
		 
	      String actualProductTax=  PageActionsGetText.fetchAttributeText(driver, properties.getProperty("loc.getProductEtax"));
	      ValidateResult.validateData(actualProductTax, expectedProductTax, "validated product tax");
	      
	      String actualProductDescription=  PageActionsGetText.fetchAttributeText(driver, properties.getProperty("loc.getIphoneProductDescription"));
	      ValidateResult.validateData(actualProductDescription, expectedIphoneDescription, "validated I phone product description");
	      
		  PageActions.clearText(driver,properties.getProperty("loc.sendRetext"));
		  
	      for(int click=0;click<3;click++)
	      {
	      PageActionSendKeys.sendKeys(driver,properties.getProperty("loc.sendRetext"),readExcelData.getData(0, 1, 1));
		  PageActions.performActionEnter(driver);
	      PageActionsScrollDown.PageScrollDown(driver);
	      PageActionsClick.clickElement(driver, properties.getProperty("loc.clickAddtoCart"));
	      PageActions.clearText(driver, properties.getProperty("loc.sendRetext"));
	      }
	      log.info("3 MACBOOK AIR ARE SUCCESSFULLY ADDED TO THE CART");
	      PageActionsScrollDown.PageScrollDown(driver);
		  ScenarioHelper helper=new ScenarioHelper(driver);
		  helper.validatingProductDetails(expectedProductAvailability,macBookPrice,macBookTax, macBookDescription ,expectedTotalAvailability,expectedCartValueAfterRemoving);
		  log.info("SUCCESSFULLY VALIDATED PRODUCT DETAILS");
		 
		}
	}
