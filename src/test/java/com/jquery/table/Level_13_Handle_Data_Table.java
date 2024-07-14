package com.jquery.table;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import pageObjects.jquery.HomePageObject;
import pageObjects.jquery.PageGeneratorManager;

public class Level_13_Handle_Data_Table extends BaseTest {
	WebDriver driver;
	HomePageObject homePageObject;

	@Parameters({ "browser", "url" })
	@BeforeClass
	public void beforeClass(String browserName, String url) {
		driver = getBrowserDriverWithUrl(browserName, url);
		homePageObject = PageGeneratorManager.getHomepage(driver);

	}

	@Test
	public void TC_01_Search_By_Header_Name() {

		// Search dữ liệu trên 1 Table (Header)
		homePageObject.inputColumnTextboxByName("Females", "283821");
		homePageObject.waitInSecond(2);

		homePageObject.inputColumnTextboxByName("Country", "Algeria");
		homePageObject.waitInSecond(2);
	}

	@Test
	public void TC_02_Paging() {

		// Click Page number và kiểm tra page hiển thị
		homePageObject.clickToPageByNumber("2");
		homePageObject.waitInSecond(2);

		Assert.assertTrue(homePageObject.isPageActiveByNumber("2"));
	}

	@Test
	public void TC_03_Search_By_Row_Values() {

		// Verify row hiển thị với những values
		homePageObject.clickToPageByNumber("1");
		homePageObject.waitInSecond(2);

		Assert.assertTrue(homePageObject.isRowDisplayedByValues("384187", "Afghanistan", "407124", "791312"));
	}

	@Test
	public void TC_04_Click_Action_Buttons() {

		homePageObject.clickToTheActionButtonByCountry("Afghanistan", "remove");
		homePageObject.waitInSecond(2);
		homePageObject.clickToTheActionButtonByCountry("AFRICA", "remove");
		homePageObject.waitInSecond(2);
		homePageObject.refreshCurrentPage(driver);

		homePageObject.clickToTheActionButtonByCountry("Afghanistan", "edit");
		homePageObject.waitInSecond(2);
	}

	@Test
	public void TC_05_Get_All_Column_Values() {
		homePageObject.refreshCurrentPage(driver);
		homePageObject.getAllPagesValueByColumnName("Country");
		homePageObject.getAllPagesValueByColumnName("Total");
	}

	@Test
	public void TC_06_Action_By_Index() {
		homePageObject.openPageURL(driver, "https://www.jqueryscript.net/demo/jQuery-Dynamic-Data-Grid-Plugin-appendGrid/");
		homePageObject.enterToTextBoxByColumnNameAndRowIndex("Contact Person", "2", "Nguyen Van A");
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
