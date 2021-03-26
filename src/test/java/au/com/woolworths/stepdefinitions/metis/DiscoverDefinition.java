package au.com.woolworths.stepdefinitions.metis;

import au.com.woolworths.graphql.parser.GraphqlParser;
import au.com.woolworths.helpers.metis.DiscoverHelper;
import au.com.woolworths.model.metis.discover.Location;
import au.com.woolworths.model.metis.discover.RewardsDiscoverResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

import java.io.InputStream;
import java.util.List;

public class DiscoverDefinition extends DiscoverHelper {

  private RewardsDiscoverResponse rewardsDiscoverResponse;

  @When("^the user selects the discover section with latitude \"([^\"]*)\" and longitude \"([^\"]*)\"$")
  public void theUserSelectsTheDiscoverSectionWithLatitudeAndLongitude(Float latitude, Float longitude) throws Throwable {
    InputStream iStream = DiscoverDefinition.class.getResourceAsStream("/gqlQueries/metis/queries/rewards/discoverFeed.graphql");
    ObjectNode variables = new ObjectMapper().createObjectNode();

    variables.put("latitude", latitude);
    variables.put("longitude", longitude);
    String graphqlQuery = GraphqlParser.parseGraphql(iStream, variables);
    rewardsDiscoverResponse = iRetrieveDiscoverFeed(graphqlQuery);
  }

  @Then("^the user should see information on stores nearby$")
  public void theUserShouldSeeInformationOnStoresNearby() {

    List<Location> locations = rewardsDiscoverResponse.getData().getRewardsDiscover().getItems().get(0).getLocations();
    for (Location location : locations) {
      Assert.assertTrue(location.getIcon().matches(".*(woolworths|bws|bigw|caltex_woolworths|caltex)"), "Location icon is not returned");
      Assert.assertTrue(location.getAddress().matches("^[ A-Za-z0-9-]*$"), "Location address is not returned");
      Assert.assertTrue(location.getDistance().contains("km"), "Location distance is not returned");
      Assert.assertNotNull(location.getStoreNo(), "Location store no is not returned");
      Assert.assertTrue(location.getDivision().matches(".*(SUPERMARKETS|BWS|BIGW|PETROL)"), "Location division is not returned");
      Assert.assertNotNull(location.getLatitude(), "Location latitude is not returned");
      Assert.assertNotNull(location.getLongitude(), "Location longitude is not returned");
    }

  }

}