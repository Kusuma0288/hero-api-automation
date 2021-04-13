package au.com.woolworths.stepdefinitions.metis;

import au.com.woolworths.graphql.parser.GraphqlParser;
import au.com.woolworths.helpers.metis.GooglePayHelper;
import au.com.woolworths.model.metis.googlewallet.WalletResponse;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import junit.framework.Assert;

import java.io.InputStream;
import java.util.logging.Logger;

public class GooglePayDefinition extends GooglePayHelper {
  private final static Logger logger = Logger.getLogger("GooglePayDefinition.class");
  private WalletResponse WalletResponse = new WalletResponse();

  @When("^the user adds the rewards card to Google pay wallet")
  public void theUserSelectsGooglePay() throws Throwable {
    InputStream iStream = GooglePayHelper.class.getResourceAsStream("/gqlQueries/metis/queries/rewards/googleWallet.graphql");

    String graphqlQuery = GraphqlParser.parseGraphql(iStream, null);
    WalletResponse = iAddToWallet(graphqlQuery);
  }


  @Then("^the rewards card is saved to Google Pay")
  public void activityDetailsAreDisplayed() {
    String connectToken = WalletResponse.getData().getRewardsDigitalWalletToken().getConnectToken();
    logger.info("Showing the connect Token: " + connectToken);
    Assert.assertNotNull("the connect token should not be empty", connectToken);
  }
}
