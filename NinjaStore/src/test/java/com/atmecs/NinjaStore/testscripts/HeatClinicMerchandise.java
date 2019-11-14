package com.atmecs.NinjaStore.testscripts;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.atmecs.NinjaStore.constants.ConstantFilePaths;
import com.atmecs.NinjaStore.constants.TimeOuts;
import com.atmecs.NinjaStore.helperpages.HeatClinicHelper;
import com.atmecs.NinjaStore.pageactions.PageActionSendKeys;
import com.atmecs.NinjaStore.pageactions.PageActions;
import com.atmecs.NinjaStore.pageactions.PageActionsClick;
import com.atmecs.NinjaStore.pageactions.PageActionsFindElement;
import com.atmecs.NinjaStore.pageactions.PageActionsScrollDown;
import com.atmecs.NinjaStore.reports.LogReports;
import com.atmecs.NinjaStore.testbase.TestBase;
import com.atmecs.NinjaStore.utils.ExcelFileReader;
import com.atmecs.NinjaStore.utils.ReadLocatorsFile;
import com.atmecs.NinjaStore.utils.TestDataProvider;
import com.atmecs.NinjaStore.waits.Waits;
import com.microsoft.sqlserver.jdbc.SQLServerDriver;

public class HeatClinicMerchandise extends TestBase
{
	LogReports log = new LogReports();
	String browserUrl;
	Properties properties;
	WebElement element;
	String productPersonalizedName;
	String productQuantity;
	String HotSaucesvalidationData;
	String MerchandiseValidationData;
	String ClearanceValidationData;
	String NewToHotSauceValidationData;
	String FAQMenuValidationData;
	String MensValidationData;
	String ProductNameInTheCart;
	String ProductSizeInTheCart;
	String ProductPersonalizedNameInTheCart;
	String ProductColourInTheCart;
	
	@BeforeClass
	public void launchingUrl() throws IOException {
	browserUrl = ReadLocatorsFile.getData(ConstantFilePaths.CONFIG_FILE,"url1");     
	driver.get(browserUrl);                                                    // launching url
	driver.manage().window().maximize();
	driver.manage().timeouts().pageLoadTimeout(TimeOuts.requiredPageLoadTime, TimeUnit.MINUTES);
	driver.manage().timeouts().implicitlyWait(TimeOuts.requiredImplicitWaitTime, TimeUnit.SECONDS);
	}
	@BeforeTest
	public void connectToDatabase()
		{ 
		Connection con=null;
		Statement stmt=null;
		ResultSet res=null;
			try
			{
				DriverManager.registerDriver(new SQLServerDriver());
				System.out.println("Driver loaded");
			}
			catch(SQLException e)
			{
				System.out.println(" driver loading problem");
			}
			try
			{
				 con=DriverManager.getConnection("jdbc:sqlserver://ATMECSINLT-044\\SQLEXPRESS;database=Student;integratedSecurity=true");
				System.out.println("connection established");
			}
			catch (Exception d)
			{
				System.out.println("connection problem");
				d.printStackTrace();
			}
			
			try
			{
				stmt=con.createStatement();
				res=  stmt.executeQuery("select * from ProjectData");
				System.out.println("query executed successfully");
			}
			catch(SQLException f)
			{
				System.out.println("query couldnt be executed");
				f.printStackTrace();
			}
			try
			{
				while(res.next())
				{
					 productPersonalizedName=res.getString(1);
					 productQuantity=res.getString(2);
					 HotSaucesvalidationData=res.getString(3);
					 MerchandiseValidationData=res.getString(4);
					 ClearanceValidationData=res.getString(5);
					 NewToHotSauceValidationData=res.getString(6);
					 FAQMenuValidationData=res.getString(7);
					 MensValidationData=res.getString(8);
					 ProductNameInTheCart=res.getString(9);
					 ProductSizeInTheCart=res.getString(10);
					 ProductPersonalizedNameInTheCart=res.getString(11);
					 ProductColourInTheCart=res.getString(12);
					
					
					System.out.println(productPersonalizedName+" "+productQuantity+" "+HotSaucesvalidationData);
				}
			}
			catch(Exception f)
			{
				System.out.println("result not found");
				f.printStackTrace();
			}

		}

/**
 * providing test data from excel to data provider
 */
	@Test(dataProvider = "validationdata", dataProviderClass = TestDataProvider.class)
	public void validateMenuFields(String expectedHeaderText,String expectedMercahntHeaderText,String expectedClearanceHeaderText,String expectedNewToHotSaucesHeaderText,String expectedFAQHeaderText,String expectedMenHeaderText,String expectedCartProductName,String expectedProductSize,String expectedPersonalizedName,
			String expectedColourOfProduct,String expectedTotalPriceOfCart) throws IOException, Exception
	{
		properties = ReadLocatorsFile.loadProperty(ConstantFilePaths.LOCATORS_FILE);
		ExcelFileReader readExcelData = new ExcelFileReader(ConstantFilePaths.TESTDATA_FILE1);
		
		HeatClinicHelper helper=new HeatClinicHelper(driver);
		helper.urlDetails();                                   
		
		helper.performPageAction(driver,properties.getProperty("loc.clickHotSaucesMenu"),properties.getProperty("loc.getMenusText"), expectedHeaderText, "validated HotSauces menu page");	
		helper.performPageAction(driver,properties.getProperty("loc.clickMerchandiseMenu"),properties.getProperty("loc.getMenusText"),expectedMercahntHeaderText, "validated Merchant menu page");     
		helper.performPageAction(driver,properties.getProperty("loc.clickclearanceMenu"),properties.getProperty("loc.getMenusText"),  expectedClearanceHeaderText, "validated Clearance  menu page");
		helper.performPageAction(driver, properties.getProperty("loc.clickNewHotSauceMenu"),properties.getProperty("loc.getNewHotSauceMenuText"),  expectedNewToHotSaucesHeaderText, "validated New To Hot Sauces menu page");
		helper.performPageAction(driver, properties.getProperty("loc.clickFAQMenu"),properties.getProperty("loc.getNewHotSauceMenuText"),expectedFAQHeaderText, "validated FAQ menu page");      
       
       element= PageActionsFindElement.findWebElement(driver,  properties.getProperty("loc.clickMerchandiseMenu"));
       PageActions.mouseOverElement(driver, element);
       helper.performPageAction(driver, properties.getProperty("loc.selectMenslink"),properties.getProperty("loc.getMenusText"),expectedMenHeaderText, "validated mens menu page");

       PageActionsScrollDown.PageScrollDown(driver);
       
       PageActionsClick.clickElement(driver, properties.getProperty("loc.clickBuyNow"));
       PageActionsClick.clickElement(driver, properties.getProperty("loc.selectRedColour"));
       
       PageActionsClick.clickElement(driver, properties.getProperty("loc.selectMediumSize"));
       
       PageActionSendKeys.sendKeys(driver, properties.getProperty("loc.personalizedName"),readExcelData.getData(0, 1, 0));
       
       PageActionsClick.clickElement(driver, properties.getProperty("loc.clickWindowBuyNow"));
       
       PageActionsClick.clickElement(driver, properties.getProperty("loc.clickcart"));
       
       helper.performCartValidation(driver,properties.getProperty("loc.getCartProductName"),expectedCartProductName,"validated product name in the cart");
       helper.performCartValidation(driver,properties.getProperty("loc.getCartProductSize"),expectedProductSize,"validated product size in the cart");
       helper.performCartValidation(driver,properties.getProperty("loc.getPersonalizedName"),expectedPersonalizedName,"validated PersonalizedName of product");
       helper.performCartValidation(driver,properties.getProperty("loc.getProductColour"),expectedColourOfProduct,"validated product name in the cart");
       
       PageActions.clearText(driver, properties.getProperty("loc.sendProductsTocart"));
       PageActionSendKeys.sendKeys(driver, properties.getProperty("loc.sendProductsTocart"),readExcelData.getData(0, 1, 1));
       log.info("adding 10 products to the cart");    
       PageActionsClick.clickElement(driver, properties.getProperty("loc.addProductsToCart"));
       
       Waits.explicitWait(driver, properties.getProperty("loc.getTotalPriceOftheCart"));
       
       helper.performCartValidation(driver,properties.getProperty("loc.getTotalPriceOftheCart"),expectedTotalPriceOfCart,"validated Total price of the products in the cart");
       
       log.info("validated all the product details");
       
}
}
