package pageUIs.user;

public class BaseElementUI {
	public static final String LOGOUT_LINK_AT_USER = "class=ico-logout";
	public static final String LOGOUT_LINK_AT_ADMIN = "xpath=//a[text()='Logout']";
	public static final String DYNAMIC_PAGE_AT_MY_ACCOUNT_AREA = "xpath=//div[@class='block block-account-navigation']//a[text()='%s']";
	public static final String DYNAMIC_LINK_BY_PAGE_NAME = "xpath=//div[contains(@class,'%s')]//a[text()='%s']";
	public static final String DYNAMIC_HEADER_LINK_BY_NAME = "xpath=//div[@class='header-links']//a[contains(string(),'%s')]";
	public static final String DYNAMIC_BUTTON_BY_TEXT = "xpath=//button[text()='%s']";
	public static final String DYNAMIC_TEXTBOX_ERROR_MESSAGE_BY_ID = "xpath=//span[@id='%s-error']";
	public static final String DYNAMIC_TEXTBOX_BY_ID = "xpath=//input[@id='%s']";
}
