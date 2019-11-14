package com.atmecs.NinjaStore.pageactions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import com.atmecs.NinjaStore.reports.LogReports;

/**
 * Easy to choose or select an option given under any drop down by visibility.
 */

public class PageActionsDropDown {
	static LogReports report = new LogReports();
	static String locatorType;
	static String locatorValue;

	public static boolean selectDropDown(WebDriver driver, String locatorProperty, String dropDownvalue) {

		boolean isSelected = true;
		String locator[]=locatorProperty.split(":", 2);
		locatorType = locator[0];
		locatorValue = locator[1];
		Select select;

		try {
			switch (locatorType) {
			case "CLASSNAME":
				select = new Select(driver.findElement(By.className(locatorValue)));
				select.selectByVisibleText(dropDownvalue);
				;
				break;
			case "CSSSELECTOR":
				select = new Select(driver.findElement(By.cssSelector(locatorValue)));
				select.selectByVisibleText(dropDownvalue);
				break;
			case "ID":
				select = new Select(driver.findElement(By.id(locatorValue)));
				select.selectByVisibleText(dropDownvalue);
				;
				break;
			case "LINKTEXT":
				select = new Select(driver.findElement(By.linkText(locatorValue)));
				select.selectByVisibleText(dropDownvalue);
				;
				break;
			case "NAME":
				select = new Select(driver.findElement(By.name(locatorValue)));
				select.selectByVisibleText(dropDownvalue);
				;
				break;
			case "PARTIALLINKTEXT":
				select = new Select(driver.findElement(By.partialLinkText(locatorValue)));
				select.selectByVisibleText(dropDownvalue);
				;
				break;
			case "TAGNAME":
				select = new Select(driver.findElement(By.tagName(locatorValue)));
				select.selectByVisibleText(dropDownvalue);
				;
				break;
			case "XPATH":
				select = new Select(driver.findElement(By.xpath(locatorValue)));
				select.selectByVisibleText(dropDownvalue);
				break;
			default:
				break;
			}
		} catch (IllegalArgumentException illegalArgumentException) {
			isSelected = false;
			report.info("Locator type doesn't exist" + illegalArgumentException.getCause());
		} catch (NullPointerException nullPointerException) {
			isSelected = false;
			report.info("Locator type doesn't exist " + nullPointerException.getCause());
		} catch (Exception e) {
			isSelected = false;
		}

		return isSelected;

	}

}
