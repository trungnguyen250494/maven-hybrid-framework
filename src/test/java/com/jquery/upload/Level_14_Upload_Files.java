package com.jquery.upload;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import pageObjects.jquery.PageGeneratorManager;
import pageObjects.jquery.UploadPageObject;

public class Level_14_Upload_Files extends BaseTest {
	WebDriver driver;
	UploadPageObject uploadPage;
	String dalatImageFileName = "DaLat.jpg";
	String hanoiImageFileName = "HaNoi.jpg";
	String saigonImageFileName = "SaiGon.jpg";

	String[] fileNames = { dalatImageFileName, hanoiImageFileName, saigonImageFileName };

	@Parameters({ "browser", "url" })
	@BeforeClass
	public void beforeClass(String browserName, String url) {
		driver = getBrowserDriverWithUrl(browserName, url);
		uploadPage = PageGeneratorManager.getUploadpage(driver);

	}

	@Test
	public void TC_01_Upload_Single_File() {
		uploadPage.uploadMultipleFiles(driver, dalatImageFileName);
		uploadPage.waitInSecond(2);
		uploadPage.uploadMultipleFiles(driver, hanoiImageFileName);
		uploadPage.waitInSecond(2);
		uploadPage.uploadMultipleFiles(driver, saigonImageFileName);
		uploadPage.waitInSecond(2);

		Assert.assertTrue(uploadPage.isFileLoadedSuccess(dalatImageFileName));
		Assert.assertTrue(uploadPage.isFileLoadedSuccess(hanoiImageFileName));
		Assert.assertTrue(uploadPage.isFileLoadedSuccess(saigonImageFileName));

		uploadPage.clickStartButtonOnEachFile();

		Assert.assertTrue(uploadPage.isPageUploadedSuccess(dalatImageFileName));
		Assert.assertTrue(uploadPage.isPageUploadedSuccess(hanoiImageFileName));
		Assert.assertTrue(uploadPage.isPageUploadedSuccess(saigonImageFileName));
	}

	@Test
	public void TC_02_Upload_Multiple_Files() {
		uploadPage.refreshCurrentPage(driver);
		uploadPage.uploadMultipleFiles(driver, fileNames);
		uploadPage.waitInSecond(2);

		Assert.assertTrue(uploadPage.isFileLoadedSuccess(dalatImageFileName));
		Assert.assertTrue(uploadPage.isFileLoadedSuccess(hanoiImageFileName));
		Assert.assertTrue(uploadPage.isFileLoadedSuccess(saigonImageFileName));

		uploadPage.clickStartButtonOnEachFile();

		Assert.assertTrue(uploadPage.isPageUploadedSuccess(dalatImageFileName));
		Assert.assertTrue(uploadPage.isPageUploadedSuccess(hanoiImageFileName));
		Assert.assertTrue(uploadPage.isPageUploadedSuccess(saigonImageFileName));
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
