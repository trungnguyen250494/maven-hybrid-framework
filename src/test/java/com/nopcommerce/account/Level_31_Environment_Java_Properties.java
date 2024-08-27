package com.nopcommerce.account;

import commons.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import utilities.PropertiesConfig;

import java.lang.reflect.Method;

public class Level_31_Environment_Java_Properties extends BaseTest {
	private WebDriver driver;
	private PropertiesConfig propertiesConfig;

	@Parameters({ "browser", "environment" })
	@BeforeMethod
	public void beforeMethod(String browserName, String environmentName) {
		propertiesConfig = PropertiesConfig.getProperties(environmentName);
		System.out.println("Url is " + propertiesConfig.getApplicationUrl());
		System.out.println("Username is " + propertiesConfig.getApplicationUserName());
		System.out.println("Password is " + propertiesConfig.getApplicationPassword());
		driver = getBrowserDriverWithUrl(browserName, propertiesConfig.getApplicationUrl());
	}

	@Test
	public void Search_01_shouldBeSearchByEmptyData(Method method) {

	}

	@AfterMethod
	public void afterMethod() {
		driver.quit();
	}
}
