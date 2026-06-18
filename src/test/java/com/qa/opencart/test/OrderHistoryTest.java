package com.qa.opencart.test;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;

import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;

public class OrderHistoryTest extends BaseTest {
	@BeforeClass
	public void wishPageSetup() {
		accPage = loginPage.doLogin(prop.getProperty("username"),prop.getProperty("password"));
}
	@Description("Order History link test....")
	@Owner("Pramod Patil")
	@Severity(SeverityLevel.MINOR)
	@Test
	public void isOrderHistoryLinkExist() {
		Assert.assertTrue(accPage.isOrderHistoryLinkExist());
	}
	@Description("Order History Test....")
	@Owner("Pramod Patil")
	@Severity(SeverityLevel.CRITICAL)
	@Test
	public void navigateToOrderHistory() {
		orderHistoryPage = accPage.navigateToOrderHistory();	
	}
	@Description("Order History Title Test....")
	@Owner("Pramod Patil")
	@Severity(SeverityLevel.MINOR)
	@Test(dependsOnMethods = "navigateToOrderHistory")
	public void orderHistoryTestTitle() {			
		String actTitle = orderHistoryPage.getOrderHistoryPageTitle();
		Assert.assertEquals(actTitle, AppConstants.ORDER_HISTORY_PAGE_TITLE);
	}	
	@Description("Order History URL Test....")
	@Owner("Pramod Patil")
	@Severity(SeverityLevel.MINOR)
	@Test(dependsOnMethods = "navigateToOrderHistory")
	public void orderHistoryTestURL() {
		String actURL = orderHistoryPage.getOrderHistoryPageURL();
		Assert.assertTrue(actURL.contains(AppConstants.ORDER_HISTORY_PAGE_FRACTION_URL));
	}
	@Description("Order history page header test....")
	@Owner("Pramod Patil")
	@Severity(SeverityLevel.MINOR)
	@Test(dependsOnMethods = "navigateToOrderHistory")
	public void isOrderHistoryHeaderExistTest() {
		Assert.assertTrue(orderHistoryPage.isheaderExist());
}
	@Description("Order history empty test....")
	@Owner("Pramod Patil")
	@Severity(SeverityLevel.MINOR)
	@Test(dependsOnMethods = "isOrderHistoryHeaderExistTest")
	public void isOrderHistoryEmptyTest() {
		Assert.assertTrue(orderHistoryPage.isOrderHistoryEmpty(),"Expected order history to be empty, but it is not.");
}
	
}
