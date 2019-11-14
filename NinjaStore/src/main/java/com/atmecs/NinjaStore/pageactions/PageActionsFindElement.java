package com.atmecs.NinjaStore.pageactions;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.atmecs.NinjaStore.reports.LogReports;

	
public class PageActionsFindElement {
	static WebElement element;
	static LogReports report=new LogReports();
	static List<WebElement> listValue;
	static String locatorType;
	static String locatorValue;
	
	/**
	 * 
	 *   web elements are uniquely identifiable using certain properties such as ID or name.
	 *
	 */
	public static WebElement findWebElement(WebDriver driver, String locatorProperty) {
		String locator[]=locatorProperty.split(":", 2);
		locatorType = locator[0];
		locatorValue = locator[1];

		try {
			switch (locatorType) {
			case "CLASSNAME":
				element = driver.findElement(By.className(locatorValue));
				break;
			case "CSSSELECTOR":
				element = driver.findElement(By.cssSelector(locatorValue));
				break;
			case "ID":
				element = driver.findElement(By.id(locatorValue));
				break;
			case "LINKTEXT":
				element = driver.findElement(By.linkText(locatorValue));
				break;
			case "NAME":
				element = driver.findElement(By.name(locatorValue));
				break;
			case "PARTIALLINKTEXT":
				element = driver.findElement(By.partialLinkText(locatorValue));
				break;
			case "TAGNAME":
				element = driver.findElement(By.tagName(locatorValue));
				break;
			case "XPATH":
				element = driver.findElement(By.xpath(locatorValue));
				break;
			default:
				break;
			}
		} catch (IllegalArgumentException illegalArgumentException) {
			report.info("Locator type doesn't exist" + illegalArgumentException.getCause());
		} catch (NullPointerException nullPointerException) {
			report.info("Locator type doesn't exist " + nullPointerException.getCause());
		} catch (Exception exception) {
			report.info("Locator type doesn't exist " + exception.getCause());
		}
		return element;

	}
	
	/**
	 * web elements are uniquely identifiable using certain properties such as ID or name and
	 * it returns the list of all matching elements.
	 *
	 */
	
	public static List<WebElement> findElementsMethod(WebDriver driver, String locatorProperty) {
		String locator[]=locatorProperty.split(":", 2);
		locatorType = locator[0];
		locatorValue = locator[1];
		try {
			switch (locatorType) {
			case "CLASSNAME":
				listValue = driver.findElements(By.className(locatorValue));
				break;
			case "CSSSELECTOR":
				listValue = driver.findElements(By.cssSelector(locatorValue));
				break;
			case "ID":
				listValue = driver.findElements(By.id(locatorValue));
				break;
			case "LINKTEXT":
				listValue = driver.findElements(By.linkText(locatorValue));
				break;
			case "NAME":
				listValue = driver.findElements(By.name(locatorValue));
				break;
			case "PARTIALLINKTEXT":
				listValue = driver.findElements(By.partialLinkText(locatorValue));
				break;
			case "TAGNAME":
				listValue = driver.findElements(By.tagName(locatorValue));
				break;
			case "XPATH":
				listValue = driver.findElements(By.xpath(locatorValue));
				break;
			default:
				break;
			}
		} catch (IllegalArgumentException illegalArgumentException) {
			report.info("Locator type doesn't exist" + illegalArgumentException.getCause());
		} catch (NullPointerException nullPointerException) {
			report.info("Locator type doesn't exist " + nullPointerException.getCause());
		} catch (Exception exception) {
			report.info("Locator type doesn't exist " + exception.getCause());

		}
		return listValue;
	}

}
