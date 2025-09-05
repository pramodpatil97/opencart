package com.qa.opencart.utils;

	import java.time.Duration;

	import java.util.ArrayList;
	import java.util.List;
	import java.util.NoSuchElementException;

	import org.apache.logging.log4j.LogManager;
	import org.apache.logging.log4j.Logger;
    import org.openqa.selenium.By;
	import org.openqa.selenium.StaleElementReferenceException;
	import org.openqa.selenium.TimeoutException;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.interactions.Actions;
	import org.openqa.selenium.support.ui.ExpectedConditions;
	import org.openqa.selenium.support.ui.FluentWait;
	import org.openqa.selenium.support.ui.Select;
	import org.openqa.selenium.support.ui.Wait;
	import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.opencart.exceptions.ElementException;
import com.qa.opencart.exceptions.FrameworkException;
import com.qa.opencart.factory.DriverFactory;

import io.qameta.allure.Step;

	public class ElementUtil {
		private WebDriver driver;
		private Actions act;
		private JavaScriptUtil jsUtil;
		private static final Logger log = LogManager.getLogger(ElementUtil.class);
		
		public ElementUtil(WebDriver driver) {
			this.driver=driver;
			act =new Actions(driver);
			jsUtil = new JavaScriptUtil(driver);
		}
		public void doSendKeys(By locator, String value) {
			log.info("entering the value : " + value + " into locator: " + locator);
			if (value == null) {
				log.error("value : " + value + " is null...");
				throw new ElementException("===value can not be null====");
			}
			WebElement ele = getElement(locator);
			ele.clear();
			ele.sendKeys(value);
		}
		
		public void doMultipleSendKeys(By locator, CharSequence... value) {

			getElement(locator).sendKeys(value);
		}
	
		public void doClick (By locator) {
			log.info("clicking on element using : " + locator);
			getElement(locator).click();
		}
		public String GetText (By locator) {
			return getElement(locator).getText();
		}
		public boolean isElementPresent(By locator) {
		    return driver.findElements(locator).size() > 0;
		}

		public boolean isElementCheck(By locator) {
			try {
				return getElement(locator).isDisplayed();
			} catch (NoSuchElementException e) {
				throw new ElementException("===ELEMENT NOT FOUND===");
			}
		}
		@Step("checking the element :{0} is displayed on the page.. ")
		public boolean isElementDisplayed(By locator) {
			try {
				return getElement(locator).isDisplayed();
			} catch (NoSuchElementException e) {
				System.out.println("Element is not displayed on the page: " + locator);
				return false;
			}
		}
			public boolean isElementDisplayed(WebElement element) {
				try {
					return element.isDisplayed();
				} catch (NoSuchElementException e) {
					System.out.println("Element is not displayed on the page: " + element);
					return false;
				}
			}
		
		public int getElementsCount(By locator) {
			return getElements(locator).size();
		}

		public List<WebElement> getElements(By locator) {
			return driver.findElements(locator);
		}
		public String getElementDOMAttributeValue(By locator, String attrName) {
			return getElement(locator).getDomAttribute(attrName);
		}

		public String getElementDOMPropertyValue(By locator, String propName) {
			return getElement(locator).getDomProperty(propName);
		}
//		public WebElement getElement(By locator) {
//		WebElement element = driver.findElement(locator);
//		if (Boolean.parseBoolean(DriverFactory.highlightEle)) {
//			jsUtil.flash(element);
//		}
//		return element;
//	}
		public WebElement getElement(By locator) {
			WebElement element = null;
			try {
				element = driver.findElement(locator);
				log.info("element is found using : " + locator);
				if (Boolean.parseBoolean(DriverFactory.highlightEle)) {
					jsUtil.flash(element);
				}
			} catch (Exception e) {
				FrameworkException fe = new FrameworkException("invalid locator " + " : " + locator);
				log.info("Element not found using " + locator, fe);
			}
			return element;
		}

		public List<String> getElementsTextList(By locator) {
			List<WebElement> eleList = getElements(locator);
			List<String> eleTextList = new ArrayList<String>();// pc=0,vc=10; []
			for (WebElement e : eleList) {
				String text = e.getText();
				if (text.length() != 0) {
					eleTextList.add(text);
				}
			}
			return eleTextList;
		}
		public boolean isElementExist(By locator) {
			if( getElementsCount(locator) == 1) {
				System.out.println("the element : "+ locator +" is present on the page one time");
				return true;
			}
			else {
				System.out.println("the element : "+ locator +" is not present on the page");
				return false;
			}
		}
		
		public boolean isElementExist(By locator, int expectedEleCount) {
			if(getElementsCount(locator) == expectedEleCount) {
				System.out.println("the element : "+ locator +" is present on the page " +expectedEleCount+ " times");
				return true;
			}
			else {
				System.out.println("the element : "+ locator +" is not present on the page " +expectedEleCount+ " times");
				return false;
			}
		}
		public void clickElement(By locator, String eleText) {
			List<WebElement> eleList = getElements(locator);
			System.out.println("total number of elements: "+ eleList.size());
			
			for(WebElement e : eleList) {
				String text = e.getText();
				System.out.println(text);
					if(text.contains(eleText)) {
						e.click();
						break;
					}
			}
		}
	   public void doSearch(By searchLocator, String searchKey, By suggestionsLocator, String suggestionValue) throws InterruptedException {
			
		   doSendKeys(searchLocator,searchKey);
			Thread.sleep(4000);//wait for 4 secs

			List<WebElement> suggList = getElements(suggestionsLocator);
			System.out.println("total number of suggestions: " + suggList.size());

			for (WebElement e : suggList) {
				String text = e.getText();
				System.out.println(text);
				if (text.contains(suggestionValue)) {
					e.click();
					break;
				}
			}
		}
	   //---------Select DropDown-----------//
	   public void doSelectByIndex(By locator, int index) {
			Select select = new Select(getElement(locator));
			select.selectByIndex(index);
		}
	   public void doSelectByVisibleText(By locator, String eleText) {
			Select select = new Select(getElement(locator));
			select.selectByVisibleText(eleText);
		}
		
		public void doSelectByValue(By locator, String value) {
			Select select = new Select(getElement(locator));
			select.selectByValue(value);
		}
		public String getDropDownFirstSelectValue(By locator) {
			Select select = new Select(getElement(locator));
			return select.getFirstSelectedOption().getText();
		}
		public void selectDropDownValue(By locator, String value) {
			List<WebElement> optionsList = driver.findElements(locator);

			System.out.println(optionsList.size());

			for (WebElement e : optionsList) {
				String text = e.getText();
				if (text.contains(value)) {
					e.click();
					break;
				}
			}
		}
		public List<String> getDropDownValuesList(By locator) {
			Select select = new Select(getElement(locator));
			List<WebElement> optionsList = select.getOptions();
			System.out.println("total number of options: " + optionsList.size());

			List<String> optionsValueList = new ArrayList<String>();// pc=0, size=0, []
			for (WebElement e : optionsList) {
				String text = e.getText();
				optionsValueList.add(text);
			}
			return optionsValueList;
		}
		public int getDropDownOptionsCount(By locator) {
			Select select = new Select(getElement(locator));
			return select.getOptions().size();
		}
	    // **************Actions-Util************//
		
		public void moveToElement(By locator) {
			act.moveToElement(getElement(locator)).perform();
		}
		
		public  void menuSubMenuHandling2(By parentMenu,By childMenu ) throws InterruptedException {
			moveToElement(parentMenu);
			Thread.sleep(3000);
			doClick(childMenu);
		
		}
	    public  void MultiMenuSubMenuHandling3(By menu1,By menu2,By menu3,String actionType ) throws InterruptedException {
			
			if(actionType.equalsIgnoreCase("Click")) {
				doClick(menu1);
			}
			else if(actionType.equalsIgnoreCase("mouseHour")){
				moveToElement(menu1);
			}
			Thread.sleep(2000);
			moveToElement(menu2);
			Thread.sleep(2000);
			doClick(menu3);
			}

		public  void MultiMenuSubMenuHandling4(By menu1,By menu2,By menu3,By menu4,String actionType ) throws InterruptedException {
			
			if(actionType.equalsIgnoreCase("Click")) {
				doClick(menu1);
			}
			else if(actionType.equalsIgnoreCase("mouseHour")){
				moveToElement(menu1);
			}
			Thread.sleep(2000);
			moveToElement(menu2);
			Thread.sleep(2000);
			moveToElement(menu3);
			Thread.sleep(2000);
			doClick(menu4);
	}
		public  void doActionsSendKeys(By locator, String value) {
			Actions act = new Actions(driver);
			act.sendKeys(getElement(locator), value).perform();
		}
		
		public void doActionsClick(By locator) {
			Actions act = new Actions(driver);
			act.click(getElement(locator)).perform();
		}
		public  void doSendKeysWithPause(By locator, String value, long pauseTime) {
			Actions act = new Actions(driver);
			
			char val[] = value.toCharArray();
			for(char ch : val) {
				act
					.sendKeys(getElement(locator), String.valueOf(ch))
						.pause(pauseTime)
							.perform();
			}
			
		}

		public void doSendKeysWithPause(By locator, String value) {

			if (value == null) {
				throw new RuntimeException("===value can not be null");
			}

			char val[] = value.toCharArray();
			for (char ch : val) {
				act.sendKeys(getElement(locator), String.valueOf(ch)).pause(200).perform();
			}

		}
		// **************Wait-Util************//
		/**
		 * An expectation for checking that an element is present on the DOM of a page.
		 * This does not necessarily mean that the element is visible.
		 * 
		 * @param locator
		 * @param timeout
		 * @return
		 */
		public WebElement waitForElementPresence(By locator, int timeout) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
			WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
			if (Boolean.parseBoolean(DriverFactory.highlightEle)) {
				jsUtil.flash(element);
			}
			return element;
		}

		/**
		 * An expectation for checking that an element is present on the DOM of a page
		 * and visible. Visibility means that the element is not only displayed but also
		 * has a height and width that is greater than 0.
		 * 
		 * @param locator
		 * @param timeout
		 * @return
		 */
		@Step("waiting for element :{0} visible within the timeout: {1}")
		public WebElement waitForElementVisible(By locator, int timeout) {
			log.info("waiting for element using By locator: " + locator + " within time out: " + timeout);
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
			WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
			if (Boolean.parseBoolean(DriverFactory.highlightEle)) {
				jsUtil.flash(element);
			}
			return element;
		}
		/**
		 * An expectation for checking that there is at least one element present on a web page.
		 * @param locator
		 * @param timeout
		 * @return
		 */
		public List<WebElement> waitForElementsPresence(By locator, int timeout) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
			return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
			
		}
		
		/**
		 * An expectation for checking that all elements present on the web page that match the locator are visible. 
		 * Visibility means that the elements are not only displayed but also have a height and width that is greater than 0.
		 * @param locator
		 * @param timeout
		 * @return
		 */
		public List<WebElement> waitForElementsVisible(By locator, int timeout) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
			return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
			
		}
		/**
		 * An expectation for checking an element is visible and enabled such that you
		 * can click it.
		 * 
		 * @param locator
		 * @param timeout
		 */
		public void clickElementWhenReady(By locator, int timeout) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
			wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
		}

		public boolean waitForFrame(By frameLocator, int timeout) {

			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));

			try {
				wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameLocator));
				return true;
			} catch (TimeoutException e) {
				System.out.println("frame is not present on the page");
				return false;
			}

		}

		public boolean waitForFrame(int frameIndex, int timeout) {

			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));

			try {
				wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameIndex));
				return true;
			} catch (TimeoutException e) {
				System.out.println("frame is not present on the page");
				return false;
			}

		}

		public boolean waitForFrame(String frameNameOrID, int timeout) {

			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));

			try {
				wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameNameOrID));
				return true;
			} catch (TimeoutException e) {
				System.out.println("frame is not present on the page");
				return false;
			}

		}
		//**********Alert wait************//
		
		  public String waitForTitleContains(String fractionTitleValue, int timeout) {
			  	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
			  	try
			  	{
			  		wait.until(ExpectedConditions.titleContains(fractionTitleValue));
				} catch (TimeoutException e) {
					System.out.println("expected title value : "+ fractionTitleValue + " is not present");
				}
				return driver.getTitle();
			  	}
		  @Step("waiting for page title with expected value: {0}" )
			    public String waitForTitleIs(String expectedTitleValue, int timeout) {
					WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));

					try {
						wait.until(ExpectedConditions.titleIs(expectedTitleValue));
					} catch (TimeoutException e) {
						System.out.println("expected title value : "+ expectedTitleValue + " is not present");
					}

					return driver.getTitle();
				}
				
			//**********URL wait************//
		  @Step("waiting for page url with expected fraction value: {0}" )
				public String waitForURLContains(String fractionURLValue, int timeout) {
					WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));

					try {
						wait.until(ExpectedConditions.urlContains(fractionURLValue));
					} catch (TimeoutException e) {
						System.out.println("expected URL value : "+ fractionURLValue + " is not present");
					}

					return driver.getCurrentUrl();
				}
				
				
				public String waitForURLIs(String epxectedURLValue, int timeout) {
					WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));

					try {
						wait.until(ExpectedConditions.urlToBe(epxectedURLValue));
					} catch (TimeoutException e) {
						System.out.println("expected URL value : "+ epxectedURLValue + " is not present");
					}

					return driver.getCurrentUrl();
		
				}
				public boolean waitForWindow(int epxectedNoOfWindows, int timeout) {
					WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));

					try {
						return wait.until(ExpectedConditions.numberOfWindowsToBe(epxectedNoOfWindows));
					} catch (TimeoutException e) {
						System.out.println("expected URL value : "+ epxectedNoOfWindows + " is not present");
					}

					return false;
				}
				////*********Fluent Wait***********//
				public WebElement waitForElementVisibleWithFluentWait(By locator, int timeout, int pollingtime) {
					Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
							.withTimeout(Duration.ofSeconds(timeout))//10
							.pollingEvery(Duration.ofMillis(pollingtime))//2
							.ignoring(NoSuchElementException.class)
							.ignoring(StaleElementReferenceException.class)
							.withMessage("=====ELEMENT NOT VISIBLE ON THE PAGE====");
					
					return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
				}
				public WebElement waitForElementPresenceWithFluentWait(By locator, int timeout, int pollingtime) {
					Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
							.withTimeout(Duration.ofSeconds(timeout))//10
							.pollingEvery(Duration.ofMillis(pollingtime))//2
							.ignoring(NoSuchElementException.class)
							.ignoring(StaleElementReferenceException.class)
							.withMessage("=====ELEMENT NOT PRESENT ON THE PAGE====");
					
					return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
				}
				public String doElementGetText(By locator) {
					return getElement(locator).getText();
				}
		
					
		

	}


