package com.qa.opencart.test;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;


@Epic("EP-100: Design the Open Cart App Login Page")
@Feature("F-101: design open cart login feature")
@Story("US-50: develop login core features: title, url, user is able to login")

public class LoginPageNegativeTest extends BaseTest {
	
	
	
	@DataProvider
	public Object[][] getNegativeLoginData() {
		return new Object[][] {
			{"testselelettttt@gmail.com", "test@123"},
			{"march2024@open.com", "test@123"},
			{"march2024@@open.com", "test@@123"},
			{"", "test@123"},
			{"", ""}
		};
	}
	
	@Description("login page negative test....")
	@Owner("Pramod Patil")
	@Severity(SeverityLevel.MINOR)
	@Test(dataProvider = "getNegativeLoginData")
	public void negativeLoginTest(String invalidUN, String invalidPWD) {
		Assert.assertTrue(loginPage.doLoginWithInvalidCredentails(invalidUN, invalidPWD));
	}

}