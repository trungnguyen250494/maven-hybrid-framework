package com.nopcommerce.account;

import commons.BaseTest;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import utilities.EnvironmentConfig;

import java.lang.reflect.Method;

public class Level_31_Environment_Owner extends BaseTest {
	private WebDriver driver;
	private EnvironmentConfig environmentConfig;

	@Parameters({ "browser", "environment" })
	@BeforeMethod
	public void beforeMethod(String browserName, String environmentName) {
		ConfigFactory.setProperty("envName", environmentName);
		environmentConfig = ConfigFactory.create(EnvironmentConfig.class);
		System.out.println("Url is " + environmentConfig.appUrl());
		System.out.println("Username is " + environmentConfig.appUsername());
		System.out.println("Password is " + environmentConfig.appPassword());
		driver = getBrowserDriverWithUrl(browserName, environmentConfig.appUrl());
	}

	@Test
	public void Search_01_shouldBeSearchByEmptyData(Method method) {

	}

	@AfterMethod
	public void afterMethod() {
		driver.quit();
	}
}
