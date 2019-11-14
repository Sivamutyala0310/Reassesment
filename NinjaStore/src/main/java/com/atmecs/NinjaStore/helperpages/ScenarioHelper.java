package com.atmecs.NinjaStore.helperpages;

import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;

import com.atmecs.NinjaStore.constants.ConstantFilePaths;
import com.atmecs.NinjaStore.pageactions.PageActionsClick;
import com.atmecs.NinjaStore.pageactions.PageActionsGetText;
import com.atmecs.NinjaStore.reports.LogReports;
import com.atmecs.NinjaStore.utils.ReadLocatorsFile;
import com.atmecs.NinjaStore.validatetest.ValidateResult;
import com.atmecs.NinjaStore.waits.Waits;

public class ScenarioHelper {
	public WebDriver driver;
	Properties properties;
	LogReports log = new LogReports();
	String locatorProperty;
	String actualTextTitle;

	public ScenarioHelper(WebDriver driver) {
		this.driver = driver;
	}

	public void validatingProductDetails(String expectedProductAvailability, String macBookPrice,
			String expectedMacProductTax, String macBookDescription, String expectedTotalAvailability,
			String expectedCartValueAfterRemoving) throws IOException {
		properties = ReadLocatorsFile.loadProperty(ConstantFilePaths.LOCATORS_FILE);

		PageActionsClick.clickElement(driver, properties.getProperty("loc.clickProduct"));
		String actualAvailability = PageActionsGetText.fetchAttributeText(driver,
				properties.getProperty("loc.getStockAvailability"));
		ValidateResult.validateData(actualAvailability, expectedProductAvailability,
				"validated availability of product");

		String actualProductPrice = PageActionsGetText.fetchAttributeText(driver,
				properties.getProperty("loc.getProductPrice"));
		ValidateResult.validateData(actualProductPrice, macBookPrice, "validated product price");

		String actualProductTax = PageActionsGetText.fetchAttributeText(driver,
				properties.getProperty("loc.getProductEtax"));
		ValidateResult.validateData(actualProductTax, expectedMacProductTax, "validated product tax");

		String actualProductDescription = PageActionsGetText.fetchAttributeText(driver,
				properties.getProperty("loc.getMacBookProductDescription"));
		ValidateResult.validateData(actualProductDescription, macBookDescription,
				"validated macbook air product description");

		String actualCartTotal = PageActionsGetText.fetchAttributeText(driver,
				properties.getProperty("loc.checkCartTotal"));
		ValidateResult.validateData(actualCartTotal, expectedTotalAvailability,
				"validated product cartTotal after adding all the products");

		log.info("YES ALL THE 5 ADDED PRODUCTS THERE IN THE CART" + " " + actualCartTotal);
		log.info("GRAND TOTAL OF THE CART VALIDATED" + " " + actualCartTotal);

		PageActionsClick.clickElement(driver, properties.getProperty("loc.clickOnCartTotal"));
		log.info("clicking on  cart total");

		PageActionsClick.clickElement(driver, properties.getProperty("loc.removeFromCart"));
		log.info("after removing on product from   cart total");
		Waits.explicitWait(driver, properties.getProperty("loc.checkCartTotal"));
		String cartTotalAfterDeleting = PageActionsGetText.fetchAttributeText(driver,
				properties.getProperty("loc.checkCartTotal"));
		ValidateResult.validateData(cartTotalAfterDeleting, expectedCartValueAfterRemoving,
				"validated product cartTotal");

	}
}
