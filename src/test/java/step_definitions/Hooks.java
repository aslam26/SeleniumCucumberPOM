package step_definitions;

import com.saucelabs.Pages.Login_Page;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import lombok.Getter;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.File;
import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

public class Hooks {

    @Getter
    public static WebDriver driver;

    String currentWorkingDir = System.getProperty("user.dir");
    public static Properties prop;
    FileInputStream ip;
    String browserType;
    String baseUrl;
    Login_Page loginPage;

    @Before(order = 0)
    public void setUp() throws Exception {
        prop = new Properties();
        ip = new FileInputStream(currentWorkingDir + "/Config/config.properties");
        prop.load(ip);

        browserType= prop.getProperty("browserType");
        System.out.println("Global tags executed");

        if(driver==null) {
            if (browserType.equalsIgnoreCase("chrome")) {
                driver = new ChromeDriver();
            } else if (browserType.equalsIgnoreCase("firefox")) {
                driver = new FirefoxDriver();
            } else if (browserType.equalsIgnoreCase("edge")) {
                driver = new EdgeDriver();
            } else if (browserType.equalsIgnoreCase("chrome-headless")) {
                ChromeOptions options=new ChromeOptions();
                options.addArguments("---headless");
                driver=new ChromeDriver(options);
            } else {
                throw new Exception("Invalid browser type" + browserType);
            }
        }

        baseUrl=prop.getProperty("baseUrl");
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(30));
        driver.get(baseUrl);

    }


    @After(order = 1)
    public void tearDown(Scenario scenario){
        if(scenario.isFailed()){
            TakesScreenshot camera=(TakesScreenshot) driver;
            try{
                final byte[] screenshotBytes=camera.getScreenshotAs(OutputType.BYTES);
                scenario.attach(screenshotBytes,"failedImage/png",scenario.getName());

            }catch (Exception e){
                e.printStackTrace();
            }
        }
        if(driver!=null) {
            driver.quit();
            driver=null;
        }
    }

}
