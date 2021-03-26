package au.com.woolworths.stepdefinitions.metis;


import au.com.woolworths.graphql.parser.GraphqlParser;
import au.com.woolworths.helpers.metis.OfferDetailsHelper;
import au.com.woolworths.model.metis.offerdetails.OfferDetailsResponse;
import au.com.woolworths.model.metis.offerdetails.RewardsOfferResponse;
import au.com.woolworths.utils.TestProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import junit.framework.Assert;

import java.io.InputStream;
import java.util.logging.Logger;

public class OfferDetailsDefinition extends OfferDetailsHelper {
  private final static Logger logger = Logger.getLogger("OfferDetailsDefinition.class");
  private OfferDetailsResponse OfferDetailsResponse = new OfferDetailsResponse();

  @When("^the user selects an individual offer on the Points screen with \"([^\"]*)\"$")
  public void theUserMakesARequestForTheOfferDetails(String offerId) throws Throwable {
    logger.info("the offer details for requested offer " + TestProperties.get(offerId));
    InputStream iStream = ActivityDefinition.class.getResourceAsStream("/gqlQueries/metis/queries/rewards/offerDetails.graphql");
    ObjectNode variables = new ObjectMapper().createObjectNode();

    variables.put("offerId", TestProperties.get(offerId));
    String graphqlQuery = GraphqlParser.parseGraphql(iStream, variables);
    OfferDetailsResponse = iRetrieveMyOfferDetails(graphqlQuery);
  }

  @Then("^the user should be able to see the offer details for \"([^\"]*)\"$")
  public void offerDetailsAreDisplayed(String offerId) {
    RewardsOfferResponse rewardsOffer = OfferDetailsResponse.getData().getRewardsOffer();
    Assert.assertEquals("Offer id in response was different to request", TestProperties.get(offerId), rewardsOffer.getOfferId());
    Assert.assertEquals("Offer id should not be more than expected characters", 36, rewardsOffer.getOfferId().length());
    Assert.assertTrue("Image is missing from offer details", rewardsOffer.getImage().contains("jpg"));
    Assert.assertNotNull("Rewards title not returned", rewardsOffer.getTitle());
    Assert.assertEquals("Subtitle length should be always empty", "", rewardsOffer.getSubtitle());
    Assert.assertNotNull("Rewards summary not returned for offer", rewardsOffer.getSummary());
    Assert.assertNotNull("Rewards body not returned for offer", rewardsOffer.getBody());
    Assert.assertNotNull("Rewards terms and conditions not returned for offer", rewardsOffer.getTermsAndConditions());
    Assert.assertNotNull("Rewards expiry not returned for offer", rewardsOffer.getDisplayExpiry());
    Assert.assertNotNull("Rewards display status not returned for offer", rewardsOffer.getDisplayStatus());
    Assert.assertNotNull("Rewards offer status not returned", rewardsOffer.getOfferStatus());
    Assert.assertNotNull("Rewards offer icon not returned", rewardsOffer.getStatusIcons());

    Assert.assertEquals("CommId should not be more than expected characters", 36, rewardsOffer.getOfferAnalytics().getCommId().length());
    Assert.assertEquals("Uoid should not be more than expected characters", 36, rewardsOffer.getOfferAnalytics().getUoid().length());
    Assert.assertNotNull("Rewards offer campaign code not returned", rewardsOffer.getOfferAnalytics().getCampaignCode());
    Assert.assertNotNull("Rewards offer campaign stream not returned", rewardsOffer.getOfferAnalytics().getCampaignStream());
    Assert.assertTrue("Non app channel cannot be send to app", rewardsOffer.getOfferAnalytics().getChannel().contains("app"));
    Assert.assertNotNull("Rewards offer analytics status not returned", rewardsOffer.getOfferAnalytics().getStatus());
    Assert.assertTrue("Rewards leaf logo is missing from mnemosyne", rewardsOffer.getBrandLogoUrl().contains("leaf_rewards_logo.png"));
  }
}
