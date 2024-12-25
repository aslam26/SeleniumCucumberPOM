package step_definitions;

import com.saucelabs.Pages.Login_Page;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;


public class Login_Steps  {

    WebDriver driver= Hooks.driver;
    Login_Page loginPage=new Login_Page(driver);


    @Given("I am at login page")
    public void i_am_at_login_page() throws InterruptedException {
        String actual=driver.getTitle();
        Assert.assertEquals(actual,"Swag Labs","Title not matching");


    }

    @When("I enter {string} and {string} and click login button")
    public void i_enter_and_and_click_login_button(String username, String password) throws InterruptedException {
        loginPage.performLogin(username,password);
        Thread.sleep(1000);
    }

    @Then("home page url {string} should be displayed")
    public void home_page_url_should_be_displayed(String url) {
        String actualUrl=driver.getCurrentUrl();
        Assert.assertEquals(actualUrl,url,"Urls not matching");
    }

    @Then("an error message {string} should be displayed")
    public void an_error_message_should_be_displayed(String errorMessage) {
        Assert.assertEquals(loginPage.errorMessage(),errorMessage,"Error message not matching");

    }

}
