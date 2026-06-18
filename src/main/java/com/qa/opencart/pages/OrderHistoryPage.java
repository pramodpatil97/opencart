package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

import io.qameta.allure.Step;

public class OrderHistoryPage {
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	public OrderHistoryPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	private final By orderHistoryHeader = By.xpath("//h1[text()='Order History']");
	//Account Downloads
	private final By emptyList = By.xpath("//p[text()='You have not made any previous orders!']");
	//https://naveenautomationlabs.com/opencart/index.php?route=account/order
	//	Order History


@Step("Order History page title exist...")
public String getOrderHistoryPageTitle() {
	String title = eleUtil.waitForTitleIs(AppConstants.ORDER_HISTORY_PAGE_TITLE, AppConstants.DEFAULT_SHORT_WAIT);
	System.out.println("Order History page title: "+ title);
	return title;
}
@Step("Order History page URL exist...")
public String getOrderHistoryPageURL() {
	String url = eleUtil.waitForURLContains(AppConstants.ORDER_HISTORY_PAGE_FRACTION_URL, AppConstants.DEFAULT_SHORT_WAIT);
	System.out.println("Order History page url: "+ url);
	return url;
}
@Step("Header exist...")
public boolean isheaderExist() {
	return eleUtil.isElementDisplayed(orderHistoryHeader);
}
@Step("Order History is empty...")
public boolean isOrderHistoryEmpty() {
	return eleUtil.isElementDisplayed(emptyList);
}
}
