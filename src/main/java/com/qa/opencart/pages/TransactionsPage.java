package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

import io.qameta.allure.Step;

public class TransactionsPage {

	private WebDriver driver;
	private ElementUtil eleUtil;
	public TransactionsPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	private final By transactionsPageHeader = By.xpath("//h1[text()='Your Transactions']");
	//Account Downloads
	private final By curentBalance = By.xpath("//p[text()='Your current balance is: ']/b");
	
	@Step("Transactions page title exist...")
	public String getTransactionsPageTitle() {
		String title = eleUtil.waitForTitleIs(AppConstants.Transactions_PAGE_TITLE, AppConstants.DEFAULT_SHORT_WAIT);
		System.out.println("Transactions page title: "+ title);
		return title;
	}
	@Step("Transactions page URL exist...")
	public String getTransactionsPageURL() {
		String url = eleUtil.waitForURLContains(AppConstants.Transactions_PAGE_FRACTION_URL, AppConstants.DEFAULT_SHORT_WAIT);
		System.out.println("Transactions page url: "+ url);
		return url;
	}
	@Step("Header exist...")
	public boolean isheaderExist() {
		return eleUtil.isElementDisplayed(transactionsPageHeader);
	}
	@Step("Transactions Page Header balance details")
	public  String getCurrentBalance() {
		String currentbalance= eleUtil.GetText(curentBalance);
		System.out.println("Your current balance is: "+ currentbalance);
		return currentbalance;
	}

}
