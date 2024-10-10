package com.nopcommerce.account;

import commons.BaseTest;
import commons.PageGeneratorManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.portal.UserHomePageObject;
import pageObjects.portal.UserSearchPageObject;

import java.lang.reflect.Method;

public class Level_33_BrowserStack extends BaseTest {
	private WebDriver driver;
	private UserHomePageObject homePage;
	private UserSearchPageObject searchPage;

	@Parameters({ "browser", "environment" })
	@BeforeMethod
	public void beforeMethod(String browserName, String environmentName) {
		driver = getBrowserDriver(browserName, environmentName);
		homePage = PageGeneratorManager.getUserHomePage(driver);
		searchPage = homePage.clickToSearchLink();
	}

	@Test
	public void Search_01_shouldBeSearchByEmptyData(Method method) {
		searchPage.enterToSearchTextbox("");
		searchPage.clickToSearchButton();
	}

	@Test
	public void Search_02_shouldBeSearchByNotExistedProductName(Method method) {
		searchPage.enterToSearchTextbox("Macbook Pro 2050");
		searchPage.clickToSearchButton();
	}

	@Test
	public void Search_03_shouldBeSearchByContainedProductName(Method method) {
		searchPage.enterToSearchTextbox("Lenovo");
		searchPage.clickToSearchButton();
	}

	@Test
	public void Search_04_shouldBeSearchByProductName(Method method) {
		searchPage.enterToSearchTextbox("ThinkPad X1 Carbon");
		searchPage.clickToSearchButton();
	}

	@AfterMethod
	public void afterMethod() {
		driver.quit();
	}
}
