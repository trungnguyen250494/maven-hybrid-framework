package com.nopcommerce.account;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import pageObjects.portal.UserHomePageObject;
import pageObjects.portal.UserRegisterPageObject;

public class Level_07_Multiple_Browsers extends BaseTest {
	private WebDriver driver;
	private String firstName, lastName, password, confirmPassword;
	private UserHomePageObject homePage;
	private UserRegisterPageObject registerPage;

	@Parameters({ "browser", "environment" })
	@BeforeClass
	public void beforeClass(String browserName, String environmentName) {
		driver = getBrowserDriver(browserName, environmentName);
		homePage = new UserHomePageObject(driver);

		firstName = "Tester";
		lastName = "Tester";
		password = "Tester@123";
		confirmPassword = "Tester@123";

	}

	@Test
	public void Register_01_Register_Empty_Data() {

		System.out.println("Register_01 - Step 01: Click to the Register link");

		homePage.clickToRegisterLink();

		registerPage = new UserRegisterPageObject(driver);

		System.out.println("Register_01 - Step 02: Click to the Register button");

		registerPage.clickToRegisterButton();

		System.out.println("Register_01 - Step 03: Verify all error messages display");
		Assert.assertEquals(registerPage.getErrorMessageAtFirstNameTextbox(), "First name is required.");
		Assert.assertEquals(registerPage.getErrorMessageAtLastNameTextbox(), "Last name is required.");
		Assert.assertEquals(registerPage.getErrorMessageAtEmailTextbox(), "Email is required.");
		Assert.assertEquals(registerPage.getErrorMessageAtPasswordTextbox(), "Password is required.");
		Assert.assertEquals(registerPage.getErrorMessageAtConfirmPasswordTextbox(), "Password is required.");

	}

	@Test
	public void Register_02_Register_Invalid_Email() {

		System.out.println("Register_02 - Step 01: Click to the Register link");
		homePage.clickToRegisterLink();
		registerPage = new UserRegisterPageObject(driver);

		System.out.println("Register_02 - Step 02: Input all fields value");
		registerPage.inputToFirstNameTextbox(firstName);
		registerPage.inputToLastNameTextbox(lastName);
		registerPage.scrollToEmail();
		registerPage.inputToEmailTextbox("abc#xyz");
		registerPage.inputToPasswordTextbox(password);
		registerPage.inputToConfirmPasswordTextbox(confirmPassword);

		System.out.println("Register_02 - Step 03: Click to the Register button");
		registerPage.clickToRegisterButton();

		System.out.println("Register_02 - Step 04: Verify wrong email - error message displays");
		Assert.assertEquals(registerPage.getErrorMessageAtEmailTextbox(), "Wrong email");

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
