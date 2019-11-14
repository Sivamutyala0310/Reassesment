package com.atmecs.NinjaStore.validatetest;

import org.openqa.selenium.WebElement;

import com.atmecs.NinjaStore.reports.LogReports;

	public class ValidatePageActions {
		static LogReports log = new LogReports();
	
		public static void isEnabled(WebElement element) {
			element.isEnabled();
			log.info("yes the element is enabled to perform any action");
		}
	
		public static void isDisplayed(WebElement element) {
			element.isDisplayed();
			log.info("yes the element is displayed to perform any action");
		}
	
		public static void isSelected(WebElement element) {
			element.isSelected();
			log.info("yes the element is selected");
		}
	}
