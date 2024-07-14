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

public class Level_15_Assert_Verify extends BaseTest {
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
	public void User_01_Register() {
		verifyFalse(homePage.isRegisterLinkDisplayed());
		registerPage = homePage.clickToRegisterLink();

		registerPage.clickToRegisterButton();
		registerPage.waitInSecond(5);
		verifyEquals(registerPage.getErrorMessageAtFirstNameTextbox(), "First name is required.");
		verifyEquals(registerPage.getErrorMessageAtLastNameTextbox(), "Last name is required");

		registerPage.inputToFirstNameTextbox(firstName);
		registerPage.inputToLastNameTextbox(lastName);
		registerPage.inputToEmailTextbox(validEmail);
		registerPage.inputToPasswordTextbox(correctPassword);
		registerPage.inputToConfirmPasswordTextbox(confirmPassword);
		registerPage.clickToRegisterButton();
		verifyEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed.");

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
