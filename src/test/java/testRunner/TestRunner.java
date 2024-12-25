package testRunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.Test;

@CucumberOptions(
        features ={"features"},
        glue ={"step_definitions"},
        plugin = {"pretty","html:Reports/test_report.html",
                "json:target/cucumber.json",
        "html:target/cucumber-html-report"},

        dryRun = false,
        monochrome = true,
        tags ="@Valid or @Invalid or @EmptyCredentials"
)
public class TestRunner extends AbstractTestNGCucumberTests {

    @Test
    public void runCucumber(){

    }
}
