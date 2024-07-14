package com.nopcommerce.account;

import java.lang.reflect.Method;
import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import commons.BaseTest;
import commons.PageGeneratorManager;
import pageObjects.portal.UserCustomerPageObject;
import pageObjects.portal.UserHomePageObject;
import pageObjects.portal.UserLoginPageObject;
import pageObjects.portal.UserRegisterPageObject;
import reportConfig.ExtentTestManager;

public class Level_18_ExtentReportV5 extends BaseTest {
	private WebDriver driver;
	private String validEmail, firstName, lastName, correctPassword;
	private UserHomePageObject homePage;
	private UserRegisterPageObject registerPage;
	private UserLoginPageObject loginPage;
	private UserCustomerPageObject customerInfoPage;

	@Parameters({ "browser", "environment" })
	@BeforeClass
	public void beforeClass(String browserName, String environmentName) {
		driver = getBrowserDriver(browserName, environmentName);
		homePage = PageGeneratorManager.getUserHomePage(driver);
		loginPage = PageGeneratorManager.getUserLoginPage(driver);
		customerInfoPage = PageGeneratorManager.getUserCustomerInfoPage(driver);

		firstName = "Tester";
		lastName = "Tester";
		correctPassword = "Tester@123";
		validEmail = "tester" + getRandomNumber() + "@yopmail.com";

	}

	@Test
	public void User_01_Register(Method method) {
		ExtentTestManager.startTest(method.getName(), "Register to system with valid Email and Password");
		ExtentTestManager.getTest().log(Status.INFO, "Register - Step 01: Navigate to 'Register' page");
		registerPage = homePage.clickToRegisterLink();

		ExtentTestManager.getTest().log(Status.INFO, "Register - Step 02: Enter to Firstname textbox with value is '" + firstName + "'");
		registerPage.inputToFirstNameTextbox(firstName);

		ExtentTestManager.getTest().log(Status.INFO, "Register - Step 03: Enter to Lastname textbox with value is '" + lastName + "'");
		registerPage.inputToLastNameTextbox(lastName);

		ExtentTestManager.getTest().log(Status.INFO, "Register - Step 04: Enter to Email textbox with value is '" + validEmail + "'");
		registerPage.inputToEmailTextbox(validEmail);

		ExtentTestManager.getTest().log(Status.INFO, "Register - Step 05: Enter to Password textbox with value is '" + correctPassword + "'");
		registerPage.inputToPasswordTextbox(correctPassword);

		ExtentTestManager.getTest().log(Status.INFO, "Register - Step 06: Enter to Confirm Password textbox with value is '" + correctPassword + "'");
		registerPage.inputToConfirmPasswordTextbox(correctPassword);

		ExtentTestManager.getTest().log(Status.INFO, "Register - Step 07: Click to 'Register' button");
		registerPage.clickToRegisterButton();

		ExtentTestManager.getTest().log(Status.INFO, "Register - Step 08: Verify register success message is displayed");
		Assert.assertEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");
	}

	@Test
	public void User_02_Login(Method method) {
		ExtentTestManager.startTest(method.getName(), "Login to system successfully");
		ExtentTestManager.getTest().log(Status.INFO, "Login - Step 01: Navigate to Login page");
		homePage.clickToLogoutLinkAtUserPage(driver);
		loginPage = homePage.clickToLoginLink();

		ExtentTestManager.getTest().log(Status.INFO, "Login - Step 02: Enter to Email textbox with value is '" + validEmail + "'");
		loginPage.inputToEmail(validEmail);

		ExtentTestManager.getTest().log(Status.INFO, "Login - Step 03: Enter to Password textbox with value is '" + correctPassword + "'");
		loginPage.inputToPassword(correctPassword);

		ExtentTestManager.getTest().log(Status.INFO, "Login - Step 04: Click to Login button");
		homePage = loginPage.clickToLoginButton();

		ExtentTestManager.getTest().log(Status.INFO, "Login - Step 05: Verify 'My Account' link is displayed");
		Assert.assertTrue(homePage.isMyAccountLinkDisplayed());

		ExtentTestManager.getTest().log(Status.INFO, "Login - Step 06: Navigate to 'My Account' page");
		customerInfoPage = homePage.clickToMyAccountLink();

		ExtentTestManager.getTest().log(Status.INFO, "Login - Step 06: Verify 'Customer Infor' page is displayed");
		Assert.assertTrue(customerInfoPage.isDisplayed());
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
