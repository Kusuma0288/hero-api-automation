package au.com.woolworths.stepdefinitions.metis;

import au.com.woolworths.graphql.parser.GraphqlParser;
import au.com.woolworths.helpers.metis.ApplePayHelper;
import au.com.woolworths.model.metis.applewallet.AppleWalletResponse;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import junit.framework.Assert;

import java.io.InputStream;
import java.util.logging.Logger;

public class ApplePayDefinition extends ApplePayHelper {
    private final static Logger logger = Logger.getLogger("ApplePayDefinition.class");
    private AppleWalletResponse AppleWalletResponse = new AppleWalletResponse();

    @When("^the user adds the rewards card to Apple Pay wallet")
    public void theUserSelectsApplePay() throws Throwable {
        InputStream iStream = ApplePayHelper.class.getResourceAsStream("/gqlQueries/metis/queries/rewards/appleWallet.graphql");

        String graphqlQuery = GraphqlParser.parseGraphql(iStream, null);
        AppleWalletResponse = iAddToAppleWallet(graphqlQuery);
    }


    @Then("^the rewards card is saved to Apple Pay")
    public void activityDetailsAreDisplayed() {
        String file = AppleWalletResponse.getData().getWalletPkPass().getFile();
        logger.info("Showing the connect Token: " + file);
        Assert.assertNotNull("the connect token should not be empty", file);
    }
}
