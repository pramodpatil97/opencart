package com.qa.opencart.test;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.CsvUtil;
import com.qa.opencart.utils.ExcelUtil;
import com.qa.opencart.utils.StringUtils;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
@Epic("EP-100: Design the Open Cart App Register Page")
@Feature("F-102: design open cart Register feature")
@Story("US-51: develop Register page core features: User Registration info")
public class RegisterPageTest extends BaseTest{
	
	//BT(chrome+login url) --> BC(move to register page) --> @Test
	
	@BeforeClass
	public void goToRegisterPage() {
		registerPage = loginPage.navigateToRegisterPage();
	}

	@DataProvider
	public Object[][] getRegData() {
		return new Object[][] {
			{"harpreet", "automation", "987878787777", "harpreet@123", "yes" },
			{"ratul", "shaha", "987878787766", "ratul@123", "no" },
			{"sandhya", "automation", "987878787755", "sandhya@123", "yes" },
		};
	}
	
	
	@DataProvider
	public Object[][] getRegSheetData() {
		return ExcelUtil.getTestData("register");
	}
	
	
	@DataProvider
	public Object[][] getRegCSVData() {
		return CsvUtil.csvData("register");
	}
	
	@Description("User Registration Test....")
	@Owner("Pramod Patil")
	@Severity(SeverityLevel.CRITICAL)
	@Test(dataProvider = "getRegCSVData")
	public void registerTest(String firstName, String lastName, String telephone, String password, String subscribe) {
		Assert.assertTrue
		(registerPage.userRegister(firstName, lastName, StringUtils.getRandomEMail(), telephone, password, subscribe));
	}
	

}