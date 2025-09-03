package com.qa.opencart.test;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
@Epic("EP-100: Design the Open Cart App Search Page")
@Feature("F-102: design open cart Search feature")
@Story("US-52: develop Search page core features: Search products")
public class SearchTest extends BaseTest {

	// BT(chrome+url) -> BC(login) --> @Test

	@BeforeClass
	public void searchSetup() {
		accPage = loginPage.doLogin(prop.getProperty("username"),prop.getProperty("password"));
	}
	@Description("Search Test....")
	@Owner("Pramod Patil")
	@Severity(SeverityLevel.CRITICAL)
	@Test
	public void searchTest() {
		searchResultsPage = accPage.doSearch("macbook");
		productInfoPage = searchResultsPage.selectProduct("MacBook Pro");
		String actHeader = productInfoPage.getProductHeader();
		Assert.assertEquals(actHeader, "MacBook Pro");
	}

	
	
	
	
	

}
