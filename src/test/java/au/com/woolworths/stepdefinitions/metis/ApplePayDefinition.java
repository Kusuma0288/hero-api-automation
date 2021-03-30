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

  @When("^the user adds the rewards card to Apple pay wallet$")
  public void theUserAddsTheRewardsCardToApplePayWallet() throws Throwable {
    InputStream iStream = ApplePayHelper.class.getResourceAsStream("/gqlQueries/metis/queries/rewards/appleWallet.graphql");
    String graphqlQuery = GraphqlParser.parseGraphql(iStream, null);
    AppleWalletResponse = iAddToAppleWallet(graphqlQuery);
  }

  @Then("^the rewards card is saved to Apple Pay")
  public void activityDetailsAreDisplayed() {
    String file = AppleWalletResponse.getData().getWalletPkPass().getFile();
    String message = base64decode(file);
    Assert.assertNotNull("the file token should not be empty", file);
    Assert.assertTrue("the icon should be present", message.contains("icon.png"));
    Assert.assertTrue("the logo should be present", message.contains("logo.png"));
    Assert.assertTrue("the 2X-logo should be present", message.contains("logo@2x.png"));
    Assert.assertTrue("the manifest json should be present", message.contains("manifest.json"));
    Assert.assertTrue("the pass json should be present", message.contains("pass.json"));
    Assert.assertTrue("the strip should be present", message.contains("strip.png"));
    Assert.assertTrue("the 2X-strip should be present", message.contains("strip@2x.png"));
  }


}
