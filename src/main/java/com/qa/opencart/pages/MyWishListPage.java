package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

import io.qameta.allure.Step;

public class MyWishListPage {
	private WebDriver driver;
	private ElementUtil eleUtil;

	public MyWishListPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	
	private final By wishListHeader = By.xpath("//h2[text()='My Wish List']");
	private final By wishListInfo = By.xpath("//p[text()='Your wish list is empty.']");
	private final By wishListCont = By.xpath("//a[text()='Continue']");
	//https://naveenautomationlabs.com/opencart/index.php?route=account/wishlist
	
	@Step("Wishlist page title exist...")
	public String getWishListPageTitle() {
		String title = eleUtil.waitForTitleIs(AppConstants.WISHLIST_PAGE_TITLE, AppConstants.DEFAULT_SHORT_WAIT);
		System.out.println("Wishlist page title: "+ title);
		return title;
	}
	@Step("Wishlist page URL exist...")
	public String getWishListPageURL() {
		String url = eleUtil.waitForURLContains(AppConstants.WISHLIST_PAGE_FRACTION_URL, AppConstants.DEFAULT_SHORT_WAIT);
		System.out.println("Wishlist page url: "+ url);
		return url;
	}
	@Step("Header exist...")
	public boolean isheaderExist() {
		return eleUtil.isElementDisplayed(wishListHeader);
	}
	@Step("Click on continue btn...")
	public AccountsPage doContinue() {
		eleUtil.doClick(wishListCont);
		return new AccountsPage(driver);
	}
}
