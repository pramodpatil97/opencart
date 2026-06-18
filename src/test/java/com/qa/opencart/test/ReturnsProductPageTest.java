package com.qa.opencart.test;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.pages.AccountsPage;

import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;

public class ReturnsProductPageTest extends BaseTest {
	@BeforeClass
	public void wishPageSetup() {
		accPage = loginPage.doLogin(prop.getProperty("username"),prop.getProperty("password"));
}
	@Description("Reurns Page link test....")
	@Owner("Pramod Patil")
	@Severity(SeverityLevel.MINOR)
	@Test
	public void isReturnsLinkExist() {
		Assert.assertTrue(accPage.isReturnsLinkExist());
	}
	@Description("Returns Page Test....")
	@Owner("Pramod Patil")
	@Severity(SeverityLevel.CRITICAL)
	@Test
	public void navigateReturnsPage() {
		returnsPage = accPage.navigateToReturns();
	}
	@Description("Returns Page Title Test....")
	@Owner("Pramod Patil")
	@Severity(SeverityLevel.MINOR)
	@Test(dependsOnMethods = "navigateReturnsPage")
	public void returnsPageTitle() {			
		String actTitle = returnsPage.getReturnsPageTitle();
		Assert.assertEquals(actTitle, AppConstants.Returns_PAGE_TITLE);
	}	
	@Description("Returns Page URL Test....")
	@Owner("Pramod Patil")
	@Severity(SeverityLevel.MINOR)
	@Test(dependsOnMethods = "navigateReturnsPage")
	public void orderHistoryTestURL() {
		String actURL = returnsPage.getReturnsPageURL();
		Assert.assertTrue(actURL.contains(AppConstants.Returns_PAGE_FRACTION_URL));
	}
	@Description("Returns page header test....")
	@Owner("Pramod Patil")
	@Severity(SeverityLevel.MINOR)
	@Test(dependsOnMethods = "navigateReturnsPage")
	public void isheaderExist() {
		Assert.assertTrue(returnsPage.isheaderExist());
}
	@Description("Returns page empty test....")
	@Owner("Pramod Patil")
	@Severity(SeverityLevel.MINOR)
	@Test(dependsOnMethods = "navigateReturnsPage")
	public void isReturnsProductPageEmpty() {
		Assert.assertTrue(returnsPage.isReturnsPageEmpty(),"Expected Products Returns page to be empty, but it is not.");
}
	
}
