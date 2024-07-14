package commons;

import java.time.Duration;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePageFactory {

	public static BasePageFactory getBasePageObject() {
		return new BasePageFactory();
	}

	protected void openPageURL(WebDriver driver, String pageURL) {
		driver.get(pageURL);
	}

	protected String getPageTitle(WebDriver driver) {
		return driver.getTitle();
	}

	protected String getPageURL(WebDriver driver) {
		return driver.getCurrentUrl();
	}

	protected String getPageSourceCode(WebDriver driver) {
		return driver.getPageSource();
	}

	protected void forwardToPage(WebDriver driver) {
		driver.navigate().forward();
	}

	protected void backToPage(WebDriver driver) {
		driver.navigate().back();
	}

	protected void refreshCurrentPage(WebDriver driver) {
		driver.navigate().refresh();
	}

	protected Alert waitForAlertPresence(WebDriver driver) {
		WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
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
		Set<String> allWindowIDs = driver.getWindowHandles();

		for (String id : allWindowIDs) {
			if (id.equals(windowID)) {
				driver.switchTo().window(id);
				break;
			}

		}
	}

	protected void switchToWindowByTitle(WebDriver driver, String tabTitle) {
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
		Set<String> allWindowIDs = driver.getWindowHandles();

		for (String id : allWindowIDs) {
			if (!id.equals(parentID)) {
				driver.switchTo().window(id);
				driver.close();
			}
			driver.switchTo().window(parentID);

		}
	}

	private WebElement getWebElement(WebDriver driver, String xpathLocator) {
		return driver.findElement(By.xpath(xpathLocator));
	}

	private List<WebElement> getListOfWebElements(WebDriver driver, String xpathLocator) {
		return driver.findElements(By.xpath(xpathLocator));
	}

	protected void clickToElement(WebDriver driver, WebElement element) {
		element.click();
	}

	protected void sendKeyToElement(WebDriver driver, WebElement element, String textValue) {
		element.clear();
		element.sendKeys(textValue);
	}

	protected String getElementText(WebDriver driver, WebElement element) {
		return element.getText();
	}

	protected void selectItemInDefaultDropDown(WebDriver driver, String xpathLocator, String textValue) {
		Select select = new Select(getWebElement(driver, xpathLocator));
		select.selectByValue(textValue);
	}

	protected String getSelectedItemDefaultDropdown(WebDriver driver, String xpathLocator) {
		Select select = new Select(getWebElement(driver, xpathLocator));
		return select.getFirstSelectedOption().getText();
	}

	protected boolean isDropdownMultiple(WebDriver driver, String xpathLocator) {
		Select select = new Select(getWebElement(driver, xpathLocator));
		return select.isMultiple();
	}

	protected void selectItemInCustomDropDown(WebDriver driver, WebElement locatorButton, String locatorItems, String inputText) {
		clickToElement(driver, locatorButton);

		List<WebElement> allItems = getListOfWebElements(driver, locatorItems);
		for (WebElement item : allItems) {
			String textItem = item.getText();
			if (textItem.equals(inputText)) {
				item.click();
				break;
			}
		}
	}

	protected void scrollToElement(WebDriver driver, WebElement emailTextBox) {
		JavascriptExecutor jsExecutor;
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", emailTextBox);
	}

	protected void waitInSecond(int second) {
		try {
			Thread.sleep(second * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	protected String getElementAttribute(WebDriver driver, String xpathLocator, String attributeName) {
		return getWebElement(driver, xpathLocator).getAttribute(attributeName);
	}

	protected String getElementCssValue(WebDriver driver, String xpathLocator, String propertyName) {
		return getWebElement(driver, xpathLocator).getCssValue(propertyName);
	}

	protected String getHexaColorFromRGBA(String rgbaValue) {
		return Color.fromString(rgbaValue).asHex();
	}

	protected int getElementSize(WebDriver driver, String xpathLocator) {
		return getListOfWebElements(driver, xpathLocator).size();
	}

	protected void checkToDefaultCheckBoxRadio(WebDriver driver, String xpathLocator) {
		WebElement element = getWebElement(driver, xpathLocator);
		if (!element.isSelected()) {
			element.click();
		}
	}

	protected void uncheckToDefaultCheckBoxRadio(WebDriver driver, String xpathLocator) {
		WebElement element = getWebElement(driver, xpathLocator);
		if (element.isSelected()) {
			element.click();
		}
	}

	protected boolean isElementDisplayed(WebDriver driver, WebElement element) {
		return element.isDisplayed();
	}

	protected boolean isElementEnabled(WebDriver driver, String xpathLocator) {
		return getWebElement(driver, xpathLocator).isEnabled();
	}

	protected boolean isElementSelected(WebDriver driver, String xpathLocator) {
		return getWebElement(driver, xpathLocator).isSelected();
	}

	protected void switchToFrameIframe(WebDriver driver, String xpathLocator) {
		driver.switchTo().frame(getWebElement(driver, xpathLocator));
	}

	protected void switchToDefaultContent(WebDriver driver) {
		driver.switchTo().defaultContent();
	}

	protected void hoverMouseToElement(WebDriver driver, String xpathLocator) {
		Actions action = new Actions(driver);
		action.moveToElement(getWebElement(driver, xpathLocator)).perform();

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
//		WebDriverWait explicitWait = new WebDriverWait(driver,Duration.ofSeconds(timeout));
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

	protected void waitUntilElementVisible(WebDriver driver, WebElement element) {
		WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		explicitWait.until(ExpectedConditions.visibilityOf(element));
	}

	protected void waitUntilAllElementsVisible(WebDriver driver, List<WebElement> elements) {
		WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		explicitWait.until(ExpectedConditions.visibilityOfAllElements(elements));
	}

	protected void waitUntilElementInvisible(WebDriver driver, WebElement element) {
		WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		explicitWait.until(ExpectedConditions.invisibilityOf(element));
	}

	protected void waitUntilAllElementsInvisible(WebDriver driver, List<WebElement> elements) {
		WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		explicitWait.until(ExpectedConditions.invisibilityOfAllElements(elements));
	}

	protected void waitForElementClickable(WebDriver driver, WebElement element) {
		WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		explicitWait.until(ExpectedConditions.elementToBeClickable(element));
	}

	private long timeout = 30;

}
