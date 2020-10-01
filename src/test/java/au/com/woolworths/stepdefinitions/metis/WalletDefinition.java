package au.com.woolworths.stepdefinitions.metis;

import au.com.woolworths.graphql.parser.GraphqlParser;
import au.com.woolworths.helpers.metis.RewardsCardWithWalletHelper;
import au.com.woolworths.model.apigee.payment.iFrameResponse;
import au.com.woolworths.model.metis.card.FetchAddSchemeCardURLResponse;
import au.com.woolworths.model.metis.card.RewardsCardHomePageWithWalletResponse;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import junit.framework.Assert;

import java.io.InputStream;

public class WalletDefinition extends RewardsCardWithWalletHelper {

  private RewardsCardHomePageWithWalletResponse rewardsCardHomePageWithWalletResponse;

  @When("^the user goes to the card screen$")
  public void goesToCardScreen() throws Throwable {
    InputStream iStream = WalletDefinition.class.getResourceAsStream("/gqlQueries/metis/rewardsCardHomePageWithWallet.graphql");
    String graphqlQuery = GraphqlParser.parseGraphql(iStream, null);

    rewardsCardHomePageWithWalletResponse = iRetrieveMyRewardsCardWithWallet(graphqlQuery);
  }

  @Then("^the user should see the wallet is empty$")
  public void shouldSeeEmptyWallet() {
    Assert.assertEquals("Wallet home page message is not as expected", "Add a bank card and redeem Everyday Rewards in one easy tap", rewardsCardHomePageWithWalletResponse.getData().getWalletHomePage().getContent());
    Assert.assertEquals("Wallet state is not as expected", "ADD_CARD", rewardsCardHomePageWithWalletResponse.getData().getWalletHomePage().getAction());
  }

  @Then("^the user should be able to add a new card$")
  public void canAddNewCard() throws Throwable {
    InputStream iStream = WalletDefinition.class.getResourceAsStream("/gqlQueries/metis/FetchAddSchemeCardURL.graphql");
    String graphqlQuery = GraphqlParser.parseGraphql(iStream, null);

    // Get the add card url
    FetchAddSchemeCardURLResponse fetchAddSchemeCardURLResponse = iRetrieveAddSchemeCardURL(graphqlQuery);

    String url = fetchAddSchemeCardURLResponse.getData().getAddSchemeCard().getUrl();
    String sessionID = url.substring(url.lastIndexOf("/") + 1);

    // TODO - Handle different environments when we start using them
    System.setProperty("useDev1", "true");

    // Submit the card
    iFrameResponse iframeResponse = postiFrameCardDetails(sessionID);

    Assert.assertEquals("Wallet home page message is not as expected", "ACCEPTED", iframeResponse.getStatus().getResponseText());
    Assert.assertEquals("Wallet home page message is not as expected", "00", iframeResponse.getStatus().getResponseCode());
    Assert.assertNull("Wallet home page message is not as expected", iframeResponse.getStatus().getError());
    Assert.assertEquals("Login response is missing a valid rewards card number", "UNVERIFIED_PERSISTENT", iframeResponse.getPaymentInstrument().getStatus());

    // TODO - Call FetchPaymentInstruments
  }
}