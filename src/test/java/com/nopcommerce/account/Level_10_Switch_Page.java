package com.nopcommerce.account;

import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import commons.PageGeneratorManager;
import pageObjects.portal.UserAddressPageObject;
import pageObjects.portal.UserCustomerPageObject;
import pageObjects.portal.UserHomePageObject;
import pageObjects.portal.UserLoginPageObject;
import pageObjects.portal.UserProductReviewPageObject;
import pageObjects.portal.UserRegisterPageObject;
import pageObjects.portal.UserRewardPointPageObject;

public class Level_10_Switch_Page extends BaseTest {
	private WebDriver driver;
	private String validEmail, firstName, lastName, correctPassword, confirmPassword;
	private UserHomePageObject homePage;
	private UserLoginPageObject loginPage;
	private UserRegisterPageObject registerPage;
	private UserCustomerPageObject customerInfoPage;
	private UserAddressPageObject addressPage;
	private UserProductReviewPageObject productReviewPage;
	private UserRewardPointPageObject rewardPointPage;

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
		registerPage = homePage.clickToRegisterLink();
		registerPage.inputToFirstNameTextbox(firstName);
		registerPage.inputToLastNameTextbox(lastName);
		registerPage.inputToEmailTextbox(validEmail);
		registerPage.inputToPasswordTextbox(correctPassword);
		registerPage.inputToConfirmPasswordTextbox(confirmPassword);
		registerPage.clickToRegisterButton();
		Assert.assertEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");
	}

	@Test
	public void User_02_Login() {
		loginPage = homePage.clickToLoginLink();
		loginPage.inputToEmail(validEmail);
		loginPage.inputToPassword(correctPassword);
		loginPage.clickToLoginButton();
		homePage = new UserHomePageObject(driver);
		Assert.assertTrue(homePage.isLogoutLinkDisplayed());
		Assert.assertTrue(homePage.isMyAccountLinkDisplayed());
	}

	@Test
	public void User_03_My_Account() {
		customerInfoPage = homePage.clickToMyAccountLink();
		Assert.assertTrue(customerInfoPage.isDisplayed());
		customerInfoPage.uncheckNewsLetter();
		Assert.assertFalse(customerInfoPage.isNewsLetterChecked());
	}

	@Test
	public void User_04_Switch_Page() {
		addressPage = customerInfoPage.openAddressPage();
		productReviewPage = addressPage.openProductReviewPage();
		rewardPointPage = productReviewPage.openRewardPointPage();
		customerInfoPage = rewardPointPage.openCustomerInfoPage();
		Assert.assertTrue(customerInfoPage.isNewsLetterChecked());
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
