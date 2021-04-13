package au.com.woolworths;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = {"src/test/resources/features/"}, glue = "au.com.woolworths.stepdefinitions", plugin = {"json:target/cucumber-reports/cucumber.json", "pretty"})
public class RunCucumberIT {
}