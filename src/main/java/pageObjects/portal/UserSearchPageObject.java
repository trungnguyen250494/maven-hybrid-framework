package pageObjects.portal;

import commons.BaseElement;
import org.openqa.selenium.WebDriver;
import pageUIs.user.SearchPageUI;

public class UserSearchPageObject extends BaseElement {
	private WebDriver driver;

	public UserSearchPageObject(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	public void enterToSearchTextbox(String value) {
		waitUntilElementVisible(driver, SearchPageUI.SEARCH_TEXTBOX);
		sendKeyToElement(driver, SearchPageUI.SEARCH_TEXTBOX, value);
	}

	public void clickToSearchButton() {
		waitUntilElementVisible(driver, SearchPageUI.SEARCH_BUTTON);
		clickToElement(driver, SearchPageUI.SEARCH_BUTTON);
	}
}
