package pageUIs.facebook;

public class HomePageUI {
	public static final String CREATE_NEW_ACCOUNT_BUTTON = "css=a[data-testid='open-registration-form-button']";
	public static final String FIRST_NAME_TEXTBOX = "name=firstname";
	public static final String LAST_NAME_TEXTBOX = "name=lastname";
	public static final String EMAIL_TEXTBOX = "name=reg_email__";
	public static final String FIRST_NAME_EMPTY_ICON = "xpath=//input[@name='firstname']/parent::div/following-sibling::i[1]";
	public static final String PASSWORD_TEXTBOX = "name=reg_passwd__";
	public static final String CLOSE_SIGNUP_BUTTON = "xpath=//div[@id='reg_box']/parent::div/parent::div/preceding-sibling::img";
}
