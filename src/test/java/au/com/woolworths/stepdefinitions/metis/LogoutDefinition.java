package au.com.woolworths.stepdefinitions.metis;

import au.com.woolworths.helpers.metis.LogoutHelper;
import au.com.woolworths.model.metis.logout.Logout;
import au.com.woolworths.utils.Utilities;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import junit.framework.Assert;

import java.util.logging.Logger;

public class LogoutDefinition extends LogoutHelper {
  private final static Logger logger = Logger.getLogger("LogoutDefinition.class");
  private Logout LogoutResponse = new Logout();

  @When("^the user logs out from the account screen")
  public void theUserMakesARequestForTheLogout() throws Throwable {
    String deviceId = Utilities.generateRandomUUIDString();
    LogoutResponse = iRetrieveMyLogout(deviceId);
  }

  @Then("^the user should be logged out successfully$")
  public void logoutWasSuccessful() {

    Assert.assertEquals("Status code for logout NOT successful", "204", LogoutResponse.getStatusCode());
    logger.info("the user was able to logout of account successfully");
  }
}