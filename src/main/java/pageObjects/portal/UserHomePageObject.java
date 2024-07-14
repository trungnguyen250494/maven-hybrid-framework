package pageObjects.portal;

import org.openqa.selenium.WebDriver;

import commons.BaseElement;
import commons.PageGeneratorManager;
import io.qameta.allure.Step;
import pageUIs.user.HomePageUI;

public class UserHomePageObject extends BaseElement {
	private WebDriver driver;

	public UserHomePageObject(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	@Step("Click to the Register link")
	public UserRegisterPageObject clickToRegisterLink() {
		waitForElementClickable(driver, HomePageUI.REGISTER_LINK);
		clickToElement(driver, HomePageUI.REGISTER_LINK);
		return PageGeneratorManager.getUserRegisterPage(driver);
	}

	@Step("Click to the Login link")
	public UserLoginPageObject clickToLoginLink() {
		waitForElementClickable(driver, HomePageUI.LOGIN_LINK);
		clickToElement(driver, HomePageUI.LOGIN_LINK);
		return PageGeneratorManager.getUserLoginPage(driver);
	}

	@Step("Verify if the Log out link is displayed")
	public boolean isLogoutLinkDisplayed() {
		waitUntilElementVisible(driver, HomePageUI.LOGOUT_LINK);
		return isElementDisplayed(driver, HomePageUI.LOGOUT_LINK);
	}

	@Step("Verify if the My Account Link is displayed")
	public boolean isMyAccountLinkDisplayed() {
		waitUntilElementVisible(driver, HomePageUI.MY_ACCOUNT_LINK);
		return isElementDisplayed(driver, HomePageUI.MY_ACCOUNT_LINK);
	}

	@Step("Click to the My Account link")
	public UserCustomerPageObject clickToMyAccountLink() {
		waitForElementClickable(driver, HomePageUI.MY_ACCOUNT_LINK);
		clickToElement(driver, HomePageUI.MY_ACCOUNT_LINK);
		return PageGeneratorManager.getUserCustomerInfoPage(driver);
	}

	@Step("Verify if the Register link is displayed")
	public boolean isRegisterLinkDisplayed() {
		waitUntilElementVisible(driver, HomePageUI.REGISTER_LINK);
		return isElementDisplayed(driver, HomePageUI.REGISTER_LINK);
	}

}
