package com.nopcommerce.share;

import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import commons.PageGeneratorManager;
import pageObjects.portal.UserHomePageObject;
import pageObjects.portal.UserLoginPageObject;

public class Level_22_Login extends BaseTest {
	private WebDriver driver;
	private String validEmail, correctPassword;
	private UserHomePageObject homePage;
	private UserLoginPageObject loginPage;

	@Parameters({ "browser", "environment" })
	@BeforeClass
	public void beforeClass(String browserName, String environmentName) {
		driver = getBrowserDriver(browserName, environmentName);
		homePage = PageGeneratorManager.getUserHomePage(driver);

		correctPassword = Common_Register.correctPassword;
		validEmail = Common_Register.validEmail;

	}

	@Test
	public void TC_01_Login_Success() {

		System.out.println("Login_01 - Step 01: Click to the Login link");

		homePage = PageGeneratorManager.getUserHomePage(driver);
		homePage.clickToHeaderLinkByName("Log in");
		loginPage = PageGeneratorManager.getUserLoginPage(driver);

		System.out.println("Login_01 - Step 02: Input existing email");
		loginPage.inputToTextboxByID("Email", validEmail);

		System.out.println("Login_01 - Step 03: Input valid password");
		loginPage.inputToTextboxByID("Password", correctPassword);

		System.out.println("Login_01 - Step 04: Click to the Login button");
		loginPage.clickToButtonByText("Log in");

		System.out.println("Login_01 - Step 05: Verify Log out link display");

		homePage = new UserHomePageObject(driver);

		Assert.assertTrue(homePage.isHeaderLinkDisplayedByName("Log out"));
		Assert.assertTrue(homePage.isHeaderLinkDisplayedByName("My account"));
	}

	public int getRandomNumber() {
		Random rd = new Random();
		return rd.nextInt(1000);
	}

	@AfterClass
	public void afterClass() {
		closeBrowserDriver();
	}
}
