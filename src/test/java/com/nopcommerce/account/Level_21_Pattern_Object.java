package com.nopcommerce.account;

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
import pageObjects.portal.UserRegisterPageObject;

public class Level_21_Pattern_Object extends BaseTest {
	private WebDriver driver;
	private String validEmail, firstName, lastName, correctPassword, confirmPassword, invalidEmail, notFoundEmail, incorrectPassword;
	private UserHomePageObject homePage;
	private UserLoginPageObject loginPage;
	private UserRegisterPageObject registerPage;

	@Parameters({ "browser", "environment" })
	@BeforeClass
	public void beforeClass(String browserName, String environmentName) {
		driver = getBrowserDriver(browserName, environmentName);
		homePage = PageGeneratorManager.getUserHomePage(driver);

		firstName = "Tester";
		lastName = "Tester";
		correctPassword = "Tester@123";
		incorrectPassword = "123";
		confirmPassword = "Tester@123";
		validEmail = "tester" + getRandomNumber() + "@yopmail.com";
		invalidEmail = "tester@yopmail.com@123";
		notFoundEmail = "testerHello@yopmail.com";

	}

	@Test
	public void TC_01_Register_Successfully() {

		System.out.println("Register_01 - Step 01: Click to the Register link");

		// registerPage = homePage.clickToRegisterLink();
		homePage.clickToHeaderLinkByName("Register");
		registerPage = PageGeneratorManager.getUserRegisterPage(driver);

		System.out.println("Register_01 - Step 02: Input all fields value");
//		registerPage.inputToFirstNameTextbox(firstName);
//		registerPage.inputToLastNameTextbox(lastName);
//		registerPage.inputToEmailTextbox(validEmail);
//		registerPage.inputToPasswordTextbox(correctPassword);
//		registerPage.inputToConfirmPasswordTextbox(confirmPassword);

		registerPage.inputToTextboxByID("FirstName", firstName);
		registerPage.inputToTextboxByID("LastName", lastName);
		registerPage.inputToTextboxByID("Email", validEmail);
		registerPage.inputToTextboxByID("Password", correctPassword);
		registerPage.inputToTextboxByID("ConfirmPassword", confirmPassword);

		System.out.println("Register_01 - Step 03: Click to the Register button");
		// registerPage.clickToRegisterButton();
		registerPage.clickToButtonByText("Register");

		System.out.println("Register_01 - Step 04: Verify success message displays");
		Assert.assertEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");

	}

	@Test
	public void TC_02_Login_Empty_Data() {

		System.out.println("Login_02 - Step 01: Click to the Login link");

		homePage = PageGeneratorManager.getUserHomePage(driver);
		homePage.clickToHeaderLinkByName("Log out");
		// loginPage = homePage.clickToLoginLink();
		homePage.clickToHeaderLinkByName("Log in");
		loginPage = PageGeneratorManager.getUserLoginPage(driver);

		System.out.println("Login_02 - Step 02: Click to the Login button");

		loginPage.clickToButtonByText("Log in");

		System.out.println("Login_02 - Step 03: Verify error messages display");
		// Assert.assertEquals(loginPage.getErrorMessageAtEmailTextbox(), "Please enter your email");
		Assert.assertEquals(loginPage.getTextboxErrorMessageByID("Email"), "Please enter your email");
	}

	@Test
	public void TC_03_Login_Invalid_Email() {

		System.out.println("Login_03 - Step 01: Click to the Login link");

		homePage = PageGeneratorManager.getUserHomePage(driver);
		// loginPage = homePage.clickToLoginLink();
		homePage.clickToHeaderLinkByName("Log in");
		loginPage = PageGeneratorManager.getUserLoginPage(driver);

		System.out.println("Login_03 - Step 02: Input invalid email");

		loginPage.inputToTextboxByID("Email", invalidEmail);

		System.out.println("Login_03 - Step 03: Click to the Login button");
		loginPage.clickToButtonByText("Log in");

		System.out.println("Login_03 - Step 04: Verify error messages display");
		Assert.assertEquals(loginPage.getTextboxErrorMessageByID("Email"), "Please enter a valid email address.");
	}

	@Test
	public void TC_04_Login_NotFound_Email() {

		System.out.println("Login_04 - Step 01: Click to the Login link");

		homePage = PageGeneratorManager.getUserHomePage(driver);
		// loginPage = homePage.clickToLoginLink();
		homePage.clickToHeaderLinkByName("Log in");
		loginPage = PageGeneratorManager.getUserLoginPage(driver);

		System.out.println("Login_04 - Step 02: Input Not Found email");
		loginPage.inputToTextboxByID("Email", notFoundEmail);

		System.out.println("Login_04 - Step 03: Click to the Login button");
		loginPage.clickToButtonByText("Log in");

		System.out.println("Login_04 - Step 04: Verify error messages display");
		Assert.assertEquals(loginPage.getValidationErrorMessage(), "Login was unsuccessful. Please correct the errors and try again.\nNo customer account found");
	}

	@Test
	public void TC_05_Existing_Email_Empty_Password() {

		System.out.println("Login_05 - Step 01: Click to the Login link");

		homePage = PageGeneratorManager.getUserHomePage(driver);
		// loginPage = homePage.clickToLoginLink();
		homePage.clickToHeaderLinkByName("Log in");
		loginPage = PageGeneratorManager.getUserLoginPage(driver);

		System.out.println("Login_05 - Step 02: Input existing email");
		loginPage.inputToTextboxByID("Email", validEmail);

		System.out.println("Login_05 - Step 03: Click to the Login button");
		loginPage.clickToButtonByText("Log in");

		System.out.println("Login_05 - Step 04: Verify error messages display");
		Assert.assertEquals(loginPage.getValidationErrorMessage(), "Login was unsuccessful. Please correct the errors and try again.\nThe credentials provided are incorrect");
	}

	@Test
	public void TC_06_Existing_Email_Incorrect_Password() {

		System.out.println("Login_06 - Step 01: Click to the Login link");

		homePage = PageGeneratorManager.getUserHomePage(driver);
		// loginPage = homePage.clickToLoginLink();
		homePage.clickToHeaderLinkByName("Log in");
		loginPage = PageGeneratorManager.getUserLoginPage(driver);

		System.out.println("Login_06 - Step 02: Input existing email");
		loginPage.inputToTextboxByID("Email", validEmail);

		System.out.println("Login_06 - Step 03: Input invalid password");
		loginPage.inputToTextboxByID("Password", incorrectPassword);

		System.out.println("Login_06 - Step 04: Click to the Login button");
		loginPage.clickToButtonByText("Log in");

		System.out.println("Login_06 - Step 05: Verify error messages display");
		Assert.assertEquals(loginPage.getValidationErrorMessage(), "Login was unsuccessful. Please correct the errors and try again.\nThe credentials provided are incorrect");
	}

	@Test
	public void TC_07_Login_Success() {

		System.out.println("Login_06 - Step 01: Click to the Login link");

		homePage = PageGeneratorManager.getUserHomePage(driver);
		// loginPage = homePage.clickToLoginLink();
		homePage.clickToHeaderLinkByName("Log in");
		loginPage = PageGeneratorManager.getUserLoginPage(driver);

		System.out.println("Login_07 - Step 02: Input existing email");
		loginPage.inputToTextboxByID("Email", validEmail);

		System.out.println("Login_07 - Step 03: Input valid password");
		loginPage.inputToTextboxByID("Password", correctPassword);

		System.out.println("Login_07 - Step 04: Click to the Login button");
		loginPage.clickToButtonByText("Log in");

		System.out.println("Login_07 - Step 05: Verify Log out link display");

		homePage = new UserHomePageObject(driver);
		// Assert.assertTrue(homePage.isLogoutLinkDisplayed());
		// Assert.assertTrue(homePage.isMyAccountLinkDisplayed());
		Assert.assertTrue(homePage.isHeaderLinkDisplayedByName("Log out"));
		Assert.assertTrue(homePage.isHeaderLinkDisplayedByName("My account"));
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
