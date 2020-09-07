package au.com.woolworths.stepdefinitions.metis;

import au.com.woolworths.graphql.parser.GraphqlParser;
import au.com.woolworths.helpers.metis.RedemptionSettingsHelper;
import au.com.woolworths.model.metis.redemptionSettings.RedemptionSettingsResponse;
import cucumber.api.PendingException;
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


// todo: should be placed in different steps
//    @Then("^user should be able to choose how is rewarded$")
//    public void userShouldBeAbleToChooseHowIsRewarded() {
//
//
//        Assert.assertEquals("Incorrect title for item 2", "Save for Christmas", redemptionSettingsResponse.getRewardsRedemptionSettings().getItems()[1].getTitle());
//        Assert.assertEquals("Incorrect body for item 2", "Get all your money off at Christmas, available from 01/12/20", redemptionSettingsResponse.getRewardsRedemptionSettings().getItems()[1].getBody());
//        Assert.assertEquals("Incorrect icon for item 2", "xmas_stocking", redemptionSettingsResponse.getRewardsRedemptionSettings().getItems()[1].getIcon());
//        Assert.assertEquals("Incorrect confirmation message title for item 2", "Switch to Bank for Christmas?", redemptionSettingsResponse.getRewardsRedemptionSettings().getItems()[1].getConfirmationMessages()[0].getTitle());
//        Assert.assertEquals("Incorrect confirmation message for switching to item 2", "Save $10 towards Christmas, whenever you reach 2000 points.\nSavings will be available 10am AEST 01/12/20 and will be automatically applied to your next shop.", redemptionSettingsResponse.getRewardsRedemptionSettings().getItems()[1].getConfirmationMessages()[0].getMessage());
//        Assert.assertEquals("Incorrect confirmation button label for item 2", "Switch", redemptionSettingsResponse.getRewardsRedemptionSettings().getItems()[1].getConfirmationMessages()[0].getButtonLabel());
//        Assert.assertFalse("Switching to item 2 should not be destructive", redemptionSettingsResponse.getRewardsRedemptionSettings().getItems()[1].getConfirmationMessages()[0].getIsDestructive());
//
//        Assert.assertEquals("Incorrect title for item 3", "Qantas Points", redemptionSettingsResponse.getRewardsRedemptionSettings().getItems()[2].getTitle());
//        Assert.assertEquals("Incorrect body for item 3", "Converting to Qantas Points. 2000 Woolworths Points = 1000 Qantas Points", redemptionSettingsResponse.getRewardsRedemptionSettings().getItems()[2].getBody());
//        Assert.assertEquals("Incorrect icon for item 3", "airplane", redemptionSettingsResponse.getRewardsRedemptionSettings().getItems()[2].getIcon());
//        Assert.assertEquals("Incorrect confirmation message title for item 3", "Switch to Qantas Points", redemptionSettingsResponse.getRewardsRedemptionSettings().getItems()[2].getConfirmationMessages()[0].getTitle());
//        Assert.assertEquals("Incorrect confirmation message for switching to item 3", "Convert to 2000 Qantas Points automatically when you reach 2000 Woolworths Points.", redemptionSettingsResponse.getRewardsRedemptionSettings().getItems()[2].getConfirmationMessages()[0].getMessage());
//        Assert.assertEquals("Incorrect confirmation button label for item 3", "Switch", redemptionSettingsResponse.getRewardsRedemptionSettings().getItems()[2].getConfirmationMessages()[0].getButtonLabel());
//        Assert.assertTrue("Switching to item 3 should be destructive", redemptionSettingsResponse.getRewardsRedemptionSettings().getItems()[2].getConfirmationMessages()[0].getIsDestructive());
//
//    }

    @Then("^the redemption setting should return \"([^\"]*)\" reward options$")
    public void theRedemptionSettingShouldReturnRewardOptions(int rewardOptionsNumber) throws Throwable {
        Assert.assertEquals("RedemptionSettings does not return the expected number of items ", rewardOptionsNumber, redemptionSettingsResponse.getRewardsRedemptionSettings().getItems().length);
    }

    @And("^the step up url should be returned$")
    public void theStepUpUrlShouldBeReturned() {
        Assert.assertTrue("RedemptionSettings doesn't include a step up url", redemptionSettingsResponse.getRewardsRedemptionSettings().getTwoFactorAuth().getStepUpUrl().contains("step-up"));
    }

    @And("^item \"([^\"]*)\" returns the title \"([^\"]*)\"$")
    public void itemShouldReturnTheTitle(int itemNumber, String title) throws Throwable {
        int itemIndex = itemNumber - 1;
        Assert.assertEquals("Incorrect title for item " + itemNumber, title, redemptionSettingsResponse.getRewardsRedemptionSettings().getItems()[itemIndex].getTitle());
    }

    @And("^item \"([^\"]*)\" should return the body \"([^\"]*)\"$")
    public void itemShouldReturnTheBody(int itemNumber, String bodyText) throws Throwable {
        int itemIndex = itemNumber - 1;
        Assert.assertEquals("Incorrect body for item " + itemNumber, bodyText, redemptionSettingsResponse.getRewardsRedemptionSettings().getItems()[itemIndex].getBody());
    }

    @And("^item \"([^\"]*)\" should return the icon \"([^\"]*)\"$")
    public void itemShouldReturnTheIcon(int itemNumber, String icon) throws Throwable {
        int itemIndex = itemNumber - 1;
        Assert.assertEquals("Incorrect icon for item " + itemNumber, icon, redemptionSettingsResponse.getRewardsRedemptionSettings().getItems()[itemIndex].getIcon());
    }

    @And("^item \"([^\"]*)\" should return title \"([^\"]*)\" for confirmation message \"([^\"]*)\"$")
    public void itemShouldReturnTitleForConfirmationMessageTitle(int itemNumber, String confirmationMessage, int confirmationMessageNumber) throws Throwable {
        int itemIndex = itemNumber - 1;
        int confirmationMessageIndex = confirmationMessageNumber - 1;
        Assert.assertEquals("Incorrect confirmation message title for item " + itemNumber, confirmationMessage, redemptionSettingsResponse.getRewardsRedemptionSettings().getItems()[itemIndex].getConfirmationMessages()[confirmationMessageIndex].getTitle());
    }

    @And("^item \"([^\"]*)\" should return button label \"([^\"]*)\" for confirmation message \"([^\"]*)\"$")
    public void itemShouldReturnButtonLabelForConfirmationMessageTitle(int itemNumber, String confirmationMessage, int confirmationMessageNumber) throws Throwable {
        int itemIndex = itemNumber - 1;
        int confirmationMessageIndex = confirmationMessageNumber - 1;
        Assert.assertEquals("Incorrect confirmation button label for item " + itemNumber, confirmationMessage, redemptionSettingsResponse.getRewardsRedemptionSettings().getItems()[itemIndex].getConfirmationMessages()[confirmationMessageIndex].getButtonLabel());
    }

    @And("^item \"([^\"]*)\" should not be destructive for confirmation message \"([^\"]*)\" title$")
    public void itemShouldNotBeDestructiveForConfirmationMessageTitle(int itemNumber, int confirmationMessageNumber) throws Throwable {
        int itemIndex = itemNumber - 1;
        int confirmationMessageIndex = confirmationMessageNumber - 1;
        Assert.assertFalse("Switching to item " + itemNumber + "should not be destructive", redemptionSettingsResponse.getRewardsRedemptionSettings().getItems()[itemIndex].getConfirmationMessages()[confirmationMessageIndex].getIsDestructive());

    }
}