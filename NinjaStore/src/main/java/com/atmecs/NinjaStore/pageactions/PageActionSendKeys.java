package com.atmecs.NinjaStore.pageactions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.atmecs.NinjaStore.constants.TimeOuts;
import com.atmecs.NinjaStore.reports.LogReports;

/** 
 * 
 *  to pass the  text into editable elements (text bar, text area).
 *
 */

public class PageActionSendKeys 
{
	static LogReports report=new LogReports();
	static String locatorType;
	static String locatorValue;
	public static boolean sendKeys(WebDriver driver, String locatorProperty,String keysToSend) {

		WebDriverWait wait = new WebDriverWait(driver, TimeOuts.requiredWaitingTIME);
		boolean presenceOfElementLocated = true;
		String locator[]=locatorProperty.split(":", 2);
		locatorType = locator[0];
		locatorValue = locator[1];
		try {
			switch (locatorType) {
			case "CLASSNAME":
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(locatorValue)));
				driver.findElement(By.className(locatorValue)).sendKeys(keysToSend);
				break;
			case "CSSSELECTOR":
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(locatorValue)));
				Thread.sleep(3000);
				driver.findElement(By.cssSelector(locatorValue)).sendKeys(keysToSend);
				break;
			case "ID":
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(locatorValue)));
				driver.findElement(By.id(locatorValue)).sendKeys(keysToSend);;
				break;
			case "LINKTEXT":
				wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText(locatorValue)));
				driver.findElement(By.linkText(locatorValue)).sendKeys(keysToSend);;
				break;
			case "NAME":
				wait.until(ExpectedConditions.presenceOfElementLocated(By.name(locatorValue)));
				driver.findElement(By.name(locatorValue)).sendKeys(keysToSend);;
				break;
			case "PARTIALLINKTEXT":
				wait.until(ExpectedConditions.presenceOfElementLocated(By.partialLinkText(locatorValue)));
				driver.findElement(By.partialLinkText(locatorValue)).sendKeys(keysToSend);;
				break;
			case "TAGNAME":
				wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName(locatorValue)));
				driver.findElement(By.tagName(locatorValue)).sendKeys(keysToSend);;
				break;
			case "XPATH":
				wait.until(ExpectedConditions.presenceOfElementLocated((By.xpath(locatorValue))));
				driver.findElement(By.xpath(locatorValue)).sendKeys(keysToSend);;
				break;
			default:
				break;
			}
		} catch (IllegalArgumentException illegalArgumentException) {
			presenceOfElementLocated = false;
			report.info("Locator type doesn't exist" + illegalArgumentException.getCause());
		} catch (NullPointerException nullPointerException) {
			presenceOfElementLocated = false;
			report.info("Locator type doesn't exist " + nullPointerException.getCause());
		} catch (Exception e) {
			presenceOfElementLocated = false;
			report.info("Locator type doesn't exist " + e.getCause());
		}

		return presenceOfElementLocated;

	}
}
