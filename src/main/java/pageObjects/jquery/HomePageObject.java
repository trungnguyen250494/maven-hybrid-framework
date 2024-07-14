package pageObjects.jquery;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import commons.BasePage;
import pageUIs.jquery.HomePageUI;

public class HomePageObject extends BasePage {
	WebDriver driver;

	public HomePageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void inputColumnTextboxByName(String columnName, String valueToSend) {
		waitUntilElementVisible(driver, HomePageUI.COLUMN_TEXTBOX_BY_NAME, columnName);
		sendKeyToElement(driver, HomePageUI.COLUMN_TEXTBOX_BY_NAME, valueToSend, columnName);
	}

	public void clickToPageByNumber(String pageNumber) {
		waitUntilElementVisible(driver, HomePageUI.PAGE_LINK_BY_NUMBER, pageNumber);
		clickToElement(driver, HomePageUI.PAGE_LINK_BY_NUMBER, pageNumber);
	}

	public boolean isPageActiveByNumber(String pageNumber) {
		waitUntilElementVisible(driver, HomePageUI.PAGE_LINK_ACTIVE_BY_NUMBER, pageNumber);
		return isElementDisplayed(driver, HomePageUI.PAGE_LINK_ACTIVE_BY_NUMBER, pageNumber);
	}

	public boolean isRowDisplayedByValues(String female, String country, String male, String total) {
		waitUntilElementVisible(driver, HomePageUI.DYNAMIC_ROW_VALUES, female, country, male, total);
		return isElementDisplayed(driver, HomePageUI.DYNAMIC_ROW_VALUES, female, country, male, total);
	}

	public void clickToTheActionButtonByCountry(String countryName, String action) {
		waitUntilElementVisible(driver, HomePageUI.BUTTON_ACTION_BY_COUNTRY, countryName, action);
		clickToElement(driver, HomePageUI.BUTTON_ACTION_BY_COUNTRY, countryName, action);
	}

	public List<String> getAllPagesValueByColumnName(String columnName) {
		List<String> allValueStrings = new ArrayList<>();

		List<WebElement> allPagesLinkElements = getListOfWebElements(driver, HomePageUI.ALL_PAGE_LINKS);

		int columnIndex = getElementSize(driver, HomePageUI.COLUMN_INDEX_BY_COLUMN_NAME, columnName) + 1;

		for (WebElement pageLink : allPagesLinkElements) {
			pageLink.click();
			waitInSecond(2);

			List<WebElement> allRowValues = getListOfWebElements(driver, HomePageUI.ALL_VALUES_BY_COLUMN_INDEX, String.valueOf(columnIndex));
			for (WebElement rowValue : allRowValues) {
				allValueStrings.add(rowValue.getText());
			}
		}

		for (String value : allValueStrings) {
			System.out.println(value);
		}
		return allValueStrings;
	}

	public void enterToTextBoxByColumnNameAndRowIndex(String columnName, String rowIndex, String valueToSend) {
		int columnIndex = getElementSize(driver, HomePageUI.DYNAMIC_COLUMN_INDEX_BY_COLUMN_NAME, columnName) + 1;

		sendKeyToElement(driver, HomePageUI.DYNAMIC_TEXTBOX_BY_ROW_INDEX_COLUMN_INDEX, valueToSend, String.valueOf(columnIndex));

	}

}
