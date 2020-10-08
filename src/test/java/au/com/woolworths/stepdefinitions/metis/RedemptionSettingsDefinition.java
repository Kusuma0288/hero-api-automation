package au.com.woolworths.stepdefinitions.metis;

import au.com.woolworths.graphql.parser.GraphqlParser;
import au.com.woolworths.helpers.metis.RedemptionSettingsHelper;
import au.com.woolworths.model.metis.redemptionsettings.*;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import junit.framework.Assert;

import java.io.InputStream;

public class RedemptionSettingsDefinition extends RedemptionSettingsHelper {

  private RewardsRedemptionResponse rewardsRedemptionResponse;

  @When("^the user makes a request for the redemption settings$")
  public void theUserMakesARequestForTheRedemptionSettings() throws Throwable {
    InputStream iStream = RedemptionSettingsDefinition.class.getResourceAsStream("/gqlQueries/metis/redemptionSettings.graphql");
    String graphqlQuery = GraphqlParser.parseGraphql(iStream, null);
    rewardsRedemptionResponse = iRetrieveMyRedemptionSettings(graphqlQuery);
  }


  @Then("^the redemption setting should return \"([^\"]*)\" reward options$")
  public void theRedemptionSettingShouldReturnRewardOptions(int rewardOptionsNumber) {
    Assert.assertEquals("RedemptionSettings does not return the expected number of items ", rewardOptionsNumber, rewardsRedemptionResponse.getData().getRewardsRedemptionSettings().getItems().size());
  }

  @And("^the step up url should be returned$")
  public void theStepUpUrlShouldBeReturned() {
    Assert.assertTrue("RedemptionSettings doesn't include a step up url", rewardsRedemptionResponse.getData().getRewardsRedemptionSettings().getTwoFactorAuth().getStepUpUrl().contains("step-up"));
  }

  @And("^the user should be able to see his redemption options$")
  public void theUserShouldBeAbleToSeeHisRedemptionOptionsForItem1() {
    Item offNextShop = rewardsRedemptionResponse.getData().getRewardsRedemptionSettings().getItems().get(0);
    Item bankForXmasItem = rewardsRedemptionResponse.getData().getRewardsRedemptionSettings().getItems().get(1);
    Item qantasItem = rewardsRedemptionResponse.getData().getRewardsRedemptionSettings().getItems().get(2);

    Assert.assertEquals("Incorrect title for item 1", "Automatic savings", offNextShop.getTitle());
    Assert.assertEquals("Incorrect icon for item 1", "dollars_off", offNextShop.getIcon());
    Assert.assertEquals("Incorrect confirmation message title for item 1", "Switch to Woolworths Dollars?", offNextShop.getConfirmationMessages().get(0).getTitle());
    Assert.assertEquals("Incorrect confirmation button label for item 1", "Switch", offNextShop.getConfirmationMessages().get(0).getButtonLabel());
    Assert.assertFalse("Switching to item 1 should not be destructive", offNextShop.getConfirmationMessages().get(0).getIsDestructive());
    Assert.assertTrue("Native switch for item 1 should be enabled", offNextShop.getIsChangeEnabled());

    Assert.assertEquals("Incorrect title for item 2", "Bank for Christmas", bankForXmasItem.getTitle());
    Assert.assertEquals("Incorrect icon for item 2", "xmas_stocking", bankForXmasItem.getIcon());
    Assert.assertEquals("Incorrect confirmation message title for item 2", "Switch to Bank for Christmas?", bankForXmasItem.getConfirmationMessages().get(0).getTitle());
    Assert.assertEquals("Incorrect confirmation button label for item 2", "Switch", bankForXmasItem.getConfirmationMessages().get(0).getButtonLabel());
    Assert.assertFalse("Switching to item  should be destructive for item 2", bankForXmasItem.getConfirmationMessages().get(0).getIsDestructive());
    Assert.assertTrue("Native switch for item 2 should be enabled", bankForXmasItem.getIsChangeEnabled());


    Assert.assertEquals("Incorrect title for item 3", "Qantas Points", qantasItem.getTitle());
    Assert.assertEquals("Incorrect icon for item 3", "airplane", qantasItem.getIcon());
    Assert.assertEquals("Incorrect confirmation message title for item 3", "Switch to Qantas Points", qantasItem.getConfirmationMessages().get(0).getTitle());
    Assert.assertEquals("Incorrect confirmation button label for item 3", "Switch", qantasItem.getConfirmationMessages().get(0).getButtonLabel());
    Assert.assertTrue("Switching to item 3 be destructive", qantasItem.getConfirmationMessages().get(0).getIsDestructive());
    Assert.assertFalse("Native switch for item 2 should be enabled", qantasItem.getIsChangeEnabled());


  }


}