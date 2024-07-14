package pageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import commons.BasePageFactory;

public class LoginPageObject extends BasePageFactory {

	private WebDriver driver;

	public LoginPageObject(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//input[@id ='Email']")
	private WebElement emailTextbox;

	@FindBy(xpath = "//input[@id='Password']")
	private WebElement passwordTextbox;

	@FindBy(xpath = "//button[contains(@class,'login-button')]")
	private WebElement loginButton;

	@FindBy(xpath = "//span[@id='Email-error']")
	private WebElement emailErrorMsg;

	@FindBy(xpath = "//div[@class='message-error validation-summary-errors']")
	private WebElement validationErrMsg;

	public void clickToLoginButton() {
		waitForElementClickable(driver, loginButton);
		clickToElement(driver, loginButton);

	}

	public String getErrorMessageAtEmailTextbox() {
		waitUntilElementVisible(driver, emailErrorMsg);
		return getElementText(driver, emailErrorMsg);
	}

	public String getValidationErrorMessage() {
		waitUntilElementVisible(driver, validationErrMsg);
		return getElementText(driver, validationErrMsg);
	}

	public void inputToEmail(String email) {
		waitUntilElementVisible(driver, emailTextbox);
		sendKeyToElement(driver, emailTextbox, email);

	}

	public void inputToPassword(String password) {
		waitUntilElementVisible(driver, passwordTextbox);
		sendKeyToElement(driver, passwordTextbox, password);

	}

}
