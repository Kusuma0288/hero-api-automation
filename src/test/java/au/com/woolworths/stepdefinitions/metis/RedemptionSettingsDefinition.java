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

  @And("^item \"([^\"]*)\" returns the title \"([^\"]*)\"$")
  public void itemShouldReturnTheTitle(int itemNumber, String title) {
    int itemIndex = itemNumber - 1;
    Assert.assertEquals("Incorrect title for item " + itemNumber, title, redemptionSettingsResponse.getRewardsRedemptionSettings().getItems()[itemIndex].getTitle());
  }

  @And("^item \"([^\"]*)\" should return the body \"([^\"]*)\"$")
  public void itemShouldReturnTheBody(int itemNumber, String bodyText) {
    int itemIndex = itemNumber - 1;
    Assert.assertEquals("Incorrect body for item " + itemNumber, bodyText, redemptionSettingsResponse.getRewardsRedemptionSettings().getItems()[itemIndex].getBody());
  }

  @And("^item \"([^\"]*)\" should return the icon \"([^\"]*)\"$")
  public void itemShouldReturnTheIcon(int itemNumber, String icon) {
    int itemIndex = itemNumber - 1;
    Assert.assertEquals("Incorrect icon for item " + itemNumber, icon, redemptionSettingsResponse.getRewardsRedemptionSettings().getItems()[itemIndex].getIcon());
  }

  @And("^item \"([^\"]*)\" should return title \"([^\"]*)\" for confirmation message \"([^\"]*)\"$")
  public void itemShouldReturnTitleForConfirmationMessageTitle(int itemNumber, String confirmationMessage, int confirmationMessageNumber) {
    int itemIndex = itemNumber - 1;
    int confirmationMessageIndex = confirmationMessageNumber - 1;
    Assert.assertEquals("Incorrect confirmation message title for item " + itemNumber, confirmationMessage, redemptionSettingsResponse.getRewardsRedemptionSettings().getItems()[itemIndex].getConfirmationMessages()[confirmationMessageIndex].getTitle());
  }

  @And("^item \"([^\"]*)\" should return button label \"([^\"]*)\" for confirmation message \"([^\"]*)\"$")
  public void itemShouldReturnButtonLabelForConfirmationMessageTitle(int itemNumber, String confirmationMessage, int confirmationMessageNumber) {
    int itemIndex = itemNumber - 1;
    int confirmationMessageIndex = confirmationMessageNumber - 1;
    Assert.assertEquals("Incorrect confirmation button label for item " + itemNumber, confirmationMessage, redemptionSettingsResponse.getRewardsRedemptionSettings().getItems()[itemIndex].getConfirmationMessages()[confirmationMessageIndex].getButtonLabel());
  }

  @And("^item \"([^\"]*)\" should not be destructive for confirmation message \"([^\"]*)\" title$")
  public void itemShouldNotBeDestructiveForConfirmationMessageTitle(int itemNumber, int confirmationMessageNumber) {
    int itemIndex = itemNumber - 1;
    int confirmationMessageIndex = confirmationMessageNumber - 1;
    Assert.assertFalse("Switching to item " + itemNumber + " should not be destructive", redemptionSettingsResponse.getRewardsRedemptionSettings().getItems()[itemIndex].getConfirmationMessages()[confirmationMessageIndex].getIsDestructive());

  }

  @And("^item \"([^\"]*)\" should be destructive for confirmation message \"([^\"]*)\" title$")
  public void itemShouldBeDestructiveForConfirmationMessageTitle(int itemNumber, int confirmationMessageNumber) {
    int itemIndex = itemNumber - 1;
    int confirmationMessageIndex = confirmationMessageNumber - 1;
    Assert.assertTrue("Switching to item " + itemNumber + " should be destructive", redemptionSettingsResponse.getRewardsRedemptionSettings().getItems()[itemIndex].getConfirmationMessages()[confirmationMessageIndex].getIsDestructive());

  }
}