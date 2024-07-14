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
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import pageObjects.portal.UserCustomerPageObject;
import pageObjects.portal.UserHomePageObject;
import pageObjects.portal.UserLoginPageObject;
import pageObjects.portal.UserRegisterPageObject;

@Epic("Account")
@Feature("Register - Login Account")
public class Level_19_Allure_Report extends BaseTest {
	private WebDriver driver;
	private String validEmail, firstName, lastName, correctPassword;
	private UserHomePageObject homePage;
	private UserRegisterPageObject registerPage;
	private UserLoginPageObject loginPage;
	private UserCustomerPageObject customerInfoPage;

	@Parameters({ "browser", "environment" })
	@BeforeClass
	public void beforeClass(String browserName, String environmentName) {
		driver = getBrowserDriver(browserName, environmentName);
		homePage = PageGeneratorManager.getUserHomePage(driver);
		loginPage = PageGeneratorManager.getUserLoginPage(driver);
		customerInfoPage = PageGeneratorManager.getUserCustomerInfoPage(driver);

		firstName = "Tester";
		lastName = "Tester";
		correctPassword = "Tester@123";
		validEmail = "tester" + getRandomNumber() + "@yopmail.com";
	}

	@Description("User_01_Register")
	@Story("Register")
	@Severity(SeverityLevel.CRITICAL)
	@Test
	public void User_01_Register() {
		registerPage = homePage.clickToRegisterLink();

		registerPage.inputToFirstNameTextbox(firstName);

		registerPage.inputToLastNameTextbox(lastName);

		registerPage.inputToEmailTextbox(validEmail);

		registerPage.inputToPasswordTextbox(correctPassword);

		registerPage.inputToConfirmPasswordTextbox(correctPassword);

		registerPage.clickToRegisterButton();

		Assert.assertEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");
	}

	@Description("User_02_Login")
	@Story("Login")
	@Severity(SeverityLevel.CRITICAL)
	@Test
	public void User_02_Login() {
		homePage.clickToLogoutLinkAtUserPage(driver);
		loginPage = homePage.clickToLoginLink();

		loginPage.inputToEmail(validEmail);

		loginPage.inputToPassword(correctPassword);

		homePage = loginPage.clickToLoginButton();

		Assert.assertFalse(homePage.isMyAccountLinkDisplayed());

		customerInfoPage = homePage.clickToMyAccountLink();

		Assert.assertFalse(customerInfoPage.isDisplayed());
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
