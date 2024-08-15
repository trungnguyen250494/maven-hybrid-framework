package pageObjects.saucelab;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pageUIs.saucelab.ProductPageUI;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProductPageObject extends BasePage {
    public ProductPageObject(WebDriver driver) {
        super();
        this.driver = driver;
    }

    private WebDriver driver;

    public void selectItemInSortDropdown(String sortItem) {
        waitForElementClickable(driver, ProductPageUI.SORT_DROPDOWN);
        clickToElement(driver,ProductPageUI.SORT_DROPDOWN);
        selectItemInDefaultDropDown(driver, ProductPageUI.SORT_DROPDOWN, sortItem);
    }

    public boolean isProductNameSortedByAscending() {
        List<WebElement> allProductNameText = getListOfWebElements(driver, ProductPageUI.PRODUCT_NAME_TEXT);
        ArrayList<String> actualProductNameList = new ArrayList<String>();
        for(WebElement productName : allProductNameText){
            actualProductNameList.add(productName.getText());
        }

        ArrayList<String> expectedProductNameList = new ArrayList<>();
        expectedProductNameList = (ArrayList) actualProductNameList.clone();

        Collections.sort(expectedProductNameList);

        return actualProductNameList.equals(expectedProductNameList);
    }

    public boolean isProductNameSortedByDescending() {
        List<WebElement> allProductNameText = getListOfWebElements(driver, ProductPageUI.PRODUCT_NAME_TEXT);
        ArrayList<String> actualProductNameList = new ArrayList<String>();
        for(WebElement productName : allProductNameText){
            actualProductNameList.add(productName.getText());
        }

        ArrayList<String> expectedProductNameList = new ArrayList<>();
        expectedProductNameList = (ArrayList) actualProductNameList.clone();

        Collections.sort(expectedProductNameList);
        Collections.reverse(expectedProductNameList);

        return actualProductNameList.equals(expectedProductNameList);
    }

    public boolean isProductPriceSortedByLowToHigh() {
        List<WebElement> allProductPriceText = getListOfWebElements(driver, ProductPageUI.PRODUCT_PRICE_TEXT);
        ArrayList<Float> actualProductPriceList = new ArrayList<Float>();
        for(WebElement productPrice : allProductPriceText){
            actualProductPriceList.add(Float.parseFloat(productPrice.getText().replace("$","")));
        }

        ArrayList<String> expectedProductPriceList = new ArrayList<>();
        expectedProductPriceList = (ArrayList) actualProductPriceList.clone();

        Collections.sort(expectedProductPriceList);

        return actualProductPriceList.equals(expectedProductPriceList);
    }

    public boolean isProductPriceSortedByHighToLow() {
        List<WebElement> allProductPriceText = getListOfWebElements(driver, ProductPageUI.PRODUCT_PRICE_TEXT);
        ArrayList<Float> actualProductPriceList = new ArrayList<Float>();
        for(WebElement productPrice : allProductPriceText){
            actualProductPriceList.add(Float.parseFloat(productPrice.getText().replace("$","")));
        }

        ArrayList<String> expectedProductPriceList = new ArrayList<>();
        expectedProductPriceList = (ArrayList) actualProductPriceList.clone();

        Collections.sort(expectedProductPriceList);
        Collections.reverse(expectedProductPriceList);

        return actualProductPriceList.equals(expectedProductPriceList);
    }
}
