package com.qa.opencart.pages;

import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.ElementUtil;

public class NewsLaterPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;

	public NewsLaterPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}

}
