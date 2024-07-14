package pageObjects.facebook;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.facebook.HomePageUI;

public class HomePageObject extends BasePage {
	WebDriver driver;

	public HomePageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void clickToCreateNewAccountButton() {
		waitForElementClickable(driver, HomePageUI.CREATE_NEW_ACCOUNT_BUTTON);
		clickToElement(driver, HomePageUI.CREATE_NEW_ACCOUNT_BUTTON);
	}

	public boolean isFirstNameTextboxDisplayed() {
		return isElementDisplayed(driver, HomePageUI.FIRST_NAME_TEXTBOX);
	}

	public boolean isSurNameTextboxDisplayed() {
		return isElementDisplayed(driver, HomePageUI.LAST_NAME_TEXTBOX);
	}

	public boolean isEmailTextboxDisplayed() {
		return isElementDisplayed(driver, HomePageUI.EMAIL_TEXTBOX);
	}

	public boolean isPasswordTextboxDisplayed() {
		return isElementDisplayed(driver, HomePageUI.PASSWORD_TEXTBOX);
	}

	public boolean isFirstNameTextboxNotDisplayed() {
		return isElementNotDisplayed(driver, HomePageUI.FIRST_NAME_TEXTBOX);
	}

	public boolean isSurNameTextboxNotDisplayed() {
		return isElementNotDisplayed(driver, HomePageUI.LAST_NAME_TEXTBOX);
	}

	public boolean isEmailTextboxNotDisplayed() {
		return isElementNotDisplayed(driver, HomePageUI.EMAIL_TEXTBOX);
	}

	public boolean isPasswordTextboxNotDisplayed() {
		return isElementNotDisplayed(driver, HomePageUI.PASSWORD_TEXTBOX);
	}

	public void clickToCloseCreateNewAccountPopup() {
		waitForElementClickable(driver, HomePageUI.CLOSE_SIGNUP_BUTTON);
		clickToElement(driver, HomePageUI.CLOSE_SIGNUP_BUTTON);
	}

	public boolean isFirstNameEmptyIconDisplayed() {
		return isElementDisplayed(driver, HomePageUI.FIRST_NAME_EMPTY_ICON);
	}

	public void inputToFirstNameTextbox(String firstName) {
		waitUntilElementVisible(driver, HomePageUI.FIRST_NAME_TEXTBOX);
		sendKeyToElement(driver, HomePageUI.FIRST_NAME_TEXTBOX, firstName);
	}

	public void inputToSurNameTextbox(String surName) {
		waitUntilElementVisible(driver, HomePageUI.LAST_NAME_TEXTBOX);
		sendKeyToElement(driver, HomePageUI.LAST_NAME_TEXTBOX, surName);
	}

}
