package com.atmecs.NinjaStore.helperpages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import com.atmecs.NinjaStore.pageactions.PageActionsClick;
import com.atmecs.NinjaStore.pageactions.PageActionsGetText;
import com.atmecs.NinjaStore.reports.LogReports;
import com.atmecs.NinjaStore.validatetest.ValidateResult;

public class HeatClinicHelper {
	public WebDriver driver;
	static LogReports log=new LogReports();
	
	
	public HeatClinicHelper(WebDriver driver) {
		this.driver = driver;
	}
	 public  void urlDetails()
	 {
		// Creating the JavascriptExecutor interface object by Type casting
				JavascriptExecutor execute = (JavascriptExecutor) driver;
	
				// Fetching the Domain Name of the site.
				String DomainName = execute.executeScript("return document.domain;").toString();
				log.info("Domain name of the site = " + DomainName);
	
				// Fetching the URL of the site.
				String url = execute.executeScript("return document.URL;").toString(); // Tostring() change object to name.
				log.info("URL of the site = " + url);	
				
	 }
	 public void performPageAction(WebDriver driver,String clickElementLocator,String getTextLocator,String expectedValidationData,String validationMessage)
	 {
		   PageActionsClick.clickElement(driver,clickElementLocator);
	       String actualHeaderText=PageActionsGetText.fetchAttributeText(driver,getTextLocator);
	       ValidateResult.validateData(actualHeaderText, expectedValidationData,validationMessage);
	 }
	 
	 public void performCartValidation(WebDriver driver,String getTextLocator,String expectedProduct,String validationMessage)
	 {
	  String actualCartProduct=PageActionsGetText.fetchAttributeText(driver,getTextLocator);
      ValidateResult.validateData(actualCartProduct, expectedProduct,validationMessage);
	 }
}
