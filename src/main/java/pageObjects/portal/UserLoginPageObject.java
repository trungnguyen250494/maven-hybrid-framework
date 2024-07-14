package pageObjects.portal;

import org.openqa.selenium.WebDriver;

import commons.BaseElement;
import commons.PageGeneratorManager;
import io.qameta.allure.Step;
import pageUIs.user.LoginPageUI;

public class UserLoginPageObject extends BaseElement {
	private WebDriver driver;

	public UserLoginPageObject(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	@Step("Click to the Login button")
	public UserHomePageObject clickToLoginButton() {
		waitForElementClickable(driver, LoginPageUI.REGISTER_BUTTON);
		clickToElement(driver, LoginPageUI.REGISTER_BUTTON);
		return PageGeneratorManager.getUserHomePage(driver);
	}

	@Step("Get error message at the Email textbox")
	public String getErrorMessageAtEmailTextbox() {
		waitUntilElementVisible(driver, LoginPageUI.EMAIL_ERROR_MESSAGE);
		return getElementText(driver, LoginPageUI.EMAIL_ERROR_MESSAGE);
	}

	@Step("Get validation error message")
	public String getValidationErrorMessage() {
		waitUntilElementVisible(driver, LoginPageUI.VALIDATION_ERROR_MESSAGE);
		return getElementText(driver, LoginPageUI.VALIDATION_ERROR_MESSAGE);
	}

	@Step("Input to the Email textbox with value is {0}")
	public void inputToEmail(String email) {
		waitUntilElementVisible(driver, LoginPageUI.EMAIL_TEXTBOX);
		sendKeyToElement(driver, LoginPageUI.EMAIL_TEXTBOX, email);
	}

	@Step("Input to the Password textbox with value is {0}")
	public void inputToPassword(String password) {
		waitUntilElementVisible(driver, LoginPageUI.PASSWORD_TEXTBOX);
		sendKeyToElement(driver, LoginPageUI.PASSWORD_TEXTBOX, password);
	}

	@Step("Login with email is {0} and password is {1}")
	public UserHomePageObject loginAsUser(String email, String password) {
		inputToEmail(email);
		inputToPassword(password);
		return clickToLoginButton();
	}

}
