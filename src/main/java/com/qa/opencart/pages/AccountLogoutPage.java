package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

import io.qameta.allure.Step;

public class AccountLogoutPage {
		
		private WebDriver driver;
		private ElementUtil eleUtil;

		public AccountLogoutPage(WebDriver driver) {
			this.driver = driver;
			eleUtil = new ElementUtil(driver);
		}
		private final By logoutHeader = By.tagName("h1");
		private final By logoutBtn=By.xpath("//a[text()='Continue']");
		
		@Step("Logout page title exist...")
		public String getLogoutPageTitle() {
			String title = eleUtil.waitForTitleIs(AppConstants.LOGOUT_PAGE_TITLE, AppConstants.DEFAULT_SHORT_WAIT);
			System.out.println("logout page title: "+ title);
			return title;
		}
		@Step("Logout page URL exist...")
		public String getLogoutPageURL() {
			String url = eleUtil.waitForURLContains(AppConstants.LOGOUT_PAGE_FRACTION_URL, AppConstants.DEFAULT_SHORT_WAIT);
			System.out.println("logout page url: "+ url);
			return url;
		}
		@Step("Header exist...")
		public boolean isheaderExist() {
			return eleUtil.isElementDisplayed(logoutHeader);
		}
		@Step("Click on logout btn...")
		public YourStorePage doLogout() {
			eleUtil.doClick(logoutBtn);
			return new YourStorePage(driver);
		}
}


