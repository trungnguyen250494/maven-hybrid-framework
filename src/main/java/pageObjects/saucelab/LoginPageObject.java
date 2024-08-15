package pageObjects.saucelab;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.saucelab.LoginPageUI;

public class LoginPageObject extends BasePage {
    public LoginPageObject(WebDriver driver) {
        super();
        this.driver = driver;
    }

    private WebDriver driver;

    public void enterToUsername(String userName) {
        waitUntilElementVisible(driver, LoginPageUI.USERNAME_TEXTBOX);
        sendKeyToElement(driver,LoginPageUI.USERNAME_TEXTBOX,userName);
    }

    public void enterToPassword(String password) {
        waitUntilElementVisible(driver, LoginPageUI.PASSWORD_TEXTBOX);
        sendKeyToElement(driver,LoginPageUI.PASSWORD_TEXTBOX,password);
    }

    public ProductPageObject clickToLoginButton() {
        waitUntilElementVisible(driver,LoginPageUI.LOGIN_BUTTON);
        clickToElement(driver,LoginPageUI.LOGIN_BUTTON);
        return new ProductPageObject(driver);
    }
}
