package com.nopcommerce.share;

import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import commons.BaseTest;
import commons.PageGeneratorManager;
import pageObjects.portal.UserHomePageObject;
import pageObjects.portal.UserRegisterPageObject;

public class Common_Register extends BaseTest {
	private WebDriver driver;
	public static String validEmail, firstName, lastName, correctPassword, confirmPassword;
	private UserHomePageObject homePage;
	private UserRegisterPageObject registerPage;

	@Parameters({ "browser", "environment" })
	@BeforeTest
	public void beforeTest(String browserName, String environmentName) {
		driver = getBrowserDriver(browserName, environmentName);
		homePage = PageGeneratorManager.getUserHomePage(driver);

		firstName = "Tester";
		lastName = "Tester";
		correctPassword = "Tester@123";
		confirmPassword = "Tester@123";
		validEmail = "tester" + getRandomNumber() + "@yopmail.com";

		System.out.println("Register_01 - Step 01: Click to the Register link");

		homePage.clickToHeaderLinkByName("Register");
		registerPage = PageGeneratorManager.getUserRegisterPage(driver);

		System.out.println("Register_01 - Step 02: Input all fields value");

		registerPage.inputToTextboxByID("FirstName", firstName);
		registerPage.inputToTextboxByID("LastName", lastName);
		registerPage.inputToTextboxByID("Email", validEmail);
		registerPage.inputToTextboxByID("Password", correctPassword);
		registerPage.inputToTextboxByID("ConfirmPassword", confirmPassword);

		System.out.println("Register_01 - Step 03: Click to the Register button");
		registerPage.clickToButtonByText("Register");

		System.out.println("Register_01 - Step 04: Verify success message displays");
		Assert.assertEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");

		driver.quit();

	}

	public int getRandomNumber() {
		Random rd = new Random();
		return rd.nextInt(1000);
	}
}
