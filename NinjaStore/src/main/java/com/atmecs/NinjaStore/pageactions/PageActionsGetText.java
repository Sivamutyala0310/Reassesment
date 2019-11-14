package com.atmecs.NinjaStore.pageactions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.atmecs.NinjaStore.constants.TimeOuts;
import com.atmecs.NinjaStore.reports.LogReports;


/**
 * 
 * able to get text from(text area, text field) 
 *
 */
public class PageActionsGetText {
	static LogReports report = new LogReports();
	static String textvalue;
	static String locatorType;
	static String locatorValue;
	

	public static String fetchAttributeText(WebDriver driver, String locatorProperty) {
		WebDriverWait wait = new WebDriverWait(driver, TimeOuts.requiredWaitingTIME);
		String locator[]=locatorProperty.split(":", 2);
		locatorType = locator[0];
		locatorValue = locator[1];
		try {
			switch (locatorType) {
			case "CLASSNAME":
				textvalue = driver.findElement(By.className(locatorValue)).getText();
				break;
			case "CSSSELECTOR":
				wait.until(ExpectedConditions.presenceOfElementLocated((By.cssSelector(locatorValue))));
				textvalue = driver.findElement(By.cssSelector(locatorValue)).getText();
				break;
			case "ID":
				textvalue = driver.findElement(By.id(locatorValue)).getText();
				break;
			case "LINKTEXT":
				textvalue = driver.findElement(By.linkText(locatorValue)).getText();
				break;
			case "NAME":
				textvalue = driver.findElement(By.name(locatorValue)).getText();
				break;
			case "PARTIALLINKTEXT":
				textvalue = driver.findElement(By.partialLinkText(locatorValue)).getText();
				break;
			case "TAGNAME":
				textvalue = driver.findElement(By.tagName(locatorValue)).getText();
				break;
			case "XPATH":
				wait.until(ExpectedConditions.presenceOfElementLocated((By.xpath(locatorValue))));
				textvalue = driver.findElement(By.xpath(locatorValue)).getText();
				break;
			default:
				break;
			}
		} catch (IllegalArgumentException illegalArgumentException) {
			report.info("Locator type doesn't exist" + illegalArgumentException.getCause());
		} catch (NullPointerException nullPointerException) {
			report.info("Locator type doesn't exist " + nullPointerException.getCause());
		} catch (Exception e) {

		}
		return textvalue;

	}
	
	public static  String getTextFrom(WebDriver driver,String locator)
	{
		String text=driver.findElement(By.cssSelector(locator)).getText();
		return text;
	}

	
}
