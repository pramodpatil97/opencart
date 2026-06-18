package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

import io.qameta.allure.Step;

public class ReturnsPage {
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	public ReturnsPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	private final By returnsHeader = By.xpath("//h1[text()='Product Returns']");
	//Account Downloads
	private final By emptyList = By.xpath("//p[text()='You have not made any previous returns!']");
	


@Step("Returns page title exist...")
public String getReturnsPageTitle() {
	String title = eleUtil.waitForTitleIs(AppConstants.Returns_PAGE_TITLE, AppConstants.DEFAULT_SHORT_WAIT);
	System.out.println("Returns page title: "+ title);
	return title;
}
@Step("Returns page URL exist...")
public String getReturnsPageURL() {
	String url = eleUtil.waitForURLContains(AppConstants.Returns_PAGE_FRACTION_URL, AppConstants.DEFAULT_SHORT_WAIT);
	System.out.println("Returns page url: "+ url);
	return url;
}
@Step("Header exist...")
public boolean isheaderExist() {
	return eleUtil.isElementDisplayed(returnsHeader);
}
@Step("Returns Page is empty...")
public boolean isReturnsPageEmpty() {
	return eleUtil.isElementDisplayed(emptyList);
}
}
