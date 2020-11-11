package au.com.woolworths.stepdefinitions.metis;

import au.com.woolworths.graphql.parser.GraphqlParser;
import au.com.woolworths.helpers.metis.OffersHelper;
import au.com.woolworths.model.metis.offers.Filter;
import au.com.woolworths.model.metis.offers.Item;
import au.com.woolworths.model.metis.offers.OffersResponse;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import junit.framework.Assert;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.logging.Logger;

public class OffersDefinition extends OffersHelper {
  private final static Logger logger = Logger.getLogger("OffersDefinition.class");

  private OffersResponse offersResponse;
  private int availableCount;
  private int readyForShopCount;
  private int endedCount;

  @When("^the user lands on the booster screen$")
  public void theUserLandsOnTheBoosterScreen() throws IOException {
    InputStream iStream = OffersDefinition.class.getResourceAsStream("/gqlQueries/metis/queries/rewards/offers.graphql");
    String graphqlQuery = GraphqlParser.parseGraphql(iStream, null);
    offersResponse = iRetrieveOffers(graphqlQuery);
    logger.info("Requesting offers information");
  }

  @Then("^the user should see offers in the correct filter$")
  public void theUserShouldSeeOffersInTheCorrectFilter() {
    countOffersInFilter();
    isOffersCountEqualToFilter(availableCount, 0);
    isOffersCountEqualToFilter(readyForShopCount, 1);
    isOffersCountEqualToFilter(endedCount, 2);
  }

  @And("^the user does not have any offers$")
  public void theUserDoesNotHaveAnyOffers() {
    countOffersInFilter();
    Assert.assertEquals("Available count should be zero", 0, availableCount);
    Assert.assertEquals("Ready for shop count should be zero", 0, readyForShopCount);
    Assert.assertEquals("Ended count should be zero", 0, endedCount);
  }

  @Then("^the user should see the empty state messages$")
  public void theUserShouldSeeTheEmptyStateMessages() {
    List<Filter> filters = offersResponse.getData().getRewardsHomePage().getOffers().getFilters();
    for (Filter filter : filters) {
      Assert.assertEquals("Icon is not returned", filter.getMessage().getIcon(), "ribbon");
      Assert.assertNotNull("Title is not returned", filter.getMessage().getTitle());
      Assert.assertNotNull("Message is not returned", filter.getMessage().getMessage());
    }
  }

  @And("^the offers should contain relevant information$")
  public void theOffersShouldContainRelevantInformation() {
    List<Item> items = offersResponse.getData().getRewardsHomePage().getOffers().getItems();

    Assert.assertNotNull("Offer title not returned", offersResponse.getData().getRewardsHomePage().getOffers().getTitle());
    for (Item item : items) {
      Assert.assertEquals("Offer id should not be more than expected characters", 36, item.getOfferId().length());
      Assert.assertTrue("Image is missing from offer with Id " + item.getOfferId(), item.getImage().matches(".*(png|jpg)"));
      Assert.assertNotNull("Rewards title not returned", item.getTitle());
      Assert.assertEquals("Subtitle should be empty string", "", item.getSubtitle());
      Assert.assertNotNull("Rewards summary not returned for offer", item.getSummary());
      Assert.assertNull("Rewards body not returned for offer", item.getBody());
      Assert.assertNull("Rewards body not returned for offer", item.getTermsAndConditions());
      Assert.assertNotNull("Rewards expiry not returned for offer", item.getDisplayExpiry());
      Assert.assertNotNull("Rewards display status not returned for offer", item.getDisplayStatus());
      Assert.assertNotNull("Rewards offer status not returned", item.getOfferStatus());
      Assert.assertNotNull("Rewards offer icon not returned", item.getStatusIcons());
      Assert.assertEquals("CommId should not be more than expected characters", 36, item.getOfferAnalytics().getCommId().length());
      Assert.assertEquals("Uoid should not be more than expected characters", 36, item.getOfferAnalytics().getUoid().length());
      Assert.assertNotNull("Rewards offer campaign code not returned", item.getOfferAnalytics().getCampaignCode());
      Assert.assertNotNull("Rewards offer campaign stream not returned", item.getOfferAnalytics().getCampaignStream());
      Assert.assertTrue("Non app channel cannot be send to app", item.getOfferAnalytics().getChannel().contains("app"));
      Assert.assertNotNull("Rewards offer analytics status not returned", item.getOfferAnalytics().getStatus());
      Assert.assertTrue("Leaf logo is missing from mnemosyne " + item.getBrandLogoUrl(), item.getBrandLogoUrl().matches(".*(logo).*(png)"));
    }
  }

  public void countOffersInFilter() {
    List<Item> items = offersResponse.getData().getRewardsHomePage().getOffers().getItems();

    for (Item item : items) {
      switch (item.getDisplayStatus()) {
        case "Boost":
          availableCount++;
          break;
        case "Ready to shop":
          readyForShopCount++;
          break;
        case "Ended":
        case "Missed":
        case "Pending":
          endedCount++;
          break;
      }
    }
  }

  public void isOffersCountEqualToFilter(int count, int index) {
    String strCount = Integer.toString(count);
    String label = offersResponse.getData().getRewardsHomePage().getOffers().getFilters().get(index).getLabel();
    Assert.assertTrue("Filter offer number does not equal: " + label, label.contains(strCount));
  }

}