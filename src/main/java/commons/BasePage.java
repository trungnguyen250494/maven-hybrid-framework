package commons;

import java.time.Duration;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import pageObjects.admin.AdminLoginPageObject;
import pageObjects.portal.UserHomePageObject;
import pageUIs.jquery.UploadPageUI;
import pageUIs.user.BaseElementUI;

public class BasePage {

	public static BasePage getBasePageObject() {
		return new BasePage();
	}

	public void openPageURL(WebDriver driver, String pageURL) {
		if (driver.toString().contains("safari")) {
			waitInSecond(GlobalConstants.LONG_TIMEOUT);
		}
		driver.get(pageURL);
	}

	protected String getPageTitle(WebDriver driver) {
		if (driver.toString().contains("safari")) {
			waitInSecond(GlobalConstants.LONG_TIMEOUT);
		}
		return driver.getTitle();
	}

	protected String getPageURL(WebDriver driver) {
		if (driver.toString().contains("safari")) {
			waitInSecond(GlobalConstants.LONG_TIMEOUT);
		}
		return driver.getCurrentUrl();
	}

	protected String getPageSourceCode(WebDriver driver) {
		if (driver.toString().contains("safari")) {
			waitInSecond(GlobalConstants.LONG_TIMEOUT);
		}
		return driver.getPageSource();
	}

	protected void forwardToPage(WebDriver driver) {
		if (driver.toString().contains("safari")) {
			waitInSecond(GlobalConstants.LONG_TIMEOUT);
		}
		driver.navigate().forward();
	}

	protected void backToPage(WebDriver driver) {
		if (driver.toString().contains("safari")) {
			waitInSecond(GlobalConstants.LONG_TIMEOUT);
		}
		driver.navigate().back();
	}

	public void refreshCurrentPage(WebDriver driver) {
		if (driver.toString().contains("safari")) {
			waitInSecond(GlobalConstants.LONG_TIMEOUT);

		}
		driver.navigate().refresh();
	}

	protected Alert waitForAlertPresence(WebDriver driver) {
		if (driver.toString().contains("safari")) {
			waitInSecond(GlobalConstants.LONG_TIMEOUT);

		}
		WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT));
		return explicitWait.until(ExpectedConditions.alertIsPresent());
	}

	protected void acceptAlert(WebDriver driver) {
		waitForAlertPresence(driver).accept();
	}

	protected void cancelAlert(WebDriver driver) {
		waitForAlertPresence(driver).dismiss();
	}

	protected String getAlertText(WebDriver driver) {
		return waitForAlertPresence(driver).getText();
	}

	protected void sendKeyToAlert(WebDriver driver, String keyToSend) {
		waitForAlertPresence(driver).sendKeys(keyToSend);
	}

	protected void switchToWindowByID(WebDriver driver, String windowID) {
		if (driver.toString().contains("safari")) {
			waitInSecond(GlobalConstants.LONG_TIMEOUT);

		}
		Set<String> allWindowIDs = driver.getWindowHandles();

		for (String id : allWindowIDs) {
			if (id.equals(windowID)) {
				driver.switchTo().window(id);
				break;
			}

		}
	}

	protected void switchToWindowByTitle(WebDriver driver, String tabTitle) {
		if (driver.toString().contains("safari")) {
			waitInSecond(GlobalConstants.LONG_TIMEOUT);

		}
		Set<String> allWindowIDs = driver.getWindowHandles();

		for (String id : allWindowIDs) {
			driver.switchTo().window(id);
			String actualTitle = driver.getTitle();
			if (actualTitle.equals(tabTitle)) {
				break;
			}

		}
	}

	protected void closeAllTabWithoutParent(WebDriver driver, String parentID) {
		if (driver.toString().contains("safari")) {
			waitInSecond(GlobalConstants.LONG_TIMEOUT);

		}
		Set<String> allWindowIDs = driver.getWindowHandles();

		for (String id : allWindowIDs) {
			if (!id.equals(parentID)) {
				driver.switchTo().window(id);
				driver.close();
			}
			driver.switchTo().window(parentID);

		}
	}

	private By getByXpath(String locatorType) {
		return By.xpath(locatorType);
	}

	private By getByLocator(String locatorType) {
		By by = null;
		if (locatorType.toLowerCase().startsWith("id=")) {
			by = By.id(locatorType.substring(3));
		} else if (locatorType.toLowerCase().startsWith("class=")) {
			by = By.className(locatorType.substring(6));
		} else if (locatorType.toLowerCase().startsWith("name=")) {
			by = By.name(locatorType.substring(5));
		} else if (locatorType.toLowerCase().startsWith("css=")) {
			by = By.cssSelector(locatorType.substring(4));
		} else if (locatorType.toLowerCase().startsWith("xpath=")) {
			by = By.xpath(locatorType.substring(6));
		} else {
			throw new RuntimeException("Locator type is not supported!");
		}

		return by;
	}

	private String getDynamicXpath(String locatorType, String... restParams) {
		if (locatorType.toLowerCase().startsWith("xpath=")) {
			locatorType = String.format(locatorType, (Object[]) restParams);
		}
		return locatorType;

	}

	private WebElement getWebElement(WebDriver driver, String locatorType) {

		if (driver.toString().contains("safari")) {
			waitInSecond(GlobalConstants.LONG_TIMEOUT);
			driver.switchTo().frame(0);
		}
		return driver.findElement(getByLocator(locatorType));
	}

	protected List<WebElement> getListOfWebElements(WebDriver driver, String locatorType) {
		if (driver.toString().contains("safari")) {
			waitInSecond(GlobalConstants.LONG_TIMEOUT);

		}
		return driver.findElements(getByLocator(locatorType));
	}

	protected List<WebElement> getListOfWebElements(WebDriver driver, String locatorType, String... restParams) {
		if (driver.toString().contains("safari")) {
			waitInSecond(GlobalConstants.LONG_TIMEOUT);

		}
		return driver.findElements(getByLocator(getDynamicXpath(locatorType, restParams)));
	}

	protected void clickToElement(WebDriver driver, String locatorType) {
		getWebElement(driver, locatorType).click();
	}

	protected void clickToElement(WebDriver driver, WebElement element) {
		if (driver.toString().contains("safari")) {
			waitInSecond(GlobalConstants.LONG_TIMEOUT);

		}
		element.click();
	}

	protected void clickToElement(WebDriver driver, String locatorType, String... restParams) {
		getWebElement(driver, getDynamicXpath(locatorType, restParams)).click();
	}

	protected void sendKeyToElement(WebDriver driver, String locatorType, String textValue) {
		WebElement element = getWebElement(driver, locatorType);
		element.clear();
		element.sendKeys(textValue);
	}

	protected void sendKeyToElement(WebDriver driver, String locatorType, String textValue, String... restParams) {
		WebElement element = getWebElement(driver, getDynamicXpath(locatorType, restParams));
		element.clear();
		element.sendKeys(textValue);
	}

	protected String getElementText(WebDriver driver, String locatorType) {
		return getWebElement(driver, locatorType).getText();
	}

	protected String getElementText(WebDriver driver, String locatorType, String... restParams) {
		return getWebElement(driver, getDynamicXpath(locatorType, restParams)).getText();
	}

	protected void selectItemInDefaultDropDown(WebDriver driver, String locatorType, String textValue) {
		Select select = new Select(getWebElement(driver, locatorType));
		select.selectByValue(textValue);
	}

	protected String getSelectedItemDefaultDropdown(WebDriver driver, String locatorType) {
		Select select = new Select(getWebElement(driver, locatorType));
		return select.getFirstSelectedOption().getText();
	}

	protected boolean isDropdownMultiple(WebDriver driver, String locatorType) {
		Select select = new Select(getWebElement(driver, locatorType));
		return select.isMultiple();
	}

	protected void selectItemInCustomDropDown(WebDriver driver, String locatorButton, String locatorItems, String inputText) {
		clickToElement(driver, locatorButton);
		waitUntilAllElementsPrecense(driver, locatorItems);

		List<WebElement> allItems = getListOfWebElements(driver, locatorItems);
		for (WebElement item : allItems) {
			String textItem = item.getText();
			if (textItem.equals(inputText)) {
				item.click();
				break;
			}
		}
	}

	protected void scrollToElement(WebDriver driver, String locatorType) {
		if (driver.toString().contains("safari")) {
			waitInSecond(GlobalConstants.LONG_TIMEOUT);
		}
		waitUntilAllElementsPrecense(driver, locatorType);
		JavascriptExecutor jsExecutor;
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getWebElement(driver, locatorType));
	}

	public void waitInSecond(int second) {
		try {
			Thread.sleep(second * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	protected String getElementAttribute(WebDriver driver, String locatorType, String attributeName) {
		return getWebElement(driver, locatorType).getAttribute(attributeName);
	}

	protected String getElementCssValue(WebDriver driver, String locatorType, String propertyName) {
		return getWebElement(driver, locatorType).getCssValue(propertyName);
	}

	protected String getHexaColorFromRGBA(String rgbaValue) {
		return Color.fromString(rgbaValue).asHex();
	}

	protected int getElementSize(WebDriver driver, String locatorType) {
		return getListOfWebElements(driver, locatorType).size();
	}

	protected int getElementSize(WebDriver driver, String locatorType, String... restParams) {
		return getListOfWebElements(driver, getDynamicXpath(locatorType, restParams)).size();
	}

	protected void checkToDefaultCheckBoxRadio(WebDriver driver, String locatorType) {
		WebElement element = getWebElement(driver, locatorType);
		if (!element.isSelected()) {
			element.click();
		}
	}

	protected void uncheckToDefaultCheckBoxRadio(WebDriver driver, String locatorType) {
		WebElement element = getWebElement(driver, locatorType);
		if (element.isSelected()) {
			element.click();
		}
	}

	protected boolean isElementDisplayed(WebDriver driver, String locatorType) {
		return getWebElement(driver, locatorType).isDisplayed();
	}

	protected void setImplicitWait(WebDriver driver, long timeout) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT));
	}

	protected boolean isElementNotDisplayed(WebDriver driver, String locatorType) {
		setImplicitWait(driver, GlobalConstants.SHORT_TIMEOUT);
		List<WebElement> elements = getListOfWebElements(driver, locatorType);
		setImplicitWait(driver, GlobalConstants.LONG_TIMEOUT);

		if (elements.size() == 0) {
			return true;
		} else if (elements.size() > 0 && !elements.get(0).isDisplayed()) {
			return true;
		} else {
			return false;
		}
	}

	protected boolean isElementDisplayed(WebDriver driver, String locatorType, String... restParams) {
		return getWebElement(driver, getDynamicXpath(locatorType, restParams)).isDisplayed();
	}

	protected boolean isElementEnabled(WebDriver driver, String locatorType) {
		return getWebElement(driver, locatorType).isEnabled();
	}

	protected boolean isElementSelected(WebDriver driver, String locatorType) {
		return getWebElement(driver, locatorType).isSelected();
	}

	protected void switchToFrameIframe(WebDriver driver, String locatorType) {
		driver.switchTo().frame(getWebElement(driver, locatorType));
	}

	protected void switchToDefaultContent(WebDriver driver) {
		driver.switchTo().defaultContent();
	}

	protected void hoverMouseToElement(WebDriver driver, String locatorType) {
		Actions action = new Actions(driver);
		action.moveToElement(getWebElement(driver, locatorType)).perform();

	}

	protected Object executeForBrowser(WebDriver driver, String javaScript) {
		JavascriptExecutor jsExecutor;
		jsExecutor = (JavascriptExecutor) driver;
		return jsExecutor.executeScript(javaScript);
	}

	protected String getInnerText(WebDriver driver) {
		JavascriptExecutor jsExecutor;
		jsExecutor = (JavascriptExecutor) driver;
		return (String) jsExecutor.executeScript("return document.documentElement.innerText;");
	}

	protected boolean areExpectedTextInInnerText(WebDriver driver, String textExpected) {
		JavascriptExecutor jsExecutor;
		jsExecutor = (JavascriptExecutor) driver;
		String textActual = (String) jsExecutor.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0]");
		return textActual.equals(textExpected);
	}

	protected void scrollToBottomPage(WebDriver driver) {
		JavascriptExecutor jsExecutor;
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}

	protected void navigateToUrlByJS(WebDriver driver, String url) {
		JavascriptExecutor jsExecutor;
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("window.location = '" + url + "'");
	}

	protected void highlightElement(WebDriver driver, String locator) {
		JavascriptExecutor jsExecutor;
		jsExecutor = (JavascriptExecutor) driver;
		WebElement element = getWebElement(driver, locator);
		String originalStyle = element.getAttribute("style");
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", "border: 2px solid red; border-style: dashed;");
		waitInSecond(1);
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", originalStyle);
	}

	protected void clickToElementByJS(WebDriver driver, String locator) {
		JavascriptExecutor jsExecutor;
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].click();", getWebElement(driver, locator));
	}

	protected void sendkeyToElementByJS(WebDriver driver, String locator, String value) {
		JavascriptExecutor jsExecutor;
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", getWebElement(driver, locator));
	}

	protected void removeAttributeInDOM(WebDriver driver, String locator, String attributeRemove) {
		JavascriptExecutor jsExecutor;
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getWebElement(driver, locator));
	}

//	protected boolean areJQueryAndJSLoadedSuccess(WebDriver driver) {
//		WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT));
//		JavascriptExecutor jsExecutor;
//		jsExecutor = (JavascriptExecutor) driver;
//
//		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<>() {
//			@Override
//			public Boolean apply(WebDriver driver) {
//				try {
//					return ((Long) jsExecutor.executeScript("return jQuery.active") == 0);
//				} catch (Exception e) {
//					return true;
//				}
//			}
//		};
//
//		ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<>() {
//			@Override
//			public Boolean apply(WebDriver driver) {
//				return jsExecutor.executeScript("return document.readyState").toString().equals("complete");
//			}
//		};
//
//		return explicitWait.until(jQueryLoad) && explicitWait.until(jsLoad);
//	}

	protected String getElementValidationMessage(WebDriver driver, String locator) {
		JavascriptExecutor jsExecutor;
		jsExecutor = (JavascriptExecutor) driver;
		return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getWebElement(driver, locator));
	}

	protected boolean isImageLoaded(WebDriver driver, String locator) {
		JavascriptExecutor jsExecutor;
		jsExecutor = (JavascriptExecutor) driver;
		boolean status = (boolean) jsExecutor.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", getWebElement(driver, locator));
		if (status) {
			return true;
		} else {
			return false;
		}
	}

	protected void waitUntilElementVisible(WebDriver driver, String elementLocator) {
		WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT));
		explicitWait.until(ExpectedConditions.visibilityOf(getWebElement(driver, elementLocator)));
	}

	protected void waitUntilElementVisible(WebDriver driver, String elementLocator, String... restParams) {
		WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT));
		explicitWait.until(ExpectedConditions.visibilityOf(getWebElement(driver, getDynamicXpath(elementLocator, restParams))));
	}

	protected void waitUntilAllElementsVisible(WebDriver driver, String elementsLocator) {
		WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT));
		explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByXpath(elementsLocator)));
	}

	protected void waitUntilElementInvisible(WebDriver driver, String elementLocator) {
		WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT));
		explicitWait.until(ExpectedConditions.invisibilityOf(getWebElement(driver, elementLocator)));
	}

	protected void waitUntilAllElementsInvisible(WebDriver driver, String elementsLocator) {
		WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT));
		explicitWait.until(ExpectedConditions.invisibilityOfAllElements(getListOfWebElements(driver, elementsLocator)));
	}

	protected void waitForElementClickable(WebDriver driver, String elementsLocator) {
		WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT));
		explicitWait.until(ExpectedConditions.elementToBeClickable(getByLocator(elementsLocator)));
	}

	protected void waitForElementClickable(WebDriver driver, WebElement element) {
		WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT));
		explicitWait.until(ExpectedConditions.elementToBeClickable(element));
	}

	protected void waitForElementClickable(WebDriver driver, String elementsLocator, String... restParams) {
		WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT));
		explicitWait.until(ExpectedConditions.elementToBeClickable(getByLocator(getDynamicXpath(elementsLocator, restParams))));
	}

	protected WebElement waitUntilElementPrecense(WebDriver driver, String elementLocator) {
		WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT));
		return explicitWait.until(ExpectedConditions.visibilityOf(getWebElement(driver, elementLocator)));
	}

	protected List<WebElement> waitUntilAllElementsPrecense(WebDriver driver, String elementsLocator) {
		WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT));
		return explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByLocator(elementsLocator)));
	}

	public Set<Cookie> getBrowserCookies(WebDriver driver) {
		return driver.manage().getCookies();
	}

	public void setCookies(WebDriver driver, Set<Cookie> cookies) {
		for (Cookie cookie : cookies) {
			driver.manage().addCookie(cookie);
		}
	}

	public BasePage openPagesAtMyAccountByName(WebDriver driver, String pageName) {
		waitForElementClickable(driver, BaseElementUI.DYNAMIC_PAGE_AT_MY_ACCOUNT_AREA, pageName);
		clickToElement(driver, BaseElementUI.DYNAMIC_PAGE_AT_MY_ACCOUNT_AREA, pageName);
		switch (pageName) {
		case "Customer info":
			return PageGeneratorManager.getUserCustomerInfoPage(driver);
		case "Addresses":
			return PageGeneratorManager.getUserAddressPage(driver);
		case "My product reviews":
			return PageGeneratorManager.getUserProductReviewPage(driver);
		case "Reward points":
			return PageGeneratorManager.getUserRewardPointPage(driver);
		default:
			throw new RuntimeException("Invalid page name at My Account area.");
		}
	}

	public UserHomePageObject clickToLogoutLinkAtUserPage(WebDriver driver) {
		waitForElementClickable(driver, BaseElementUI.LOGOUT_LINK_AT_USER);
		clickToElement(driver, BaseElementUI.LOGOUT_LINK_AT_USER);
		return PageGeneratorManager.getUserHomePage(driver);
	}

	public AdminLoginPageObject clickToLogoutLinkAtAdminPage(WebDriver driver) {
		waitForElementClickable(driver, BaseElementUI.LOGOUT_LINK_AT_ADMIN);
		clickToElement(driver, BaseElementUI.LOGOUT_LINK_AT_ADMIN);
		return PageGeneratorManager.getAdminLoginPage(driver);

	}

	public void uploadMultipleFiles(WebDriver driver, String... fileNames) {
		String filePath = System.getProperty("user.dir") + getDirectorySlash("uploadFiles");
		String fullFileName = "";

		for (String file : fileNames) {
			fullFileName = fullFileName + filePath + file + "\n";
		}

		fullFileName = fullFileName.trim();

		getWebElement(driver, UploadPageUI.UPLOAD_FILE_TYPE).sendKeys(fullFileName);
	}

	public String getDirectorySlash(String folderName) {
		String separator = System.getProperty("file.separator");
		return separator + folderName + separator;
	}

}
