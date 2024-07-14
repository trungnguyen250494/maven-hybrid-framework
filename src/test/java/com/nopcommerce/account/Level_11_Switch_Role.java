package com.nopcommerce.account;

import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import commons.GlobalConstants;
import commons.PageGeneratorManager;
import pageObjects.admin.AdminDashboardPageObject;
import pageObjects.admin.AdminLoginPageObject;
import pageObjects.portal.UserHomePageObject;
import pageObjects.portal.UserLoginPageObject;
import pageObjects.portal.UserRegisterPageObject;

public class Level_11_Switch_Role extends BaseTest {
	private WebDriver driver;
	private String validUserEmail, firstName, lastName, correctUserPassword, confirmPassword, adminEmail, adminPassword;
	private UserHomePageObject userHomePage;
	private UserLoginPageObject userLoginPage;
	private AdminLoginPageObject adminLoginPage;
	private UserRegisterPageObject registerPage;
	private AdminDashboardPageObject adminDashboardPage;

	@Parameters({ "browser", "environment" })
	@BeforeClass
	public void beforeClass(String browserName, String environmentName) {
		driver = getBrowserDriver(browserName, environmentName);
		userHomePage = PageGeneratorManager.getUserHomePage(driver);

		firstName = "Tester";
		lastName = "Tester";
		correctUserPassword = "Tester@123";
		confirmPassword = "Tester@123";
		validUserEmail = "tester" + getRandomNumber() + "@yopmail.com";
		adminEmail = "admin@yourstore.com";
		adminPassword = "admin";

	}

	@Test
	public void TC01_Role_User_Register() {
		registerPage = userHomePage.clickToRegisterLink();
		registerPage.inputToFirstNameTextbox(firstName);
		registerPage.inputToLastNameTextbox(lastName);
		registerPage.inputToEmailTextbox(validUserEmail);
		registerPage.inputToPasswordTextbox(correctUserPassword);
		registerPage.inputToConfirmPasswordTextbox(confirmPassword);
		registerPage.clickToRegisterButton();
		Assert.assertEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");
	}

	@Test
	public void TC02_Role_User_Login_Logout() {
		userLoginPage = userHomePage.clickToLoginLink();
		userHomePage = userLoginPage.loginAsUser(validUserEmail, correctUserPassword);
		Assert.assertTrue(userHomePage.isLogoutLinkDisplayed());
		Assert.assertTrue(userHomePage.isMyAccountLinkDisplayed());

		userHomePage.clickToLogoutLinkAtUserPage(driver);

		userHomePage.openPageURL(driver, GlobalConstants.ADMIN_PAGE_URL);
		adminLoginPage = PageGeneratorManager.getAdminLoginPage(driver);
		adminDashboardPage = adminLoginPage.loginAsAdmin(adminEmail, adminPassword);
		Assert.assertTrue(adminDashboardPage.isDashboardHeaderDisplayed());

		adminLoginPage = adminDashboardPage.clickToLogoutLinkAtAdminPage(driver);

	}

	@Test
	public void TC03_Role_Admin_Login_Logout() {
		adminLoginPage.openPageURL(driver, GlobalConstants.USER_PAGE_URL);
		userHomePage = PageGeneratorManager.getUserHomePage(driver);
	}

	public int getRandomNumber() {
		Random rd = new Random();
		return rd.nextInt(1000);
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
