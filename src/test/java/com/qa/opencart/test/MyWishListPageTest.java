package com.qa.opencart.test;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.pages.LoginPage;

import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;

public class MyWishListPageTest extends BaseTest {
	@BeforeClass
	public void wishPageSetup() {
		accPage = loginPage.doLogin(prop.getProperty("username"),prop.getProperty("password"));
}
	@Description("Wishlist link test....")
	@Owner("Pramod Patil")
	@Severity(SeverityLevel.MINOR)
	@Test
	public void isWishListLinkExistTest() {
		Assert.assertTrue(accPage.isWishListLinkExist());
	}
	@Description("Search Test....")
	@Owner("Pramod Patil")
	@Severity(SeverityLevel.CRITICAL)
	@Test
	public void navigateToWishList() {
		wishPage = accPage.navigateToMyWishList();	
	}
	@Description("WishListTestTitle Test....")
	@Owner("Pramod Patil")
	@Severity(SeverityLevel.MINOR)
	@Test(dependsOnMethods = "navigateToWishList")
	public void wishListTestTitle() {			
		String actTitle = wishPage.getWishListPageTitle();
		Assert.assertEquals(actTitle, AppConstants.WISHLIST_PAGE_TITLE);
	}	
	@Description("WishListTestURL Test....")
	@Owner("Pramod Patil")
	@Severity(SeverityLevel.MINOR)
	@Test(dependsOnMethods = "navigateToWishList")
	public void wishListTestURL() {
		String actURL = wishPage.getWishListPageURL();
		Assert.assertTrue(actURL.contains(AppConstants.WISHLIST_PAGE_FRACTION_URL));
	}
}