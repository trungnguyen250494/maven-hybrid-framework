package pageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import commons.BasePageFactory;

public class RegisterPageObject extends BasePageFactory {

	private WebDriver driver;

	public RegisterPageObject(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//input[@id='FirstName']")
	private WebElement firstNameTextbox;

	@FindBy(xpath = "//input[@id='LastName']")
	private WebElement lastNameTextbox;

	@FindBy(xpath = "//input[@id='Email']")
	private WebElement emailTextBox;

	@FindBy(xpath = "//input[@id='Password']")
	private WebElement passwordTextbox;

	@FindBy(xpath = "//input[@id='ConfirmPassword']")
	private WebElement confirmPwdTextbox;

	@FindBy(xpath = "//button[@id='register-button']")
	private WebElement registerButton;

	@FindBy(xpath = "//span[@id='FirstName-error']")
	private WebElement firstNameErrMsg;

	@FindBy(xpath = "//span[@id='LastName-error']")
	private WebElement lastNameErrMsg;

	@FindBy(xpath = "//span[@id='Email-error']")
	private WebElement emailErrMsg;

	@FindBy(xpath = "//span[@id='Password-error']")
	private WebElement passwordErrMsg;

	@FindBy(xpath = "//span[@id='ConfirmPassword-error']")
	private WebElement confirmPwdErrMsg;

	@FindBy(xpath = "//div[@class='message-error validation-summary-errors']//li")
	private WebElement existingEmailErrMsg;

	@FindBy(xpath = "//div[@class='result']")
	private WebElement successMessage;

	public void clickToRegisterButton() {
		waitForElementClickable(driver, registerButton);
		clickToElement(driver, registerButton);
	}

	public String getErrorMessageAtFirstNameTextbox() {
		waitUntilElementVisible(driver, firstNameErrMsg);
		return getElementText(driver, firstNameErrMsg);
	}

	public String getErrorMessageAtLastNameTextbox() {
		waitUntilElementVisible(driver, lastNameErrMsg);
		return getElementText(driver, lastNameErrMsg);
	}

	public String getErrorMessageAtEmailTextbox() {
		waitUntilElementVisible(driver, emailErrMsg);
		return getElementText(driver, emailErrMsg);
	}

	public String getErrorMessageAtPasswordTextbox() {
		waitUntilElementVisible(driver, passwordErrMsg);
		return getElementText(driver, passwordErrMsg);
	}

	public String getErrorMessageAtConfirmPasswordTextbox() {
		waitUntilElementVisible(driver, confirmPwdErrMsg);
		return getElementText(driver, confirmPwdErrMsg);
	}

	public void inputToFirstNameTextbox(String firstName) {
		waitUntilElementVisible(driver, firstNameTextbox);
		sendKeyToElement(driver, firstNameTextbox, firstName);

	}

	public void inputToLasttNameTextbox(String lastName) {
		waitUntilElementVisible(driver, lastNameTextbox);
		sendKeyToElement(driver, lastNameTextbox, lastName);

	}

	public void inputToEmailTextbox(String email) {
		waitUntilElementVisible(driver, emailTextBox);
		sendKeyToElement(driver, emailTextBox, email);

	}

	public void inputToPasswordTextbox(String password) {
		waitUntilElementVisible(driver, passwordTextbox);
		sendKeyToElement(driver, passwordTextbox, password);

	}

	public void inputToConfirmPasswordTextbox(String confirmPassword) {
		waitUntilElementVisible(driver, confirmPwdTextbox);
		sendKeyToElement(driver, confirmPwdTextbox, confirmPassword);

	}

	public String getRegisterSuccessMessage() {
		waitUntilElementVisible(driver, successMessage);
		return getElementText(driver, successMessage);
	}

	public String getExistingEmailErrorMessage() {
		waitUntilElementVisible(driver, existingEmailErrMsg);
		return getElementText(driver, existingEmailErrMsg);
	}

	public void scrollToEmail() {
		scrollToElement(driver, emailTextBox);
	}

	public void scrollToPassword() {
		scrollToElement(driver, passwordTextbox);

	}
}
