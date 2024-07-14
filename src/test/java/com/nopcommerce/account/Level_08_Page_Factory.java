package com.nopcommerce.account;

import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import pageFactory.HomePageObject;
import pageFactory.LoginPageObject;
import pageFactory.RegisterPageObject;

public class Level_08_Page_Factory extends BaseTest {
	private WebDriver driver;
	private String validEmail, firstName, lastName, correctPassword, confirmPassword, invalidEmail, notFoundEmail, incorrectPassword;
	private HomePageObject homePage;
	private LoginPageObject loginPage;
	private RegisterPageObject registerPage;

	@Parameters({ "browser", "environment" })
	@BeforeClass
	public void beforeClass(String browserName, String environmentName) {
		driver = getBrowserDriver(browserName, environmentName);
		homePage = new HomePageObject(driver);

		firstName = "Tester";
		lastName = "Tester";
		correctPassword = "Tester@123";
		incorrectPassword = "123";
		confirmPassword = "Tester@123";
		validEmail = "tester" + getRandomNumber() + "@yopmail.com";
		invalidEmail = "tester@yopmail.com@123";
		notFoundEmail = "testerHello@yopmail.com";

		System.out.println("Pre-condition - Step 01: Click to the Register link");
		homePage.clickToRegisterLink();

		registerPage = new RegisterPageObject(driver);

		System.out.println("Pre-condition - Step 02: Input all fields value");
		registerPage.inputToFirstNameTextbox(firstName);
		registerPage.inputToLasttNameTextbox(lastName);
		registerPage.inputToEmailTextbox(validEmail);
		registerPage.scrollToPassword();
		registerPage.inputToPasswordTextbox(correctPassword);
		registerPage.inputToConfirmPasswordTextbox(confirmPassword);

		System.out.println("Pre-condition - Step 03: Click to the Register button");
		registerPage.clickToRegisterButton();

		System.out.println("Pre-condition - Step 04: Verify success message displays");
		Assert.assertEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");

	}

	@Test
	public void Login_01_Login_Empty_Data() {

		loginPage = new LoginPageObject(driver);
		System.out.println("Login_01 - Step 01: Click to the Login link");

		homePage.clickToLoginLink();

		System.out.println("Login_01 - Step 02: Click to the Login button");

		loginPage.clickToLoginButton();

		System.out.println("Login_01 - Step 03: Verify error messages display");
		Assert.assertEquals(loginPage.getErrorMessageAtEmailTextbox(), "Please enter your email");

	}

	@Test
	public void Login_02_Login_Invalid_Email() {

		loginPage = new LoginPageObject(driver);
		System.out.println("Login_02 - Step 01: Click to the Login link");

		homePage.clickToLoginLink();

		System.out.println("Login_02 - Step 02: Input invalid email");

		loginPage.inputToEmail(invalidEmail);

		System.out.println("Login_02 - Step 03: Click to the Login button");
		loginPage.clickToLoginButton();

		System.out.println("Login_02 - Step 04: Verify error messages display");
		Assert.assertEquals(loginPage.getErrorMessageAtEmailTextbox(), "Wrong email");
	}

	@Test
	public void Login_03_Login_NotFound_Email() {

		loginPage = new LoginPageObject(driver);
		System.out.println("Login_03 - Step 01: Click to the Login link");

		homePage.clickToLoginLink();

		System.out.println("Login_03 - Step 02: Input Not Found email");
		loginPage.inputToEmail(notFoundEmail);

		System.out.println("Login_03 - Step 03: Click to the Login button");
		loginPage.clickToLoginButton();

		System.out.println("Login_03 - Step 04: Verify error messages display");
		Assert.assertEquals(loginPage.getValidationErrorMessage(), "Login was unsuccessful. Please correct the errors and try again.\nNo customer account found");
	}

	@Test
	public void Login_04_Existing_Email_Empty_Password() {

		loginPage = new LoginPageObject(driver);
		System.out.println("Login_04 - Step 01: Click to the Login link");

		homePage.clickToLoginLink();

		System.out.println("Login_04 - Step 02: Input existing email");
		loginPage.inputToEmail(validEmail);

		System.out.println("Login_04 - Step 03: Click to the Login button");
		loginPage.clickToLoginButton();

		System.out.println("Login_04 - Step 04: Verify error messages display");
		Assert.assertEquals(loginPage.getValidationErrorMessage(), "Login was unsuccessful. Please correct the errors and try again.\nThe credentials provided are incorrect");
	}

	@Test
	public void Login_05_Existing_Email_Incorrect_Password() {

		loginPage = new LoginPageObject(driver);
		System.out.println("Login_05 - Step 01: Click to the Login link");

		homePage.clickToLoginLink();

		System.out.println("Login_05 - Step 02: Input existing email");
		loginPage.inputToEmail(validEmail);

		System.out.println("Login_05 - Step 03: Input invalid password");
		loginPage.inputToPassword(incorrectPassword);

		System.out.println("Login_05 - Step 04: Click to the Login button");
		loginPage.clickToLoginButton();

		System.out.println("Login_05 - Step 05: Verify error messages display");
		Assert.assertEquals(loginPage.getValidationErrorMessage(), "Login was unsuccessful. Please correct the errors and try again.\nThe credentials provided are incorrect");
	}

	@Test
	public void Login_06_Login_Success() {

		loginPage = new LoginPageObject(driver);
		System.out.println("Login_06 - Step 01: Click to the Login link");

		homePage.clickToLoginLink();

		System.out.println("Login_06 - Step 02: Input existing email");
		loginPage.inputToEmail(validEmail);

		System.out.println("Login_06 - Step 03: Input valid password");
		loginPage.inputToPassword(correctPassword);

		System.out.println("Login_06 - Step 04: Click to the Login button");
		loginPage.clickToLoginButton();

		System.out.println("Login_06 - Step 05: Verify Log out link display");

		homePage = new HomePageObject(driver);
		Assert.assertTrue(homePage.isLogoutLinkDisplayed());
		Assert.assertTrue(homePage.isMyAccountLinkDisplayed());
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
