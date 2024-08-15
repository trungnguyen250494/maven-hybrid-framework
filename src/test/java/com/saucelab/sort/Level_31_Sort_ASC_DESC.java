package com.saucelab.sort;

import commons.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.saucelab.LoginPageObject;
import pageObjects.saucelab.PageGeneratorManager;
import pageObjects.saucelab.ProductPageObject;

public class Level_31_Sort_ASC_DESC extends BaseTest {
	private WebDriver driver;
	private LoginPageObject loginPage;
	private ProductPageObject productPage;

	@Parameters({ "browser", "url" })
	@BeforeMethod
	public void beforeMethod(String browserName, String url) {
		driver = getBrowserDriverWithUrl(browserName,url);
		loginPage = PageGeneratorManager.getLoginPage(driver);
		loginPage.enterToUsername("standard_user");
		loginPage.enterToPassword("secret_sauce");
		productPage = loginPage.clickToLoginButton();
	}

	@Test
	public void Sort_01_Name() {
		productPage.selectItemInSortDropdown("Name (A to Z)");
		Assert.assertTrue(productPage.isProductNameSortedByAscending());
		productPage.selectItemInSortDropdown("Name (Z to A)");
		Assert.assertTrue(productPage.isProductNameSortedByDescending());
	}

	@Test
	public void Sort_02_Price() {
		productPage.selectItemInSortDropdown("Price (low to high)");
		Assert.assertTrue(productPage.isProductPriceSortedByLowToHigh());
		productPage.selectItemInSortDropdown("Price (high to low)");
		Assert.assertTrue(productPage.isProductPriceSortedByHighToLow());
	}

	@AfterMethod
	public void afterMethod() {
		driver.quit();
	}
}
