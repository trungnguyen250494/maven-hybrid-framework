package com.facebook.home;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import pageObjects.facebook.HomePageObject;
import pageObjects.facebook.PageGeneratorManager;

public class Level_20_Element_Undisplayed extends BaseTest {
	private WebDriver driver;
	HomePageObject homePage;

	@Parameters({ "browser", "url" })
	@BeforeClass
	public void beforeClass(String browserName, String url) {
		driver = getBrowserDriverWithUrl(browserName, url);
		homePage = PageGeneratorManager.getHomepage(driver);
	}

	@Test
	public void Home_01_Element_Displayed() {
		homePage.clickToCreateNewAccountButton();

		log.info("Verify First name textbox is displayed");
		verifyTrue(homePage.isFirstNameTextboxDisplayed());

		log.info("Verify Surname textbox is displayed");
		verifyTrue(homePage.isSurNameTextboxDisplayed());

		log.info("Verify Email textbox is displayed");
		verifyTrue(homePage.isEmailTextboxDisplayed());

		log.info("Verify Password textbox is displayed");
		verifyTrue(homePage.isPasswordTextboxDisplayed());

		homePage.inputToFirstNameTextbox("");
		homePage.inputToSurNameTextbox("");
		log.info("Verify First name empty text is displayed");
		verifyTrue(homePage.isFirstNameEmptyIconDisplayed());
	}

	@Test
	public void Home_02_Element_Undisplayed_In_HTML() {
		homePage.inputToFirstNameTextbox("Trung");
		log.info("Verify First name empty text is not displayed");
		verifyFalse(homePage.isFirstNameEmptyIconDisplayed());
	}

	@Test
	public void Home_03_Element_Undisplayed_Not_In_HTML() {
		homePage.clickToCloseCreateNewAccountPopup();
		homePage.waitInSecond(2);

		log.info("Verify First name textbox is not displayed");
		verifyTrue(homePage.isFirstNameTextboxNotDisplayed());

		log.info("Verify Surname textbox is not displayed");
		verifyTrue(homePage.isSurNameTextboxNotDisplayed());

		log.info("Verify Email textbox is not displayed");
		verifyTrue(homePage.isEmailTextboxNotDisplayed());

		log.info("Verify Password textbox is not displayed");
		verifyTrue(homePage.isPasswordTextboxNotDisplayed());
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
