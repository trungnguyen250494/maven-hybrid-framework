package com.nopcommerce.account;

import java.time.Duration;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Level_01_Register_DRY {

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
		driver.manage().window().maximize();

	}

	@Test
	public void TC_01_Register_Empty_Data() {
		driver.get("https://demo.nopcommerce.com/");

		driver.findElement(By.cssSelector(".ico-register")).click();

		driver.findElement(By.cssSelector("#register-button")).click();

		Assert.assertEquals(driver.findElement(By.cssSelector("#FirstName-error")).getText(), "First name is required.");

		Assert.assertEquals(driver.findElement(By.cssSelector("#LastName-error")).getText(), "Last name is required.");

		Assert.assertEquals(driver.findElement(By.cssSelector("#Email-error")).getText(), "Email is required.");

		Assert.assertEquals(driver.findElement(By.cssSelector("#Password-error")).getText(), "Password is required.");

		Assert.assertEquals(driver.findElement(By.cssSelector("#ConfirmPassword-error")).getText(), "Password is required.");

	}

	@Test
	public void TC_02_Register_Invalid_Email() {
		driver.get("https://demo.nopcommerce.com/");

		driver.findElement(By.cssSelector(".ico-register")).click();

		driver.findElement(By.cssSelector("#FirstName")).sendKeys("Tester");

		driver.findElement(By.cssSelector("#LastName")).sendKeys("Tester");

		driver.findElement(By.cssSelector("#Email")).sendKeys("abc#xyz");

		driver.findElement(By.cssSelector("#Password")).sendKeys("Tester@123");

		driver.findElement(By.cssSelector("#ConfirmPassword")).sendKeys("Tester@123");

		driver.findElement(By.cssSelector("#register-button")).click();

		Assert.assertEquals(driver.findElement(By.cssSelector("#Email-error")).getText(), "Wrong email");

	}

	@Test
	public void TC_03_Register_Success() {
		driver.get("https://demo.nopcommerce.com/");

		driver.findElement(By.cssSelector(".ico-register")).click();

		driver.findElement(By.cssSelector("#FirstName")).sendKeys("Tester");

		driver.findElement(By.cssSelector("#LastName")).sendKeys("Tester");

		email = "tester" + getRandomNumber() + "@yopmail.com";

		driver.findElement(By.cssSelector("#Email")).sendKeys(email);

		driver.findElement(By.cssSelector("#Password")).sendKeys("Tester@123");

		driver.findElement(By.cssSelector("#ConfirmPassword")).sendKeys("Tester@123");

		driver.findElement(By.cssSelector("#register-button")).click();

		Assert.assertEquals(driver.findElement(By.cssSelector("div.result")).getText(), "Your registration completed");

		System.out.println(email);

	}

	@Test
	public void TC_04_Register_Existing_Email() {
		driver.get("https://demo.nopcommerce.com/");

		driver.findElement(By.cssSelector(".ico-register")).click();

		driver.findElement(By.cssSelector("#FirstName")).sendKeys("Tester");

		driver.findElement(By.cssSelector("#LastName")).sendKeys("Tester");

		driver.findElement(By.cssSelector("#Email")).sendKeys(email);

		driver.findElement(By.cssSelector("#Password")).sendKeys("Tester@123");

		driver.findElement(By.cssSelector("#ConfirmPassword")).sendKeys("Tester@123");

		driver.findElement(By.cssSelector("#register-button")).click();

		WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(30));
		explicitWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("div.validation-summary-errors li"))));

		Assert.assertEquals(driver.findElement(By.cssSelector("div.validation-summary-errors li")).getText(), "The specified email already exists");

		System.out.println(email);

	}

	@Test
	public void TC_05_Register_Password_Less_Than_6_Chars() {
		driver.get("https://demo.nopcommerce.com/");

		driver.findElement(By.cssSelector(".ico-register")).click();

		driver.findElement(By.cssSelector("#FirstName")).sendKeys("Tester");

		driver.findElement(By.cssSelector("#LastName")).sendKeys("Tester");

		email = "tester" + getRandomNumber() + "@yopmail.com";

		driver.findElement(By.cssSelector("#Email")).sendKeys("test" + getRandomNumber() + "@yopmail.com");

		driver.findElement(By.cssSelector("#Password")).sendKeys("Test");

		driver.findElement(By.cssSelector("#ConfirmPassword")).sendKeys("Test");

		driver.findElement(By.cssSelector("#register-button")).click();

		Assert.assertEquals(driver.findElement(By.cssSelector("span#Password-error")).getText(), "Password must meet the following rules:\nmust have at least 6 characters");

	}

	@Test
	public void TC_06_Register_Invalid_Confirm_Password() {
		driver.get("https://demo.nopcommerce.com/");

		driver.findElement(By.cssSelector(".ico-register")).click();

		driver.findElement(By.cssSelector("#FirstName")).sendKeys("Tester");

		driver.findElement(By.cssSelector("#LastName")).sendKeys("Tester");

		email = "tester" + getRandomNumber() + "@yopmail.com";

		driver.findElement(By.cssSelector("#Email")).sendKeys("test" + getRandomNumber() + "@yopmail.com");

		driver.findElement(By.cssSelector("#Password")).sendKeys("Tester@123");

		driver.findElement(By.cssSelector("#ConfirmPassword")).sendKeys("Tester@456");

		driver.findElement(By.cssSelector("#register-button")).click();

		Assert.assertEquals(driver.findElement(By.cssSelector("#ConfirmPassword-error")).getText(), "The password and confirmation password do not match.");

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
