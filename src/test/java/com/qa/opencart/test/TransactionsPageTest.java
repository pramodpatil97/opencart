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

public class TransactionsPageTest extends BaseTest {
	@BeforeClass
	public void wishPageSetup() {
		accPage = loginPage.doLogin(prop.getProperty("username"),prop.getProperty("password"));
}
	@Description("Transactions Page link test....")
	@Owner("Pramod Patil")
	@Severity(SeverityLevel.MINOR)
	@Test
	public void isTransactionsLinkExist() {
		Assert.assertTrue(accPage.isTransactionsLinkExist());
	}
	@Description("Transactions Page Test....")
	@Owner("Pramod Patil")
	@Severity(SeverityLevel.CRITICAL)
	@Test
	public void navigateTransactionsPage() {
		transactionsPage = accPage.navigateToTransactions();
	}
	@Description("Transactions Page Title Test....")
	@Owner("Pramod Patil")
	@Severity(SeverityLevel.MINOR)
	@Test(dependsOnMethods = "navigateTransactionsPage")
	public void getTransactionsPageTitle() {			
		String actTitle = transactionsPage.getTransactionsPageTitle();
		Assert.assertEquals(actTitle, AppConstants.Transactions_PAGE_TITLE);
	}	
	@Description("Transactions Page URL Test....")
	@Owner("Pramod Patil")
	@Severity(SeverityLevel.MINOR)
	@Test(dependsOnMethods = "navigateTransactionsPage")
	public void getTransactionsPageURL() {
		String actURL = transactionsPage.getTransactionsPageURL();
		Assert.assertTrue(actURL.contains(AppConstants.Transactions_PAGE_FRACTION_URL));
	}
	@Description("Transactions page header test....")
	@Owner("Pramod Patil")
	@Severity(SeverityLevel.MINOR)
	@Test(dependsOnMethods = "navigateTransactionsPage")
	public void isheaderExist() {
		Assert.assertTrue(transactionsPage.isheaderExist());
}
	@Description("Transactions page current balance check test....")
	@Owner("Pramod Patil")
	@Severity(SeverityLevel.MINOR)
	@Test(dependsOnMethods = "navigateTransactionsPage")
	public void verifyCurrentBalance() {
		 String actualBalance = transactionsPage.getCurrentBalance();
		    Assert.assertEquals(actualBalance, "$1.00", "Balance is not matching");
		}
}
	
