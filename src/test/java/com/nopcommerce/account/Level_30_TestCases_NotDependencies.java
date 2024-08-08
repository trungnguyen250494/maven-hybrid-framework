package com.nopcommerce.account;

import com.aventstack.extentreports.Status;
import commons.BaseTest;
import commons.PageGeneratorManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import pageObjects.portal.*;
import reportConfig.ExtentTestManager;

import java.lang.reflect.Method;

public class Level_30_TestCases_NotDependencies extends BaseTest {
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
