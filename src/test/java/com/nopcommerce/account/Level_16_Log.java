package com.nopcommerce.account;

import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import commons.PageGeneratorManager;
import pageObjects.portal.UserHomePageObject;
import pageObjects.portal.UserRegisterPageObject;

public class Level_16_Log extends BaseTest {
	private WebDriver driver;
	private String validEmail, firstName, lastName, correctPassword, confirmPassword;
	private UserHomePageObject homePage;
	private UserRegisterPageObject registerPage;

	@Parameters({ "browser", "environment" })
	@BeforeClass
	public void beforeClass(String browserName, String environmentName) {
		driver = getBrowserDriver(browserName, environmentName);
		homePage = PageGeneratorManager.getUserHomePage(driver);

		firstName = "Tester";
		lastName = "Tester";
		correctPassword = "Tester@123";
		confirmPassword = "Tester@123";
		validEmail = "tester" + getRandomNumber() + "@yopmail.com";

	}

	@Test
	public void User_01_Register_Validate() {
		log.info("User 01 - Step 01: Verify Register link is displayed.");
		verifyFalse(homePage.isRegisterLinkDisplayed());

		log.info("User 01 - Step 02: Click to Register link.");
		registerPage = homePage.clickToRegisterLink();

		log.info("User 01 - Step 03: Click to Register button.");
		registerPage.clickToRegisterButton();
		registerPage.waitInSecond(5);

		log.info("User 01 - Step 04: Verify error message - First Name is displayed.");
		verifyEquals(registerPage.getErrorMessageAtFirstNameTextbox(), "First name is required.");

		log.info("User 01 - Step 05: Verify error message - Last Name is displayed.");
		verifyEquals(registerPage.getErrorMessageAtLastNameTextbox(), "Last name is required");
	}

	@Test
	public void User_02_Register_Success() {
		log.info("User 01 - Step 01: Enter to First Name textbox with value is " + firstName);
		registerPage.refreshCurrentPage(driver);
		registerPage.inputToFirstNameTextbox(firstName);

		log.info("User 01 - Step 02: Enter to Last Name textbox with value is " + lastName);
		registerPage.inputToLastNameTextbox(lastName);

		log.info("User 01 - Step 03: Enter to Email textbox with value is " + validEmail);
		registerPage.inputToEmailTextbox(validEmail);

		log.info("User 01 - Step 04: Enter to Password textbox with value is " + correctPassword);
		registerPage.inputToPasswordTextbox(correctPassword);

		log.info("User 01 - Step 05: Enter to Confirm Password textbox with value is " + confirmPassword);
		registerPage.inputToConfirmPasswordTextbox(confirmPassword);

		log.info("User 01 - Step 06: Click to Register button.");
		registerPage.clickToRegisterButton();

		log.info("User 01 - Step 07: Verify Register completed.");
		verifyEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");
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
