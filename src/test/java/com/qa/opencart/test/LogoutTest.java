package com.qa.opencart.test;

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
@Epic("EP-100: Design the Open Cart App Logout Page")
@Feature("F-105: design open cart Logout feature")
@Story("US-55: develop Logout page core features: Logout link,LogOutTestURL,LogOutTestTitle,Exit page and login page")
public class LogoutTest extends BaseTest {
	// BT(chrome+url) -> BC(login) --> @Test

		@BeforeClass
		public void logoutSetup() {
			accPage = loginPage.doLogin(prop.getProperty("username"),prop.getProperty("password"));
		}
		@Description("Navigate to logout Test....")
		@Owner("Pramod Patil")
		@Severity(SeverityLevel.MINOR)
		@Test
		public void LogOutTest() {		
			accLogout = accPage.navigateToLogout();
		}
		@Description("LogOutTestTitle Test....")
		@Owner("Pramod Patil")
		@Severity(SeverityLevel.MINOR)
		@Test
		public void LogOutTestTitle() {			
			String actTitle = accLogout.getLogoutPageTitle();
			Assert.assertEquals(actTitle, AppConstants.LOGOUT_PAGE_TITLE);
		}	
		@Description("LogOutTestURL Test....")
		@Owner("Pramod Patil")
		@Severity(SeverityLevel.MINOR)
		@Test
		public void LogOutTestURL() {
			String actURL = accLogout.getLogoutPageURL();
			Assert.assertTrue(actURL.contains(AppConstants.LOGOUT_PAGE_FRACTION_URL));
		}
		@Description("ExitPageTest Test....")
		@Owner("Pramod Patil")
		@Severity(SeverityLevel.MINOR)
		@Test(dependsOnMethods = "LogOutTest")
		public void ExitPageTest() {	
			urStore = accLogout.doLogout();
		}
		@Description("ExitPageTestTitle Test....")
		@Owner("Pramod Patil")
		@Severity(SeverityLevel.MINOR)
		@Test(dependsOnMethods = "ExitPageTest")
		public void ExitPageTestTitle() {
			String actTitle = urStore.getYourStorePageTitle();
			Assert.assertEquals(actTitle, AppConstants.YOURSTORE_PAGE_TITLE);
		}
		@Description("ExitPageTestURL Test....")
		@Owner("Pramod Patil")
		@Severity(SeverityLevel.MINOR)
		@Test(dependsOnMethods = "ExitPageTest")
		public void ExitPageTestURL() {
			String actURL = urStore.getYourStorePageURL();
			Assert.assertTrue(actURL.contains(AppConstants.YOURSTORE_PAGE_FRACTION_URL));	
		}	
		@Description("LoginPageTest....")
		@Owner("Pramod Patil")
		@Severity(SeverityLevel.MINOR)
		@Test(dependsOnMethods = "ExitPageTest")
		public void LoginPageTest() {
		    loginPage= urStore.goToLoginPage();
		}
		@Description("loginPageTitleTest....")
		@Owner("Pramod Patil")
		@Severity(SeverityLevel.MINOR)
		@Test(dependsOnMethods = "LoginPageTest")
		public void loginPageTitleTest() {
		    String actTitle = loginPage.getLoginPageTitle();
			Assert.assertEquals(actTitle, AppConstants.LOGIN_PAGE_TITLE);
		}
		@Description("loginPageURLTest....")
		@Owner("Pramod Patil")
		@Severity(SeverityLevel.MINOR)
		@Test(dependsOnMethods = "LoginPageTest")
		public void loginPageURLTest() {
		    String actURL = loginPage.getLoginPageURL();
			Assert.assertTrue(actURL.contains(AppConstants.LOGIN_PAGE_FRACTION_URL));
		}


}
