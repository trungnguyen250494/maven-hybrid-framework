package com.nopcommerce.account;
/*
 * package com.nopcommerce.user;
 *
 * import java.util.Random; import java.util.concurrent.TimeUnit;
 *
 * import org.openqa.selenium.By; import org.openqa.selenium.WebDriver; import org.openqa.selenium.firefox.FirefoxDriver; import
 * org.openqa.selenium.support.ui.ExpectedConditions; import org.openqa.selenium.support.ui.WebDriverWait; import org.testng.Assert; import
 * org.testng.annotations.AfterClass; import org.testng.annotations.BeforeClass; import org.testng.annotations.Test;
 *
 * import commons.BasePage;
 *
 * public class Level_02_Apply_BasePage_II { WebDriver driver;
 *
 * String projectPath = System.getProperty("user.dir"); String osName = System.getProperty("os.name"); String email; BasePage basePage;
 *
 * @BeforeClass public void beforeClass() { if (osName.contains("Mac OS")) { System.setProperty("webdriver.gecko.driver", projectPath +
 * "/browserDrivers/geckodriver"); } else { System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe"); }
 *
 * driver = new FirefoxDriver(); driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS); driver.manage().window().maximize();
 *
 * basePage = BasePage.getBasePageObject();
 *
 * driver.get("https://demo.nopcommerce.com/");
 *
 * }
 *
 * @Test public void TC_01_Register_Empty_Data() { basePage.waitForElementClickable(driver, "//a[@class='ico-register']"); basePage.clickToElement(driver,
 * "//a[@class='ico-register']");
 *
 * basePage.waitForElementClickable(driver, "//button[@id='register-button']"); basePage.clickToElement(driver, "//button[@id='register-button']");
 *
 * Assert.assertEquals(basePage.getElementText(driver, "//span[@id='FirstName-error']"), "First name is required.");
 *
 * Assert.assertEquals(basePage.getElementText(driver, "//span[@id='LastName-error']"), "Last name is required.");
 *
 * Assert.assertEquals(basePage.getElementText(driver, "//span[@id='Email-error']"), "Email is required.");
 *
 * Assert.assertEquals(basePage.getElementText(driver, "//span[@id='Password-error']"), "Password is required.");
 *
 * Assert.assertEquals(basePage.getElementText(driver, "//span[@id='ConfirmPassword-error']"), "Password is required.");
 *
 * }
 *
 * @Test public void TC_02_Register_Invalid_Email() {
 *
 * basePage.waitForElementClickable(driver, "//a[@class='ico-register']"); basePage.clickToElement(driver, "//a[@class='ico-register']");
 *
 * basePage.sendKeyToElement(driver, "//input[@id='FirstName']", "Tester");
 *
 * basePage.sendKeyToElement(driver, "//input[@id='LastName']", "Tester");
 *
 * basePage.sendKeyToElement(driver, "//input[@id='Email']", "abc#xyz");
 *
 * basePage.sendKeyToElement(driver, "//input[@id='Password']", "Tester@123");
 *
 * basePage.sendKeyToElement(driver, "//input[@id='ConfirmPassword']", "Tester@123");
 *
 * basePage.clickToElement(driver, "//button[@id='register-button']");
 *
 * Assert.assertEquals(basePage.getElementText(driver, "//span[@id='Email-error']"), "Wrong email");
 *
 * }
 *
 * @Test public void TC_03_Register_Success() {
 *
 * basePage.waitForElementClickable(driver, "//a[@class='ico-register']"); basePage.clickToElement(driver, "//a[@class='ico-register']");
 *
 * basePage.sendKeyToElement(driver, "//input[@id='FirstName']", "Tester");
 *
 * basePage.sendKeyToElement(driver, "//input[@id='LastName']", "Tester");
 *
 * email = "tester" + getRandomNumber() + "@yopmail.com";
 *
 * basePage.sendKeyToElement(driver, "//input[@id='Email']", email);
 *
 * basePage.sendKeyToElement(driver, "//input[@id='Password']", "Tester@123");
 *
 * basePage.sendKeyToElement(driver, "//input[@id='ConfirmPassword']", "Tester@123");
 *
 * basePage.clickToElement(driver, "//button[@id='register-button']");
 *
 * Assert.assertEquals(basePage.getElementText(driver, "//div[@class='result']"), "Your registration completed");
 *
 * }
 *
 * @Test public void TC_04_Register_Existing_Email() {
 *
 * basePage.waitForElementClickable(driver, "//a[@class='ico-register']"); basePage.clickToElement(driver, "//a[@class='ico-register']");
 *
 * basePage.sendKeyToElement(driver, "//input[@id='FirstName']", "Tester");
 *
 * basePage.sendKeyToElement(driver, "//input[@id='LastName']", "Tester");
 *
 * basePage.sendKeyToElement(driver, "//input[@id='Email']", email);
 *
 * basePage.sendKeyToElement(driver, "//input[@id='Password']", "Tester@123");
 *
 * basePage.sendKeyToElement(driver, "//input[@id='ConfirmPassword']", "Tester@123");
 *
 * basePage.clickToElement(driver, "//button[@id='register-button']");
 *
 * basePage.waitUntilElementVisible(driver, "//div[@class='message-error validation-summary-errors']//li");
 *
 * Assert.assertEquals(basePage.getElementText(driver, "//div[@class='message-error validation-summary-errors']//li"), "The specified email already exists");
 *
 * }
 *
 * @Test public void TC_05_Register_Password_Less_Than_6_Chars() {
 *
 * basePage.waitForElementClickable(driver, "//a[@class='ico-register']"); basePage.clickToElement(driver, "//a[@class='ico-register']");
 *
 * basePage.sendKeyToElement(driver, "//input[@id='FirstName']", "Tester");
 *
 * basePage.sendKeyToElement(driver, "//input[@id='LastName']", "Tester");
 *
 * email = "tester" + getRandomNumber() + "@yopmail.com";
 *
 * basePage.sendKeyToElement(driver, "//input[@id='Email']", email);
 *
 * basePage.sendKeyToElement(driver, "//input[@id='Password']", "Test");
 *
 * basePage.sendKeyToElement(driver, "//input[@id='ConfirmPassword']", "Test");
 *
 * basePage.clickToElement(driver, "//button[@id='register-button']");
 *
 * Assert.assertEquals(basePage.getElementText(driver, "//span[@id='Password-error']"),
 * "Password must meet the following rules:\nmust have at least 6 characters");
 *
 * }
 *
 * @Test public void TC_06_Register_Invalid_Confirm_Password() {
 *
 * basePage.waitForElementClickable(driver, "//a[@class='ico-register']"); basePage.clickToElement(driver, "//a[@class='ico-register']");
 *
 * driver.findElement(By.cssSelector("#FirstName")).sendKeys("Tester");
 *
 * driver.findElement(By.cssSelector("#LastName")).sendKeys("Tester");
 *
 * basePage.sendKeyToElement(driver, "//input[@id='FirstName']", "Tester");
 *
 * basePage.sendKeyToElement(driver, "//input[@id='LastName']", "Tester");
 *
 * email = "tester" + getRandomNumber() + "@yopmail.com";
 *
 * basePage.sendKeyToElement(driver, "//input[@id='Email']", email);
 *
 * basePage.sendKeyToElement(driver, "//input[@id='Password']", "Tester@123");
 *
 * basePage.sendKeyToElement(driver, "//input[@id='ConfirmPassword']", "Tester345");
 *
 * basePage.clickToElement(driver, "//button[@id='register-button']");
 *
 * Assert.assertEquals(basePage.getElementText(driver, "//span[@id='ConfirmPassword-error']"), "The password and confirmation password do not match.");
 *
 * }
 *
 * public int getRandomNumber() { Random rd = new Random(); return rd.nextInt(1000); }
 *
 * @AfterClass public void afterClass() { driver.quit(); } }
 */