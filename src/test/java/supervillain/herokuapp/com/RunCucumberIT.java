package supervillain.herokuapp.com;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = {"src/test/resources/features/"},
    glue = "supervillain.herokuapp.com.stepdefinitions",
    plugin = {"json:target/cucumber-reports/cucumber.json", "pretty"}
)
public class RunCucumberIT {
  // Note: class named as such due to:
  // https://maven.apache.org/surefire/maven-failsafe-plugin/examples/inclusion-exclusion.html
}