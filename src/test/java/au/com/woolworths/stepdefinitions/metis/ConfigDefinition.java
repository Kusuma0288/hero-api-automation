package au.com.woolworths.stepdefinitions.metis;

import au.com.woolworths.helpers.metis.ConfigHelper;
import au.com.woolworths.model.metis.config.ConfigResponse;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import junit.framework.Assert;

import java.util.logging.Logger;

public class ConfigDefinition extends ConfigHelper {
  private final static Logger logger = Logger.getLogger("ConfigDefinition.class");
  private ConfigResponse configResponse;

  @Given("^the user launches the App with \"([^\"]*)\" and osversion \"([^\"]*)\"$")
  public void theUserLaunchesApp(String appVersion, String osVersion) throws Throwable {
    configResponse = getconfig(appVersion, osVersion);
    logger.info("User queried with appVersion " + appVersion + " and osVersion " + osVersion);
  }

  @Then("^the user should be able to see the on-boarding screen$")
  public void theUserShouldBeAbleToSeeTheOnboardingScreen() {
    Assert.assertEquals("Incorrect os version supported", "SUPPORTED", configResponse.getData().getStatus());
    Assert.assertEquals("Status code for config NOT successful", "200", configResponse.getStatusCode());
    Assert.assertEquals("Incorrect Rewards App config returned", "RewardsAppConfig", configResponse.getType());
    logger.info("The supported message was returned successfully");
  }

  @Then("^the user should not be able to open the app$")
  public void theUserShouldNotBeAbleToOpenTheApp() {
    Assert.assertEquals("Unsupported os is not blocked by metis", "OS_NOT_SUPPORTED", configResponse.getData().getStatus());
    Assert.assertEquals("Status code for config NOT successful", "200", configResponse.getStatusCode());
    Assert.assertEquals("Incorrect Rewards App config returned", "RewardsAppConfig", configResponse.getType());
    logger.info("The blocking message was returned successfully");
  }

}


