package com.qa.opencart.test;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
@Epic("EP-100: Design the Open Cart App Account Page")
@Feature("F-104: design open cart Accounts page feature")
@Story("US-54: develop Accounts page core features: Logout link, Account page headers")
public class AccountsPageTest extends BaseTest {
	//  BT --> BC -> @Test	
	
	@BeforeClass
	public void accPageSetup() {
		accPage = loginPage.doLogin(prop.getProperty("username"),prop.getProperty("password"));
	}
	@Description("Logout link test....")
	@Owner("Pramod Patil")
	@Severity(SeverityLevel.MINOR)
	@Test
	public void isLogoutLinkExistTest() {
		Assert.assertTrue(accPage.isLogoutLinkExist());
	}
	@Description("Account page headers test....")
	@Owner("Pramod Patil")
	@Severity(SeverityLevel.MINOR)
	@Test
	public void accPageHeadersTest() {
		List<String> actHeadersList = accPage.getAccPageHeaders();
		Assert.assertEquals(actHeadersList.size(), AppConstants.ACC_PAGE_HEADERS_COUNT);
		Assert.assertEquals(actHeadersList, AppConstants.expectedAccPageHeadersList);
	}
	
}