package pageObjects.jquery;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import commons.BasePage;
import pageUIs.jquery.UploadPageUI;

public class UploadPageObject extends BasePage {
	WebDriver driver;

	public UploadPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public boolean isFileLoadedSuccess(String fileName) {
		waitUntilElementVisible(driver, UploadPageUI.FILE_LOADED_BY_NAME, fileName);
		return isElementDisplayed(driver, UploadPageUI.FILE_LOADED_BY_NAME, fileName);
	}

	public void clickStartButtonOnEachFile() {
		List<WebElement> startButtonsElements = getListOfWebElements(driver, UploadPageUI.MULTIPLE_START_BUTTON);

		for (WebElement button : startButtonsElements) {
			waitForElementClickable(driver, button);
			clickToElement(driver, button);
		}
	}

	public boolean isPageUploadedSuccess(String fileName) {
		waitUntilElementVisible(driver, UploadPageUI.FILE_UPLOADED_BY_NAME, fileName);
		return isElementDisplayed(driver, UploadPageUI.FILE_UPLOADED_BY_NAME, fileName);
	}

}
