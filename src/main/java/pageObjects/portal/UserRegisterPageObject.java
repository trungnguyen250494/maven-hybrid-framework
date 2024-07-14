package pageObjects.portal;

import org.openqa.selenium.WebDriver;

import commons.BaseElement;
import io.qameta.allure.Step;
import pageUIs.user.RegisterPageUI;

public class UserRegisterPageObject extends BaseElement {
	private WebDriver driver;

	public UserRegisterPageObject(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	@Step("Click to the Register button")
	public void clickToRegisterButton() {
		waitForElementClickable(driver, RegisterPageUI.REGISTER_BUTTON);
		clickToElement(driver, RegisterPageUI.REGISTER_BUTTON);
	}

	@Step("Get the error message at the First name text box")
	public String getErrorMessageAtFirstNameTextbox() {
		waitUntilElementVisible(driver, RegisterPageUI.FIRST_NAME_ERROR_MESSAGE);
		return getElementText(driver, RegisterPageUI.FIRST_NAME_ERROR_MESSAGE);
	}

	@Step("Get the error message at the Last name text box")
	public String getErrorMessageAtLastNameTextbox() {
		waitUntilElementVisible(driver, RegisterPageUI.LAST_NAME_ERROR_MESSAGE);
		return getElementText(driver, RegisterPageUI.LAST_NAME_ERROR_MESSAGE);
	}

	@Step("Get the error message at the Email text box")
	public String getErrorMessageAtEmailTextbox() {
		waitUntilElementVisible(driver, RegisterPageUI.EMAIL_ERROR_MESSAGE);
		return getElementText(driver, RegisterPageUI.EMAIL_ERROR_MESSAGE);
	}

	@Step("Get the error message at the Password text box")
	public String getErrorMessageAtPasswordTextbox() {
		waitUntilElementVisible(driver, RegisterPageUI.PASSWORD_ERROR_MESSAGE);
		return getElementText(driver, RegisterPageUI.PASSWORD_ERROR_MESSAGE);
	}

	@Step("Get the error messafe at the Confirm password textbox")
	public String getErrorMessageAtConfirmPasswordTextbox() {
		waitUntilElementVisible(driver, RegisterPageUI.CONFIRM_PASSWORD_ERROR_MESSAGE);
		return getElementText(driver, RegisterPageUI.CONFIRM_PASSWORD_ERROR_MESSAGE);
	}

	@Step("Input to the First name textbox with value is {0}")
	public void inputToFirstNameTextbox(String firstName) {
		waitUntilElementVisible(driver, RegisterPageUI.FIRST_NAME_TEXTBOX);
		sendKeyToElement(driver, RegisterPageUI.FIRST_NAME_TEXTBOX, firstName);

	}

	@Step("Input to the Last name textbox with value is {0}")
	public void inputToLastNameTextbox(String lastName) {
		waitUntilElementVisible(driver, RegisterPageUI.LAST_NAME_TEXTBOX);
		sendKeyToElement(driver, RegisterPageUI.LAST_NAME_TEXTBOX, lastName);

	}

	@Step("Input to the Email textbox with value is {0}")
	public void inputToEmailTextbox(String email) {
		waitUntilElementVisible(driver, RegisterPageUI.EMAIL_TEXTBOX);
		sendKeyToElement(driver, RegisterPageUI.EMAIL_TEXTBOX, email);

	}

	@Step("Input to the Password textbox with value is {0}")
	public void inputToPasswordTextbox(String password) {
		waitUntilElementVisible(driver, RegisterPageUI.PASSWORD_TEXTBOX);
		sendKeyToElement(driver, RegisterPageUI.PASSWORD_TEXTBOX, password);

	}

	@Step("Input to the Confirm password textbox with value is {0}")
	public void inputToConfirmPasswordTextbox(String confirmPassword) {
		waitUntilElementVisible(driver, RegisterPageUI.CONFIRM_PASSWORD_TEXTBOX);
		sendKeyToElement(driver, RegisterPageUI.CONFIRM_PASSWORD_TEXTBOX, confirmPassword);

	}

	@Step("Get the Register success message")
	public String getRegisterSuccessMessage() {
		waitUntilElementVisible(driver, RegisterPageUI.SUCCESS_MESSAGE);
		return getElementText(driver, RegisterPageUI.SUCCESS_MESSAGE);
	}

	@Step("Get the existing email error message")
	public String getExistingEmailErrorMessage() {
		waitUntilElementVisible(driver, RegisterPageUI.EXISTING_EMAIL_ERROR_MESSAGE);
		return getElementText(driver, RegisterPageUI.EXISTING_EMAIL_ERROR_MESSAGE);
	}

	@Step("Scroll to Email textbox")
	public void scrollToEmail() {
		scrollToElement(driver, RegisterPageUI.EMAIL_TEXTBOX);
	}

}
