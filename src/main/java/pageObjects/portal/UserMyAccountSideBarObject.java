package pageObjects.portal;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import commons.PageGeneratorManager;
import pageUIs.user.MyAccountSideBarPageUI;

public class UserMyAccountSideBarObject extends BasePage {
	private WebDriver driver;

	public UserMyAccountSideBarObject(WebDriver driver) {
		this.driver = driver;
	}

	public UserAddressPageObject openAddressPage() {
		waitForElementClickable(driver, MyAccountSideBarPageUI.ADDRESS_LINK);
		clickToElement(driver, MyAccountSideBarPageUI.ADDRESS_LINK);
		return PageGeneratorManager.getUserAddressPage(driver);
	}

	public UserProductReviewPageObject openProductReviewPage() {
		waitForElementClickable(driver, MyAccountSideBarPageUI.PRODUCT_REVIEW_LINK);
		clickToElement(driver, MyAccountSideBarPageUI.PRODUCT_REVIEW_LINK);
		return PageGeneratorManager.getUserProductReviewPage(driver);
	}

	public UserRewardPointPageObject openRewardPointPage() {
		waitForElementClickable(driver, MyAccountSideBarPageUI.REWARD_POINT_LINK);
		clickToElement(driver, MyAccountSideBarPageUI.REWARD_POINT_LINK);
		return PageGeneratorManager.getUserRewardPointPage(driver);
	}

	public UserCustomerPageObject openCustomerInfoPage() {
		waitForElementClickable(driver, MyAccountSideBarPageUI.CUSTOMER_INFO_LINK);
		clickToElement(driver, MyAccountSideBarPageUI.CUSTOMER_INFO_LINK);
		return PageGeneratorManager.getUserCustomerInfoPage(driver);
	}

	public UserMyAccountSideBarObject openDynamicSideBar(String pageName) {
		waitForElementClickable(driver, MyAccountSideBarPageUI.DYNAMIC_SIDEBAR_LINK, pageName);
		clickToElement(driver, MyAccountSideBarPageUI.DYNAMIC_SIDEBAR_LINK, pageName);

		switch (pageName) {
		case "Addresses":
			return PageGeneratorManager.getUserAddressPage(driver);
		case "My product reviews":
			return PageGeneratorManager.getUserProductReviewPage(driver);
		case "Reward points":
			return PageGeneratorManager.getUserRewardPointPage(driver);
		case "Customer info":
			return PageGeneratorManager.getUserCustomerInfoPage(driver);
		default:
			throw new RuntimeException("Invalid side bar page name");
		}
	}

	public void openDynamicSideBarByName(String pageName) {
		waitForElementClickable(driver, MyAccountSideBarPageUI.DYNAMIC_SIDEBAR_LINK, pageName);
		clickToElement(driver, MyAccountSideBarPageUI.DYNAMIC_SIDEBAR_LINK, pageName);
	}
}
