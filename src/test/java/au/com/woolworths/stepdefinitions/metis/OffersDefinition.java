package au.com.woolworths.stepdefinitions.metis;

import au.com.woolworths.graphql.parser.GraphqlParser;
import au.com.woolworths.helpers.metis.OffersHelper;
import au.com.woolworths.model.metis.offers.Item;
import au.com.woolworths.model.metis.offers.OffersResponse;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import junit.framework.Assert;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class OffersDefinition extends OffersHelper {

  private OffersResponse offersResponse;
  private int availableCount;
  private int readyForShopCount;
  private int endedCount;

  @When("^the user lands on the booster screen$")
  public void theUserLandsOnTheBoosterScreen() throws IOException {
    InputStream iStream = OffersDefinition.class.getResourceAsStream("/gqlQueries/metis/queries/rewards/offers.graphql");
    String graphqlQuery = GraphqlParser.parseGraphql(iStream, null);
    offersResponse = iRetrieveOffers(graphqlQuery);
  }

  @Then("^the user should see offers in the correct filter$")
  public void theUserShouldSeeOffersInTheCorrectFilter() {
    Assert.assertEquals("Offers", offersResponse.getData().getRewardsHomePage().getOffers().getTitle());
    countOffersInFilter();
    isOfferFilterTrue(availableCount, 0);
    isOfferFilterTrue(readyForShopCount, 1);
    isOfferFilterTrue(endedCount, 2);
  }

  @And("^the offers should contain relevant information$")
  public void theOffersShouldContainRelevantInformation() {

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

  public void isOfferFilterTrue(int count1, int filterNumber) {
    String count = Integer.toString(count1);
    String label = offersResponse.getData().getRewardsHomePage().getOffers().getFilters().get(filterNumber).getLabel();
    Assert.assertTrue("Filter offer number does not equal: " + label, label.contains(count));
  }

}