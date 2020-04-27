import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;
import org.testng.*;
import org.testng.annotations.*;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Logger;

@CucumberOptions(
        strict = false,
        features = {"/Users/xkgu1/Documents/AutoFix/shop-app-apigee-iris-api-automation/src/test/resources/features/Specials.feature"},
        plugin = {"json:/Users/xkgu1/Documents/AutoFix/shop-app-apigee-iris-api-automation/target/cucumber-reports/advanced-reports/1.json"},
        monochrome = false,
        tags = {"@IN_PROGRESS"},
        glue = {"au.com.woolworths.apigee.stepdefinitions"},
        format = {
                        "pretty",
                        "rerun:target/cucumber-reports/rerun.txt"
                })

public class Parallel02IT extends AbstractTestNGCucumberTests {

    private static Logger logger = Logger.getLogger("Parallel02IT"+".class");

}


