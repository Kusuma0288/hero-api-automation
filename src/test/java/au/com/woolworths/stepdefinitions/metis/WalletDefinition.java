package au.com.woolworths.stepdefinitions.metis;

import au.com.woolworths.graphql.parser.GraphqlParser;
import au.com.woolworths.helpers.metis.RewardsCardWithWalletHelper;
import au.com.woolworths.model.apigee.payment.iFrameResponse;
import au.com.woolworths.model.metis.card.add_scheme_card.FetchAddSchemeCardURLResponse;
import au.com.woolworths.model.metis.card.delete_scheme_card.DeleteSchemeCardResponse;
import au.com.woolworths.model.metis.card.home_page_with_wallet.RewardsCardHomePageWithWalletResponse;
import au.com.woolworths.model.metis.card.payment_instruments.FetchPaymentInstrumentsResponse;
import au.com.woolworths.model.metis.card.update_scheme_card.FetchUpdateSchemeCardURLResponse;
import au.com.woolworths.model.metis.card.view_user_preference.FetchUserPreferencesResponse;
import au.com.woolworths.utils.TestProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import junit.framework.Assert;

import java.io.IOException;
import java.io.InputStream;

public class WalletDefinition extends RewardsCardWithWalletHelper {

  final int cardNumberLength = TestProperties.get("CARD_NUMBER").length();
  private RewardsCardHomePageWithWalletResponse rewardsCardHomePageWithWalletResponse;
  private FetchPaymentInstrumentsResponse fetchPaymentInstrumentsResponse;
  private FetchUserPreferencesResponse fetchUserPreferencesResponse;
  private String fetchAddSchemeCardURL;
  private String fetchUpdateSchemeCardURL;
  private String cardToUpdate;

  @When("^the user goes to the card screen$")
  public void goesToCardScreen() throws IOException {
    InputStream iStream = WalletDefinition.class.getResourceAsStream("/gqlQueries/metis/queries/wallet/rewardsCardHomePageWithWallet.graphql");
    String graphqlQuery = GraphqlParser.parseGraphql(iStream, null);

    rewardsCardHomePageWithWalletResponse = iRetrieveMyRewardsCardWithWallet(graphqlQuery);
  }

  @Then("^the user should see the wallet is empty$")
  public void shouldSeeEmptyWallet() throws IOException {
    // Ensure the user does not have an existing card before we attempt to add a new one

    if (rewardsCardHomePageWithWalletResponse.getData().getWalletHomePage().getAction().equals("SCAN")) {
      canRemoveCard();
      goesToCardScreen();
    }

    Assert.assertEquals("Wallet home page message is not as expected", "Add a bank card and redeem Everyday Rewards in one easy tap", rewardsCardHomePageWithWalletResponse.getData().getWalletHomePage().getContent());
    Assert.assertEquals("Wallet state is not as expected", "ADD_CARD", rewardsCardHomePageWithWalletResponse.getData().getWalletHomePage().getAction());
  }

  @Then("^the user should see the wallet has a card$")
  public void shouldSeeWalletHasCard() throws IOException {
    // Ensure the user has an existing card so we can remove it
    if (rewardsCardHomePageWithWalletResponse.getData().getWalletHomePage().getAction().equals("ADD_CARD")) {
      canAddNewCard();
    }

    Assert.assertEquals("Wallet title is not as expected", "Scan to Everyday Pay", rewardsCardHomePageWithWalletResponse.getData().getWalletHomePage().getTitle());
    Assert.assertEquals("Wallet state is not as expected", "SCAN", rewardsCardHomePageWithWalletResponse.getData().getWalletHomePage().getAction());
  }

  @Then("^the user should be able to add a new card$")
  public void canAddNewCard() throws IOException {
    getAddCardURL();
    submitCard();
    goesToCardScreen();
    shouldSeeWalletHasCard();
    canViewCardDetails();
  }

  @Then("^the user should be able to remove a card$")
  public void canRemoveCard() throws IOException {
    String cardToDelete = getCardID();
    ObjectNode variables = new ObjectMapper().createObjectNode();
    variables.put("id", cardToDelete);
    InputStream iStreamDelete = WalletDefinition.class.getResourceAsStream("/gqlQueries/metis/mutations/wallet/deleteSchemeCard.graphql");
    String graphqlQueryDelete = GraphqlParser.parseGraphql(iStreamDelete, variables);

    DeleteSchemeCardResponse deleteSchemeCardResponse = iRemoveSchemeCard(graphqlQueryDelete);

    Assert.assertTrue("Delete scheme card was not successful", deleteSchemeCardResponse.getData().getDeleteSchemeCard().getSuccess());
    Assert.assertEquals("Delete scheme card response message is not as expected", "PaymentInstrument " + cardToDelete + " has been successfully deleted.", deleteSchemeCardResponse.getData().getDeleteSchemeCard().getMessage());
  }

  @Then("^the user should be able to update a card$")
  public void canUpdateCard() throws IOException {
    cardToUpdate = getCardID();
    ObjectNode variables = new ObjectMapper().createObjectNode();
    variables.put("id", cardToUpdate);
    InputStream iStreamUpdate = WalletDefinition.class.getResourceAsStream("/gqlQueries/metis/queries/wallet/fetchUpdateSchemeCardURL.graphql");
    String graphqlQueryUpdateCard = GraphqlParser.parseGraphql(iStreamUpdate, variables);

    FetchUpdateSchemeCardURLResponse fetchUpdateSchemeCardURLResponse = iRetrieveUpdateSchemeCardURL(graphqlQueryUpdateCard);
    fetchUpdateSchemeCardURL = fetchUpdateSchemeCardURLResponse.getData().getUpdateSchemeCard().getUrl();
    submitUpdateCard();
  }

  @Then("^the user should be able to view the card details$")
  public void canViewCardDetails() throws IOException {
    InputStream iStream = WalletDefinition.class.getResourceAsStream("/gqlQueries/metis/queries/wallet/fetchPaymentInstruments.graphql");
    String graphqlQuery = GraphqlParser.parseGraphql(iStream, null);

    fetchPaymentInstrumentsResponse = iRetrievePaymentInstruments(graphqlQuery);

    int instrumentCardNumberLength = fetchPaymentInstrumentsResponse.getData().getPaymentInstruments()[0].getCardNumber().length();

    // TODO - When we start allowing more than 1 instrument this will need to be updated
    Assert.assertEquals("The number of payment instruments is not equal to 1", 1, fetchPaymentInstrumentsResponse.getData().getPaymentInstruments().length);
    Assert.assertTrue("The payment instrument card number is not obfuscated", fetchPaymentInstrumentsResponse.getData().getPaymentInstruments()[0].getCardNumber().contains("••••"));
    Assert.assertEquals("The payment instrument card number last 4 digits do not match", TestProperties.get("CARD_NUMBER").substring(cardNumberLength - 4), fetchPaymentInstrumentsResponse.getData().getPaymentInstruments()[0].getCardNumber().substring(instrumentCardNumberLength - 4));
    Assert.assertEquals("The payment instrument does not have a valid status", "VALID", fetchPaymentInstrumentsResponse.getData().getPaymentInstruments()[0].getStatus());
    Assert.assertNull("The payment instrument does not have a null lastUsed value", fetchPaymentInstrumentsResponse.getData().getPaymentInstruments()[0].getLastUsed());
  }

  @When("^the user goes to the account screen$")
  public void goesToAccountScreen() throws IOException {
    InputStream iStream = WalletDefinition.class.getResourceAsStream("/gqlQueries/metis/queries/wallet/fetchUserPreferences.graphql");
    String graphqlQuery = GraphqlParser.parseGraphql(iStream, null);
    fetchUserPreferencesResponse = iRetrieveViewWallet(graphqlQuery);
  }

  @Then("^the user should see a badge for new user$")
  public void hasViewedWalletBadge() {
    if (fetchUserPreferencesResponse.getData().getUserPreferences().isHasViewedWallet()) {

    }

    Assert.assertTrue("View User Preferences is set", fetchUserPreferencesResponse.getData().getUserPreferences().isHasViewedWallet());

  }

  private void getAddCardURL() throws IOException {
    InputStream iStream = WalletDefinition.class.getResourceAsStream("/gqlQueries/metis/queries/wallet/fetchAddSchemeCardURL.graphql");
    String graphqlQuery = GraphqlParser.parseGraphql(iStream, null);

    FetchAddSchemeCardURLResponse fetchAddSchemeCardURLResponse = iRetrieveAddSchemeCardURL(graphqlQuery);

    fetchAddSchemeCardURL = fetchAddSchemeCardURLResponse.getData().getAddSchemeCard().getUrl();
  }

  private void submitCard() throws IOException {
    String host = fetchAddSchemeCardURL.substring(0, fetchAddSchemeCardURL.indexOf("/container"));
    String sessionID = fetchAddSchemeCardURL.substring(fetchAddSchemeCardURL.lastIndexOf("/") + 1);

    iFrameResponse iframeResponse = postiFrameCardDetails(sessionID, host);

    Assert.assertEquals("Card iFrame status response message is not as expected", "ACCEPTED", iframeResponse.getStatus().getResponseText());
    Assert.assertEquals("Card iFrame status response code is not as expected", "00", iframeResponse.getStatus().getResponseCode());
    Assert.assertNull("Card iFrame status has an error - " + iframeResponse.getStatus().getError(), iframeResponse.getStatus().getError());
    Assert.assertEquals("Card iFrame payment instrument status is not as expected", "UNVERIFIED_PERSISTENT", iframeResponse.getPaymentInstrument().getStatus());
    Assert.assertEquals("Card iFrame payment instrument suffix is not as expected", TestProperties.get("CARD_NUMBER").substring(cardNumberLength - 4), iframeResponse.getPaymentInstrument().getSuffix());
    Assert.assertEquals("Card iFrame payment instrument expiry month is not as expected", TestProperties.get("EXPIRY_MONTH"), iframeResponse.getPaymentInstrument().getExpiryMonth());
    Assert.assertEquals("Card iFrame payment instrument expiry year is not as expected", TestProperties.get("EXPIRY_YEAR"), iframeResponse.getPaymentInstrument().getExpiryYear());
  }

  private void submitUpdateCard() throws IOException {
    String host = fetchUpdateSchemeCardURL.substring(0, fetchUpdateSchemeCardURL.indexOf("/container"));
    String sessionID = fetchUpdateSchemeCardURL.substring(fetchUpdateSchemeCardURL.lastIndexOf("cvvExpiry/") + 10, fetchUpdateSchemeCardURL.lastIndexOf("/"));

    iFrameResponse iframeResponse = postUpdateCardRequest(cardToUpdate, sessionID, host);

    Assert.assertEquals("Card iFrame status response message is not as expected", "ACCEPTED", iframeResponse.getStatus().getResponseText());
    Assert.assertEquals("Card iFrame status response code is not as expected", "00", iframeResponse.getStatus().getResponseCode());
    Assert.assertNull("Card iFrame status has an error - " + iframeResponse.getStatus().getError(), iframeResponse.getStatus().getError());
    Assert.assertEquals("Card iFrame item ID is not as expected", cardToUpdate, iframeResponse.getItemId());
    Assert.assertNull("Card iFrame status has an error - " + iframeResponse.getStatus().getError(), iframeResponse.getStatus().getError());
    Assert.assertNull("Card iFrame fraud response client ID is not null - " + iframeResponse.getFraudResponse().getFraudClientId(), iframeResponse.getFraudResponse().getFraudClientId());
    Assert.assertNull("Card iFrame fraud response reason code is not null - " + iframeResponse.getFraudResponse().getFraudReasonCd(), iframeResponse.getFraudResponse().getFraudReasonCd());
    Assert.assertNull("Card iFrame fraud response decision is not null - " + iframeResponse.getFraudResponse().getFraudDecision(), iframeResponse.getFraudResponse().getFraudDecision());
  }

  private String getCardID() throws IOException {
    InputStream iStreamInstruments = WalletDefinition.class.getResourceAsStream("/gqlQueries/metis/queries/wallet/fetchPaymentInstruments.graphql");
    String graphqlQueryInstruments = GraphqlParser.parseGraphql(iStreamInstruments, null);
    fetchPaymentInstrumentsResponse = iRetrievePaymentInstruments(graphqlQueryInstruments);
    return fetchPaymentInstrumentsResponse.getData().getPaymentInstruments()[0].getId();
  }
}