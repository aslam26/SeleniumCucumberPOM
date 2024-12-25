package com.saucelabs.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Login_Page extends BasePage {

    public Login_Page(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "user-name")
    WebElement usnTextBox;

    @FindBy(id = "password")
    WebElement pswTextBox;

    @FindBy(id = "login-button")
    WebElement loginBtn;

    @FindBy(xpath = "//h3[@data-test='error']")
    WebElement errorMsg;


    public void performLogin(String username, String password){
        usnTextBox.sendKeys(username);
        pswTextBox.sendKeys(password);
        loginBtn.click();
    }

    public String errorMessage(){
        String errMessage = errorMsg.getText();
        return errMessage;
    }

}
