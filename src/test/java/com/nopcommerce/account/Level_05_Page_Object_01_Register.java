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
import pageObjects.portal.UserRegisterPageObject;

public class Level_05_Page_Object_01_Register {
	private WebDriver driver;

	private String projectPath = System.getProperty("user.dir");
	private String osName = System.getProperty("os.name");
	private String email, firstName, lastName, password, confirmPassword;
	private UserHomePageObject homePage;
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
		password = "Tester@123";
		confirmPassword = "Tester@123";
		email = "tester" + getRandomNumber() + "@yopmail.com";

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
		registerPage.inputToEmailTextbox("abc#xyz");
		registerPage.inputToPasswordTextbox(password);
		registerPage.inputToConfirmPasswordTextbox(confirmPassword);

		System.out.println("Register_02 - Step 03: Click to the Register button");
		registerPage.clickToRegisterButton();

		System.out.println("Register_02 - Step 04: Verify wrong email - error message displays");
		Assert.assertEquals(registerPage.getErrorMessageAtEmailTextbox(), "Wrong email");

	}

	@Test
	public void Register_03_Register_Success() {

		System.out.println("Register_03 - Step 01: Click to the Register link");
		homePage.clickToRegisterLink();
		registerPage = new UserRegisterPageObject(driver);

		System.out.println("Register_03 - Step 02: Input all fields value");
		registerPage.inputToFirstNameTextbox(firstName);
		registerPage.inputToLastNameTextbox(lastName);
		registerPage.inputToEmailTextbox(email);
		registerPage.inputToPasswordTextbox(password);
		registerPage.inputToConfirmPasswordTextbox(confirmPassword);

		System.out.println("Register_03 - Step 03: Click to the Register button");
		registerPage.clickToRegisterButton();

		System.out.println("Register_03 - Step 04: Verify success message displays");
		Assert.assertEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");

	}

	@Test
	public void Register_04_Register_Existing_Email() {

		System.out.println("Register_04 - Step 01: Click to the Register link");
		homePage.clickToRegisterLink();
		registerPage = new UserRegisterPageObject(driver);

		System.out.println("Register_04 - Step 02: Input all fields value");
		registerPage.inputToFirstNameTextbox(firstName);
		registerPage.inputToLastNameTextbox(lastName);
		registerPage.inputToEmailTextbox(email);
		registerPage.inputToPasswordTextbox(password);
		registerPage.inputToConfirmPasswordTextbox(confirmPassword);

		System.out.println("Register_04 - Step 03: Click to the Register button");
		registerPage.clickToRegisterButton();

		System.out.println("Register_04 - Step 04: Verify existing email - error message displays");
		Assert.assertEquals(registerPage.getExistingEmailErrorMessage(), "The specified email already exists");

	}

	@Test
	public void Register_05_Register_Password_Less_Than_6_Chars() {

		System.out.println("Register_05 - Step 01: Click to the Register link");
		homePage.clickToRegisterLink();
		registerPage = new UserRegisterPageObject(driver);

		System.out.println("Register_05 - Step 02: Input all fields value");
		registerPage.inputToFirstNameTextbox(firstName);
		registerPage.inputToLastNameTextbox(lastName);
		registerPage.inputToEmailTextbox(email);
		registerPage.inputToPasswordTextbox("Test");
		registerPage.inputToConfirmPasswordTextbox("Test");

		System.out.println("Register_05 - Step 03: Click to the Register button");
		registerPage.clickToRegisterButton();

		System.out.println("Register_05 - Step 04: Verify password - error message displays");
		Assert.assertEquals(registerPage.getErrorMessageAtPasswordTextbox(), "Password must meet the following rules:\nmust have at least 6 characters");

	}

	@Test
	public void Register_06_Register_Invalid_Confirm_Password() {

		System.out.println("Register_06 - Step 01: Click to the Register link");
		homePage.clickToRegisterLink();
		registerPage = new UserRegisterPageObject(driver);

		System.out.println("Register_06 - Step 02: Input all fields value");
		registerPage.inputToFirstNameTextbox(firstName);
		registerPage.inputToLastNameTextbox(lastName);
		registerPage.inputToEmailTextbox(email);
		registerPage.inputToPasswordTextbox(password);
		registerPage.inputToConfirmPasswordTextbox("Tester@456");

		System.out.println("Register_06 - Step 03: Click to the Register button");
		registerPage.clickToRegisterButton();

		System.out.println("Register_06 - Step 04: Verify confirm password - error message displays");
		Assert.assertEquals(registerPage.getErrorMessageAtConfirmPasswordTextbox(), "The password and confirmation password do not match.");

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
