package pageObjects.admin;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.admin.AdminDashboardPageUI;

public class AdminDashboardPageObject extends BasePage {
	private WebDriver driver;

	public AdminDashboardPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public boolean isDashboardHeaderDisplayed() {
		return isElementDisplayed(driver, AdminDashboardPageUI.DASHBOARD_HEADER);
	}

}
