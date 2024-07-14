package com.nopcommerce.account;

import java.time.Duration;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import commons.BasePage;
import commons.GlobalConstants;

public class Level_04_Apply_BasePage_III extends BasePage {
	WebDriver driver;

	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	String email;

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

	}

	@Test
	public void TC_01_Register_Empty_Data() {
		waitForElementClickable(driver, "//a[@class='ico-register']");
		clickToElement(driver, "//a[@class='ico-register']");

		waitForElementClickable(driver, "//button[@id='register-button']");
		clickToElement(driver, "//button[@id='register-button']");

		Assert.assertEquals(getElementText(driver, "//span[@id='FirstName-error']"), "First name is required.");

		Assert.assertEquals(getElementText(driver, "//span[@id='LastName-error']"), "Last name is required.");

		Assert.assertEquals(getElementText(driver, "//span[@id='Email-error']"), "Email is required.");

		Assert.assertEquals(getElementText(driver, "//span[@id='Password-error']"), "Password is required.");

		Assert.assertEquals(getElementText(driver, "//span[@id='ConfirmPassword-error']"), "Password is required.");

	}

	@Test
	public void TC_02_Register_Invalid_Email() {

		waitForElementClickable(driver, "//a[@class='ico-register']");
		clickToElement(driver, "//a[@class='ico-register']");

		sendKeyToElement(driver, "//input[@id='FirstName']", "Tester");

		sendKeyToElement(driver, "//input[@id='LastName']", "Tester");

		sendKeyToElement(driver, "//input[@id='Email']", "abc#xyz");

		sendKeyToElement(driver, "//input[@id='Password']", "Tester@123");

		sendKeyToElement(driver, "//input[@id='ConfirmPassword']", "Tester@123");

		clickToElement(driver, "//button[@id='register-button']");

		Assert.assertEquals(getElementText(driver, "//span[@id='Email-error']"), "Wrong email");

	}

	@Test
	public void TC_03_Register_Success() {

		waitForElementClickable(driver, "//a[@class='ico-register']");
		clickToElement(driver, "//a[@class='ico-register']");

		sendKeyToElement(driver, "//input[@id='FirstName']", "Tester");

		sendKeyToElement(driver, "//input[@id='LastName']", "Tester");

		email = "tester" + getRandomNumber() + "@yopmail.com";

		sendKeyToElement(driver, "//input[@id='Email']", email);

		sendKeyToElement(driver, "//input[@id='Password']", "Tester@123");

		sendKeyToElement(driver, "//input[@id='ConfirmPassword']", "Tester@123");

		clickToElement(driver, "//button[@id='register-button']");

		Assert.assertEquals(getElementText(driver, "//div[@class='result']"), "Your registration completed");

	}

	@Test
	public void TC_04_Register_Existing_Email() {

		waitForElementClickable(driver, "//a[@class='ico-register']");
		clickToElement(driver, "//a[@class='ico-register']");

		sendKeyToElement(driver, "//input[@id='FirstName']", "Tester");

		sendKeyToElement(driver, "//input[@id='LastName']", "Tester");

		sendKeyToElement(driver, "//input[@id='Email']", email);

		sendKeyToElement(driver, "//input[@id='Password']", "Tester@123");

		sendKeyToElement(driver, "//input[@id='ConfirmPassword']", "Tester@123");

		clickToElement(driver, "//button[@id='register-button']");

		waitUntilElementVisible(driver, "//div[@class='message-error validation-summary-errors']//li");

		Assert.assertEquals(getElementText(driver, "//div[@class='message-error validation-summary-errors']//li"), "The specified email already exists");

	}

	@Test
	public void TC_05_Register_Password_Less_Than_6_Chars() {

		waitForElementClickable(driver, "//a[@class='ico-register']");
		clickToElement(driver, "//a[@class='ico-register']");

		sendKeyToElement(driver, "//input[@id='FirstName']", "Tester");

		sendKeyToElement(driver, "//input[@id='LastName']", "Tester");

		email = "tester" + getRandomNumber() + "@yopmail.com";

		sendKeyToElement(driver, "//input[@id='Email']", email);

		sendKeyToElement(driver, "//input[@id='Password']", "Test");

		sendKeyToElement(driver, "//input[@id='ConfirmPassword']", "Test");

		clickToElement(driver, "//button[@id='register-button']");

		Assert.assertEquals(getElementText(driver, "//span[@id='Password-error']"), "Password must meet the following rules:\nmust have at least 6 characters");

	}

	@Test
	public void TC_06_Register_Invalid_Confirm_Password() {

		waitForElementClickable(driver, "//a[@class='ico-register']");
		clickToElement(driver, "//a[@class='ico-register']");

		driver.findElement(By.cssSelector("#FirstName")).sendKeys("Tester");

		driver.findElement(By.cssSelector("#LastName")).sendKeys("Tester");

		sendKeyToElement(driver, "//input[@id='FirstName']", "Tester");

		sendKeyToElement(driver, "//input[@id='LastName']", "Tester");

		email = "tester" + getRandomNumber() + "@yopmail.com";

		sendKeyToElement(driver, "//input[@id='Email']", email);

		sendKeyToElement(driver, "//input[@id='Password']", "Tester@123");

		sendKeyToElement(driver, "//input[@id='ConfirmPassword']", "Tester345");

		clickToElement(driver, "//button[@id='register-button']");

		Assert.assertEquals(getElementText(driver, "//span[@id='ConfirmPassword-error']"), "The password and confirmation password do not match.");

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
