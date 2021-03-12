package au.com.woolworths.stepdefinitions.scango;


import au.com.woolworths.helpers.scango.UserProfileHelper;
import au.com.woolworths.model.scango.startshop.UserProfileResponse;
import cucumber.api.java.en.Then;
import org.testng.Assert;

public class UserProfileDefinition extends UserProfileHelper {


  @Then("^I verify \"([^\"]*)\" status in UserProfile API$")
  public void verifyStatusInUserProfileApi(String status) throws Throwable {
    UserProfileResponse userProfileResponse = iCallUserProfileAPI();

    String cartStatus = userProfileResponse.getStatus().getStatus();
    Assert.assertTrue(cartStatus.equals("CANCELLED"), "Invalid Status");

    Assert.assertTrue(sharedData.responseStatusCode.contains("200"), "Connection not successful::" + sharedData.responseStatusCode);

    System.out.println("UserProfileResponse  file " + userProfileResponse.toString());
  }
}
