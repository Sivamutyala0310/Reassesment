package com.atmecs.NinjaStore.pageactions;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;


	/**
	* To move the window up and down.( Scroll by visible element) Scroll using
	* pixcel.
	*/
public class PageActionsScrollDown 
{
public static void homePageScrollDown(WebDriver driver) {
		
		JavascriptExecutor javaScriptExecutor = (JavascriptExecutor) driver;
		javaScriptExecutor.executeScript("window.scrollBy(0,1200)");
	}
	
	public static void PageScrollDown(WebDriver driver) {
		
		JavascriptExecutor javaScriptExecutor = (JavascriptExecutor) driver;
		javaScriptExecutor.executeScript("window.scrollBy(0,600)");
	}
	
public static void scrollDownToBottom(WebDriver driver) {
		
		JavascriptExecutor javaScriptExecutor = (JavascriptExecutor) driver;
		javaScriptExecutor.executeScript("window.scrollTo(0, document.body.scrollHeight)");
	}

public static void PageScrollDownUp(WebDriver driver) {
	
	JavascriptExecutor javaScriptExecutor = (JavascriptExecutor) driver;
	javaScriptExecutor.executeScript("window.scrollBy(0,-700)");
}
}
