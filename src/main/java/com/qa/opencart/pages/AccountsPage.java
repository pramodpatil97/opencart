package com.qa.opencart.pages;

import java.util.ArrayList;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

import io.qameta.allure.Step;

public class AccountsPage {

	private WebDriver driver;
	private ElementUtil eleUtil;

	public AccountsPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}

	private final By headers = By.cssSelector("div#content h2");
	private final By logoutLink = By.linkText("Logout");
	private final By search = By.name("search");
	private final By searchIcon = By.cssSelector("div#search button");
	private final By wishList = By.linkText("Wish List");
	private final By addressBook = By.linkText("Address Book");
	private final By orderHistory = By.linkText("Order History");
	private final By rewardPoints = By.linkText("Reward Points");
	private final By returns = By.linkText("Returns");
	private final By transactions = By.linkText("Transactions");
	
	private static final Logger log = LogManager.getLogger(LoginPage.class); 

	public List<String> getAccPageHeaders() {

		List<WebElement> headersList = eleUtil.waitForElementsPresence(headers, AppConstants.DEFAULT_SHORT_WAIT);
		System.out.println("total number of headers: " + headersList.size());
		List<String> headersValList = new ArrayList<String>();
		for (WebElement e : headersList) {
			String text = e.getText();
			headersValList.add(text);
		}
		return headersValList;
	}
	@Step("Logout link exist...")
	public boolean isLogoutLinkExist() {
		WebElement logoutEle = eleUtil.waitForElementVisible(logoutLink, AppConstants.DEFAULT_MEDIUM_WAIT);
		return eleUtil.isElementDisplayed(logoutEle);
	}
	@Step("WishList link exist...")
	public boolean isWishListLinkExist() {
		WebElement WishListLink = eleUtil.waitForElementVisible(wishList, AppConstants.DEFAULT_MEDIUM_WAIT);
		return eleUtil.isElementDisplayed(WishListLink);
	}
	@Step("OrderHistory link exist...")
	public boolean isOrderHistoryLinkExist() {
		WebElement OrderHistoryLink = eleUtil.waitForElementVisible(orderHistory, AppConstants.DEFAULT_MEDIUM_WAIT);
		return eleUtil.isElementDisplayed(OrderHistoryLink);
	}
	@Step("Returns link exist...")
	public boolean isReturnsLinkExist() {
		WebElement ReturnsLink = eleUtil.waitForElementVisible(returns, AppConstants.DEFAULT_MEDIUM_WAIT);
		return eleUtil.isElementDisplayed(ReturnsLink);
	}
	@Step("Transactions link exist...")
	public boolean isTransactionsLinkExist() {
		WebElement TransactionsLink = eleUtil.waitForElementVisible(transactions, AppConstants.DEFAULT_MEDIUM_WAIT);
		return eleUtil.isElementDisplayed(TransactionsLink);
	}
	@Step("do search with searchKey: {0}")
	public SearchResultsPage doSearch(String searchKey) {
		System.out.println("search key -->"+ searchKey);
		WebElement searchEle = eleUtil.waitForElementVisible(search, AppConstants.DEFAULT_MEDIUM_WAIT);
		searchEle.clear();
		searchEle.sendKeys(searchKey);
		eleUtil.doClick(searchIcon);
		return new SearchResultsPage(driver);
		
	}
	@Step("Navigate to MyWishList...")
	public MyWishListPage navigateToMyWishList() {
		log.info("trying to navigating to wishList page...");
		eleUtil.waitForElementVisible(wishList, AppConstants.DEFAULT_SHORT_WAIT).click();
		return new MyWishListPage(driver);
	}
	@Step("Navigate to Logout...")
	public AccountLogoutPage navigateToLogout() {
		eleUtil.doClick(logoutLink);
		return new AccountLogoutPage(driver);
	}
	@Step("Navigate to AddressBook...")
	public AddressBookPage navigateToAddressBook() {
		log.info("trying to navigating to AddressBookPage...");
		eleUtil.waitForElementVisible(addressBook, AppConstants.DEFAULT_SHORT_WAIT).click();
		return new AddressBookPage(driver);
	}
	@Step("Navigate to OrderHistoryPage...")
	public OrderHistoryPage navigateToOrderHistory() {
		log.info("trying to navigating to OrderHistoryPage...");
		eleUtil.waitForElementVisible(orderHistory, AppConstants.DEFAULT_SHORT_WAIT).click();
		return new OrderHistoryPage(driver);
	}
	@Step("Navigate to RewardPointsPage...")
	public RewardPointsPage navigateToRewardPoints() {
		log.info("trying to navigating to RewardPointsPage...");
		eleUtil.waitForElementVisible(rewardPoints, AppConstants.DEFAULT_SHORT_WAIT).click();
		return new RewardPointsPage(driver);
	}
	@Step("Navigate to ReturnsPage...")
	public ReturnsPage navigateToReturns() {
		log.info("trying to navigating to ReturnsPage...");
		eleUtil.waitForElementVisible(returns, AppConstants.DEFAULT_SHORT_WAIT).click();
		return new ReturnsPage(driver);
	}
	@Step("Navigate to TransactionsPage...")
	public TransactionsPage navigateToTransactions() {
		log.info("trying to navigating to TransactionsPage...");
		eleUtil.waitForElementVisible(transactions, AppConstants.DEFAULT_SHORT_WAIT).click();
		return new TransactionsPage(driver);
	}
}
	
	
	
	
	