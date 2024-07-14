package com.nopcommerce.cookie;

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

public class Level_22_Login extends BaseTest {
	private WebDriver driver;
	private UserHomePageObject homePage;

	@Parameters({ "browser", "environment" })
	@BeforeClass
	public void beforeClass(String browserName, String environmentName) {
		driver = getBrowserDriver(browserName, environmentName);
		homePage = PageGeneratorManager.getUserHomePage(driver);
	}

	@Test
	public void TC_01_Login_Success() {

		System.out.println("Login_01 - Step 01: Click to the Login link");

		homePage = PageGeneratorManager.getUserHomePage(driver);

		homePage.setCookies(driver, Common_Register.cookies);
		homePage.waitInSecond(5);
		homePage.refreshCurrentPage(driver);

		Assert.assertTrue(homePage.isHeaderLinkDisplayedByName("Log out"));
		Assert.assertTrue(homePage.isHeaderLinkDisplayedByName("My account"));
		homePage.waitInSecond(5);
	}

	public int getRandomNumber() {
		Random rd = new Random();
		return rd.nextInt(1000);
	}

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		closeBrowserDriver();
	}
}
