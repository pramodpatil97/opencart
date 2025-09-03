package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

import io.qameta.allure.Step;

public class YourStorePage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;

	public YourStorePage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	private final By myAccounts= By.xpath("//span[text()='My Account']");
	private final By login= By.xpath("(//a[text()='Login'])[1]");
	
	@Step("YourStorePage Title exist...")
	public String getYourStorePageTitle() {
		String title = eleUtil.waitForTitleIs(AppConstants.YOURSTORE_PAGE_TITLE, AppConstants.DEFAULT_SHORT_WAIT);
		System.out.println("YourStore page title: "+ title);
		return title;
	}
	@Step("YourStorePage URL exist...")
	public String getYourStorePageURL() {
		String url = eleUtil.waitForURLContains(AppConstants.YOURSTORE_PAGE_FRACTION_URL, AppConstants.DEFAULT_SHORT_WAIT);
		System.out.println("YourStore page url: "+ url);
		return url;
	}
	@Step("Go to Login page...")
	public LoginPage goToLoginPage() {
		eleUtil.doClick(myAccounts);
		eleUtil.clickElementWhenReady(login,AppConstants.DEFAULT_SHORT_WAIT );
		return new LoginPage(driver);
	}

}
