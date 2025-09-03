package com.qa.opencart.test;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.CsvUtil;
import com.qa.opencart.utils.ExcelUtil;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
@Epic("EP-100: Design the Open Cart App Product Page")
@Feature("F-103: design open cart Product feature")
@Story("US-53: develop Product page core features: Product Headers,Product Images,Product Info")
public class ProductInfoTest extends BaseTest{
	
	//BT(chrome+url) --> BC(login) --> @Test
	
	@BeforeClass
	public void prodInfoSetup() {
		accPage = loginPage.doLogin(prop.getProperty("username"),prop.getProperty("password"));
			}	

	@DataProvider
	public Object[][] getProducts() {
		return new Object[][] {
			{"macbook", "MacBook Pro"},
			{"samsung", "Samsung SyncMaster 941BW"},
			{"canon", "Canon EOS 5D"}
		};
	}
	
	@DataProvider
	public Object[][] getProductTestData() {
		return  ExcelUtil.getTestData("product");
	}
	
	@DataProvider
	public Object[][] getProCSVData() {
		return CsvUtil.csvData("product");
	}
	
	
	@Description("Product Header Test....")
	@Owner("Pramod Patil")
	@Severity(SeverityLevel.NORMAL)
	@Test(dataProvider = "getProCSVData")
	public void productHeaderTest(String searchKey, String productName) {
		searchResultsPage = accPage.doSearch(searchKey);
		productInfoPage = searchResultsPage.selectProduct(productName);
		String actHeader = productInfoPage.getProductHeader();
		Assert.assertEquals(actHeader, productName);
	}

	
	@DataProvider
	public Object[][] getProductImages() {
		return new Object[][] {
			{"macbook", "MacBook Pro", 4},
			{"samsung", "Samsung SyncMaster 941BW", 1},
			{"canon", "Canon EOS 5D", 3},
			
		};
	}
	
	@Description("Product Images Count Test....")
	@Owner("Pramod Patil")
	@Severity(SeverityLevel.NORMAL)
	@Test(dataProvider = "getProductImages")
	public void productImagesCountTest(String searchKey, String productName, int imageCount) {
		searchResultsPage = accPage.doSearch(searchKey);
		productInfoPage = searchResultsPage.selectProduct(productName);
		int actImagesCount = productInfoPage.getProductImages();
		Assert.assertEquals(actImagesCount, imageCount);
	}
	@Description("Product Info Test....")
	@Owner("Pramod Patil")
	@Severity(SeverityLevel.NORMAL)
	@Test
	public void productInfoTest() {
		searchResultsPage = accPage.doSearch("macbook");
		productInfoPage = searchResultsPage.selectProduct("MacBook Pro");
		Map<String, String> productDataMap = productInfoPage.getProductData();
		
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.assertEquals(productDataMap.get("productname"), "MacBook Pro");
		
		softAssert.assertEquals(productDataMap.get("Brand"), "Apple");
		softAssert.assertEquals(productDataMap.get("Availability"), "Out Of Stock");
		softAssert.assertEquals(productDataMap.get("Reward Points"), "800");
		softAssert.assertEquals(productDataMap.get("Product Code"), "Product 18");

		softAssert.assertEquals(productDataMap.get("productprice"), "$2,000.00");
		softAssert.assertEquals(productDataMap.get("extaxprice"), "$2,000.00");

		softAssert.assertAll();//7 --> 1 failed
		
	}
	
	//AAA pattern -- Arrange Act Assert
	// we can have multiple soft assertions in a single test case
	//but only one hard assert in the test case
	
	
//	@Test
//	public void productHeaderTest() {
//		searchResultsPage = accPage.doSearch("macbook");
//		productInfoPage = searchResultsPage.selectProduct("MacBook Pro");
//		String actHeader = productInfoPage.getProductHeader();
//		Assert.assertEquals(actHeader, "MacBook Pro");
//	}
//
//	
//	@Test
//	public void productImagesCountTest() {
//		searchResultsPage = accPage.doSearch("samsung");
//		productInfoPage = searchResultsPage.selectProduct("Samsung SyncMaster 941BW");
//		int actImagesCount = productInfoPage.getProductImages();
//		Assert.assertEquals(actImagesCount, 1);
//	}
//	
	
	

}