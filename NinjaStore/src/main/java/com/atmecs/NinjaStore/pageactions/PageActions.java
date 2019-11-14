package com.atmecs.NinjaStore.pageactions;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import com.atmecs.NinjaStore.waits.Waits;

public class PageActions {
	static WebElement element;
	static String text;
	static Waits waits = new Waits();

	/**
	 * Handling keyboard event(Enter at the required location)
	 * 
	 */
	public static void performActionEnter(WebDriver driver) {
		Actions action = new Actions(driver);
		action.sendKeys(Keys.ENTER).build().perform();
	}

	/**
	 * Handling mouse event(Clicks at the current mouse location). Click button or
	 * whatever
	 */
	public static void mouseOverElement(WebDriver driver, WebElement element) {
		Actions action = new Actions(driver);
		action.moveToElement(element).build().perform();
	}

	/**
	 * Handling Alert if the web page creates alerts while executing the script and
	 * it will get alert text also.
	 */
	public static String handlingAlert(WebDriver driver) {
		text = driver.switchTo().alert().getText();
		return text;
	}

	/**
	 * Navigating to the previous page.
	 */

	public static void navigateBack(WebDriver driver) {
		driver.navigate().back();
	}

	/**
	 * handling all the windows in the web page
	 * 
	 * @param driver
	 */
	public static void handlingWindows(WebDriver driver) {
		for (String handle : driver.getWindowHandles()) {

			try {

				driver.switchTo().window(handle);
			} catch (NoSuchWindowException e) {

				System.out.println("an exception while handling windows");
			}
		}
	}
	public static void clearText(WebDriver driver,String locatorProperty)
	{
		String locator[]=locatorProperty.split(":", 2);
		String locatorValue = locator[1];
		driver.findElement(By.cssSelector(locatorValue)).clear();
		
	}

}
