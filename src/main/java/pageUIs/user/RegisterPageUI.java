package pageUIs.user;

public class RegisterPageUI {

	public static final String FIRST_NAME_TEXTBOX = "id=FirstName";
	public static final String LAST_NAME_TEXTBOX = "id=LastName";
	public static final String EMAIL_TEXTBOX = "id=Email";
	public static final String PASSWORD_TEXTBOX = "id=Password";
	public static final String CONFIRM_PASSWORD_TEXTBOX = "id=ConfirmPassword";
	public static final String REGISTER_BUTTON = "xpath=//button[@id='register-button']";
	public static final String FIRST_NAME_ERROR_MESSAGE = "xpath=//span[@id='FirstName-error']";
	public static final String LAST_NAME_ERROR_MESSAGE = "xpath=//span[@id='LastName-error']";
	public static final String EMAIL_ERROR_MESSAGE = "xpath=//span[@id='Email-error']";
	public static final String PASSWORD_ERROR_MESSAGE = "xpath=//span[@id='Password-error']";
	public static final String CONFIRM_PASSWORD_ERROR_MESSAGE = "xpath=//span[@id='ConfirmPassword-error']";
	public static final String EXISTING_EMAIL_ERROR_MESSAGE = "xpath=//div[@class='message-error validation-summary-errors']//li";
	public static final String SUCCESS_MESSAGE = "xpath=//div[@class='result']";
}
