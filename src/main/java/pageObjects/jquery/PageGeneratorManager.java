package pageObjects.jquery;

import org.openqa.selenium.WebDriver;

public class PageGeneratorManager {
	public static HomePageObject getHomepage(WebDriver driver) {
		return new HomePageObject(driver);
	}

	public static UploadPageObject getUploadpage(WebDriver driver) {
		return new UploadPageObject(driver);
	}

}
