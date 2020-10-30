package au.com.woolworths.stepdefinitions.metis;

import au.com.woolworths.graphql.parser.GraphqlParser;
import au.com.woolworths.helpers.metis.OffersHelper;
import au.com.woolworths.model.metis.offers.OffersResponse;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.io.IOException;
import java.io.InputStream;

public class OffersDefinition extends OffersHelper {

  private OffersResponse offersResponse;

  @When("^the user selects fuel vouchers$")
  public void theUserSelectsOffers() throws IOException {
    InputStream iStream = OffersDefinition.class.getResourceAsStream("/gqlQueries/metis/queries/rewards/offers.graphql");
    String graphqlQuery = GraphqlParser.parseGraphql(iStream, null);
    offersResponse = iRetrieveOffers(graphqlQuery);
  }

  @When("^the user lands on the booster screen$")
  public void theUserLandsOnTheBoosterScreen() {
  }

  @Then("^the user should see offers in the correct filter$")
  public void theUserShouldSeeOffersInTheCorrectFilter() {
  }

  @And("^the offers should contain relevant information$")
  public void theOffersShouldContainRelevantInformation() {
  }
}