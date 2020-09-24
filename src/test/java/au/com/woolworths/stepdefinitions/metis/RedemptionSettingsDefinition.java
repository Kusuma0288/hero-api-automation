package au.com.woolworths.stepdefinitions.metis;

import au.com.woolworths.graphql.parser.GraphqlParser;
import au.com.woolworths.helpers.metis.RedemptionSettingsHelper;
import au.com.woolworths.model.metis.redemptionsettings.RedemptionSettingsResponse;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import junit.framework.Assert;

import java.io.InputStream;

public class RedemptionSettingsDefinition extends RedemptionSettingsHelper {

  private RedemptionSettingsResponse redemptionSettingsResponse;

  @When("^the user makes a request for the redemption settings$")
  public void theUserMakesARequestForTheRedemptionSettings() throws Throwable {
    InputStream iStream = RedemptionSettingsDefinition.class.getResourceAsStream("/gqlQueries/metis/redemptionSettings.graphql");
    String graphqlQuery = GraphqlParser.parseGraphql(iStream, null);
    redemptionSettingsResponse = iRetrieveMyRedemptionSettings(graphqlQuery);
  }


  @Then("^the redemption setting should return \"([^\"]*)\" reward options$")
  public void theRedemptionSettingShouldReturnRewardOptions(int rewardOptionsNumber) {
    Assert.assertEquals("RedemptionSettings does not return the expected number of items ", rewardOptionsNumber, redemptionSettingsResponse.getRewardsRedemptionSettings().getItems().length);
  }

  @And("^the step up url should be returned$")
  public void theStepUpUrlShouldBeReturned() {
    Assert.assertTrue("RedemptionSettings doesn't include a step up url", redemptionSettingsResponse.getRewardsRedemptionSettings().getTwoFactorAuth().getStepUpUrl().contains("step-up"));
  }

  @And("^the user should be able to see his redemption options$")
  public void theUserShouldBeAbleToSeeHisRedemptionOptionsForItem1() {
    Assert.assertEquals("Incorrect title for item 1", "Automatic savings", redemptionSettingsResponse.getRewardsRedemptionSettings().getItems()[0].getTitle());
    Assert.assertEquals("Incorrect icon for item 1", "dollars_off", redemptionSettingsResponse.getRewardsRedemptionSettings().getItems()[0].getIcon());
    Assert.assertEquals("Incorrect confirmation message title for item 1", "Switch to Woolworths Dollars?", redemptionSettingsResponse.getRewardsRedemptionSettings().getItems()[0].getConfirmationMessages()[0].getTitle());
    Assert.assertEquals("Incorrect confirmation button label for item 1", "Switch", redemptionSettingsResponse.getRewardsRedemptionSettings().getItems()[0].getConfirmationMessages()[0].getButtonLabel());
    Assert.assertFalse("Switching to item 1 should not be destructive", redemptionSettingsResponse.getRewardsRedemptionSettings().getItems()[0].getConfirmationMessages()[0].getIsDestructive());

    Assert.assertEquals("Incorrect title for item 2", "Bank for Christmas", redemptionSettingsResponse.getRewardsRedemptionSettings().getItems()[1].getTitle());
    Assert.assertEquals("Incorrect icon for item 2", "xmas_stocking", redemptionSettingsResponse.getRewardsRedemptionSettings().getItems()[1].getIcon());
    Assert.assertEquals("Incorrect confirmation message title for item 2", "Switch to Bank for Christmas?", redemptionSettingsResponse.getRewardsRedemptionSettings().getItems()[1].getConfirmationMessages()[0].getTitle());
    Assert.assertEquals("Incorrect confirmation button label for item 2", "Switch", redemptionSettingsResponse.getRewardsRedemptionSettings().getItems()[1].getConfirmationMessages()[0].getButtonLabel());
    Assert.assertFalse("Switching to item  should be destructive for item 2", redemptionSettingsResponse.getRewardsRedemptionSettings().getItems()[1].getConfirmationMessages()[0].getIsDestructive());

    Assert.assertEquals("Incorrect title for item 3", "Qantas Points", redemptionSettingsResponse.getRewardsRedemptionSettings().getItems()[2].getTitle());
    Assert.assertEquals("Incorrect icon for item 3", "airplane", redemptionSettingsResponse.getRewardsRedemptionSettings().getItems()[2].getIcon());
    Assert.assertEquals("Incorrect confirmation message title for item 3", "Switch to Qantas Points", redemptionSettingsResponse.getRewardsRedemptionSettings().getItems()[2].getConfirmationMessages()[0].getTitle());
    Assert.assertEquals("Incorrect confirmation button label for item 3", "Switch", redemptionSettingsResponse.getRewardsRedemptionSettings().getItems()[2].getConfirmationMessages()[0].getButtonLabel());
    Assert.assertTrue("Switching to item 3 be destructive", redemptionSettingsResponse.getRewardsRedemptionSettings().getItems()[2].getConfirmationMessages()[0].getIsDestructive());

  }


}