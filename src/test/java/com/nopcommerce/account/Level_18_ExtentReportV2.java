//package com.nopcommerce.account;
//
//import java.lang.reflect.Method;
//import java.util.Random;
//
//import org.openqa.selenium.WebDriver;
//import org.testng.Assert;
//import org.testng.annotations.AfterClass;
//import org.testng.annotations.BeforeClass;
//import org.testng.annotations.Parameters;
//import org.testng.annotations.Test;
//
//import com.relevantcodes.extentreports.LogStatus;
//
//import commons.BaseTest;
//import commons.PageGeneratorManager;
//import pageObjects.portal.UserAddressPageObject;
//import pageObjects.portal.UserCustomerPageObject;
//import pageObjects.portal.UserHomePageObject;
//import pageObjects.portal.UserLoginPageObject;
//import pageObjects.portal.UserProductReviewPageObject;
//import pageObjects.portal.UserRegisterPageObject;
//import pageObjects.portal.UserRewardPointPageObject;
//import reportConfig.ExtentManager;
//
//public class Level_18_ExtentReportV2 extends BaseTest{
//	private WebDriver driver;
//	private String validEmail, firstName, lastName, correctPassword, confirmPassword, invalidEmail, notFoundEmail, incorrectPassword;
//	private UserHomePageObject homePage;
//	private UserRegisterPageObject registerPage;
//
//
//	@Parameters({"browser","environment"})
//	@BeforeClass
//	public void beforeClass(String browserName, String environmentName) {
//		driver = getBrowserDriver(browserName,environmentName);
//		homePage = PageGeneratorManager.getUserHomePage(driver);
//
//		firstName = "Tester";
//		lastName = "Tester";
//		correctPassword = "Tester@123";
//		incorrectPassword = "123";
//		confirmPassword = "Tester@123";
//		validEmail = "tester" + getRandomNumber() + "@yopmail.com";
//		invalidEmail = "tester@yopmail.com@123";
//		notFoundEmail = "testerHello@yopmail.com";
//
//	}
//
//	@Test
//	public void User_01_Register_Validate(Method method) {
//		ExtentManager.startTest(method.getName(), "User_01_Register_Validate");
//
//		ExtentManager.getTest().log(LogStatus.INFO,"User 01 - Step 01: Verify Register link is displayed.");
//		Assert.assertTrue(homePage.isRegisterLinkDisplayed());
//
//		ExtentManager.getTest().log(LogStatus.INFO,"User 01 - Step 02: Click to Register link.");
//		registerPage = homePage.clickToRegisterLink();
//
//		ExtentManager.getTest().log(LogStatus.INFO,"User 01 - Step 03: Click to Register button.");
//		registerPage.clickToRegisterButton();
//		registerPage.waitInSecond(5);
//
//		ExtentManager.getTest().log(LogStatus.INFO,"User 01 - Step 04: Verify error message - First Name is displayed.");
//		Assert.assertEquals(registerPage.getErrorMessageAtFirstNameTextbox(), "First name is required.");
//
//		ExtentManager.getTest().log(LogStatus.INFO,"User 01 - Step 05: Verify error message - Last Name is displayed.");
//		Assert.assertEquals(registerPage.getErrorMessageAtLastNameTextbox(), "Last name is required");
//	}
//
//	@Test
//	public void User_02_Register_Success(Method method) {
//		ExtentManager.startTest(method.getName(), "User_02_Register_Success");
//
//		ExtentManager.getTest().log(LogStatus.INFO,"User 01 - Step 01: Enter to First Name textbox with value is " + firstName);
//		registerPage = homePage.clickToRegisterLink();
//		registerPage.inputToFirstNameTextbox(firstName);
//
//		ExtentManager.getTest().log(LogStatus.INFO,"User 01 - Step 02: Enter to Last Name textbox with value is " + lastName);
//		registerPage.inputToLasttNameTextbox(lastName);
//
//		ExtentManager.getTest().log(LogStatus.INFO,"User 01 - Step 03: Enter to Email textbox with value is " + validEmail);
//		registerPage.inputToEmailTextbox(validEmail);
//
//		ExtentManager.getTest().log(LogStatus.INFO,"User 01 - Step 04: Enter to Password textbox with value is " + correctPassword);
//		registerPage.inputToPasswordTextbox(correctPassword);
//
//		ExtentManager.getTest().log(LogStatus.INFO,"User 01 - Step 05: Enter to Confirm Password textbox with value is " + confirmPassword);
//		registerPage.inputToConfirmPasswordTextbox(confirmPassword);
//
//		ExtentManager.getTest().log(LogStatus.INFO,"User 01 - Step 06: Click to Register button.");
//		registerPage.clickToRegisterButton();
//
//		ExtentManager.getTest().log(LogStatus.INFO,"User 01 - Step 07: Verify Register completed.");
//		Assert.assertEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");
//	}
//
//	public int getRandomNumber() {
//		Random rd = new Random();
//		return rd.nextInt(1000);
//	}
//
//	@AfterClass
//	public void afterClass() {
//		driver.quit();
//	}
//}
