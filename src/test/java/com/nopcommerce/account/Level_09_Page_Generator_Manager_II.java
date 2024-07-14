package com.nopcommerce.account;

import java.time.Duration;
import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import commons.GlobalConstants;
import pageObjects.portal.UserHomePageObject;
import pageObjects.portal.UserLoginPageObject;
import pageObjects.portal.UserRegisterPageObject;

public class Level_09_Page_Generator_Manager_II {
	private WebDriver driver;

	private String projectPath = System.getProperty("user.dir");
	private String osName = System.getProperty("os.name");
	private String validEmail, firstName, lastName, correctPassword, confirmPassword, invalidEmail, notFoundEmail, incorrectPassword;
	private UserHomePageObject homePage;
	private UserLoginPageObject loginPage;
	private UserRegisterPageObject registerPage;

	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Mac OS")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		}

		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT));
		driver.manage().window().maximize();

		driver.get("https://demo.nopcommerce.com/");
		homePage = new UserHomePageObject(driver);

		firstName = "Tester";
		lastName = "Tester";
		correctPassword = "Tester@123";
		incorrectPassword = "123";
		confirmPassword = "Tester@123";
		validEmail = "tester" + getRandomNumber() + "@yopmail.com";
		invalidEmail = "tester@yopmail.com@123";
		notFoundEmail = "testerHello@yopmail.com";

		System.out.println("Pre-condition - Step 01: Click to the Register link");

		registerPage = homePage.clickToRegisterLink();

		System.out.println("Pre-condition - Step 02: Input all fields value");
		registerPage.inputToFirstNameTextbox(firstName);
		registerPage.inputToLastNameTextbox(lastName);
		registerPage.inputToEmailTextbox(validEmail);
		registerPage.inputToPasswordTextbox(correctPassword);
		registerPage.inputToConfirmPasswordTextbox(confirmPassword);

		System.out.println("Pre-condition - Step 03: Click to the Register button");
		registerPage.clickToRegisterButton();

		System.out.println("Pre-condition - Step 04: Verify success message displays");
		Assert.assertEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");

	}

	@Test
	public void Login_01_Login_Empty_Data() {

		System.out.println("Login_01 - Step 01: Click to the Login link");

		loginPage = homePage.clickToLoginLink();

		System.out.println("Login_01 - Step 02: Click to the Login button");

		loginPage.clickToLoginButton();

		System.out.println("Login_01 - Step 03: Verify error messages display");
		Assert.assertEquals(loginPage.getErrorMessageAtEmailTextbox(), "Please enter your email");

	}

	@Test
	public void Login_02_Login_Invalid_Email() {

		System.out.println("Login_02 - Step 01: Click to the Login link");

		loginPage = homePage.clickToLoginLink();

		System.out.println("Login_02 - Step 02: Input invalid email");

		loginPage.inputToEmail(invalidEmail);

		System.out.println("Login_02 - Step 03: Click to the Login button");
		loginPage.clickToLoginButton();

		System.out.println("Login_02 - Step 04: Verify error messages display");
		Assert.assertEquals(loginPage.getErrorMessageAtEmailTextbox(), "Wrong email");
	}

	@Test
	public void Login_03_Login_NotFound_Email() {

		System.out.println("Login_03 - Step 01: Click to the Login link");

		loginPage = homePage.clickToLoginLink();

		System.out.println("Login_03 - Step 02: Input Not Found email");
		loginPage.inputToEmail(notFoundEmail);

		System.out.println("Login_03 - Step 03: Click to the Login button");
		loginPage.clickToLoginButton();

		System.out.println("Login_03 - Step 04: Verify error messages display");
		Assert.assertEquals(loginPage.getValidationErrorMessage(), "Login was unsuccessful. Please correct the errors and try again.\nNo customer account found");
	}

	@Test
	public void Login_04_Existing_Email_Empty_Password() {

		System.out.println("Login_04 - Step 01: Click to the Login link");

		loginPage = homePage.clickToLoginLink();

		System.out.println("Login_04 - Step 02: Input existing email");
		loginPage.inputToEmail(validEmail);

		System.out.println("Login_04 - Step 03: Click to the Login button");
		loginPage.clickToLoginButton();

		System.out.println("Login_04 - Step 04: Verify error messages display");
		Assert.assertEquals(loginPage.getValidationErrorMessage(), "Login was unsuccessful. Please correct the errors and try again.\nThe credentials provided are incorrect");
	}

	@Test
	public void Login_05_Existing_Email_Incorrect_Password() {

		System.out.println("Login_05 - Step 01: Click to the Login link");

		loginPage = homePage.clickToLoginLink();

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

		System.out.println("Login_06 - Step 01: Click to the Login link");

		loginPage = homePage.clickToLoginLink();

		System.out.println("Login_06 - Step 02: Input existing email");
		loginPage.inputToEmail(validEmail);

		System.out.println("Login_06 - Step 03: Input valid password");
		loginPage.inputToPassword(correctPassword);

		System.out.println("Login_06 - Step 04: Click to the Login button");
		loginPage.clickToLoginButton();

		System.out.println("Login_06 - Step 05: Verify Log out link display");

		homePage = new UserHomePageObject(driver);
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
