package commons;

import org.openqa.selenium.WebDriver;

import pageUIs.user.BaseElementUI;

public class BaseElement extends BasePage {

	WebDriver driver;

	public BaseElement(WebDriver driver) {
		this.driver = driver;
	}

	public void clickToHeaderLinkByName(String pageName) {
		waitForElementClickable(driver, BaseElementUI.DYNAMIC_HEADER_LINK_BY_NAME, pageName);
		clickToElement(driver, BaseElementUI.DYNAMIC_HEADER_LINK_BY_NAME, pageName);
	}

	public void clickToButtonByText(String buttonText) {
		waitForElementClickable(driver, BaseElementUI.DYNAMIC_BUTTON_BY_TEXT, buttonText);
		clickToElement(driver, BaseElementUI.DYNAMIC_BUTTON_BY_TEXT, buttonText);
	}

	public String getTextboxErrorMessageByID(String errorMessageID) {
		waitUntilElementVisible(driver, BaseElementUI.DYNAMIC_TEXTBOX_ERROR_MESSAGE_BY_ID, errorMessageID);
		return getElementText(driver, BaseElementUI.DYNAMIC_TEXTBOX_ERROR_MESSAGE_BY_ID, errorMessageID);
	}

	public void inputToTextboxByID(String textboxID, String inputText) {
		waitUntilElementVisible(driver, BaseElementUI.DYNAMIC_TEXTBOX_BY_ID, textboxID);
		sendKeyToElement(driver, BaseElementUI.DYNAMIC_TEXTBOX_BY_ID, inputText, textboxID);
	}

	public boolean isHeaderLinkDisplayedByName(String pageName) {
		waitUntilElementVisible(driver, BaseElementUI.DYNAMIC_HEADER_LINK_BY_NAME, pageName);
		return isElementDisplayed(driver, BaseElementUI.DYNAMIC_HEADER_LINK_BY_NAME, pageName);
	}

}
