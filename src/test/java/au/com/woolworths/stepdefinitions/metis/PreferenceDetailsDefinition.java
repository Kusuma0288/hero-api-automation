package au.com.woolworths.stepdefinitions.metis;


import au.com.woolworths.graphql.parser.GraphqlParser;
import au.com.woolworths.helpers.metis.PreferenceDetailsHelper;
import au.com.woolworths.model.metis.preferenceDetails.Details;
import au.com.woolworths.model.metis.preferenceDetails.PreferenceDetailsResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import junit.framework.Assert;

import java.io.InputStream;
import java.util.logging.Logger;

public class PreferenceDetailsDefinition extends PreferenceDetailsHelper {
  private final static Logger logger = Logger.getLogger("PreferenceDetailsDefinition.class");
  private PreferenceDetailsResponse preferenceDetailsResponse;

  @When("^the user selects his preference details for \"([^\"]*)\"$")
  public void theUserSelectsHisPreferenceDetailsFor(String key) throws Throwable {
    InputStream iStream = PreferenceDetailsDefinition.class.getResourceAsStream("/gqlQueries/metis/queries/rewards/preferenceDetails.graphql");
    ObjectNode variables = new ObjectMapper().createObjectNode();

    variables.put("key", key);
    String graphqlQuery = GraphqlParser.parseGraphql(iStream, variables);
    preferenceDetailsResponse = iRetrieveMyPreferenceDetails(graphqlQuery);
    logger.info("User preference has been called");
  }

  @Then("^the user should see the preference details for receipts$")
  public void theUserShouldSeeThePreferenceDetailsFor() {

    Assert.assertTrue(preferenceDetailsResponse.getData().getPreferenceDetails().getTitle().contains("Receipt"));
    Details details = preferenceDetailsResponse.getData().getPreferenceDetails().getDetails().get(0);
    Details groupDetails = preferenceDetailsResponse.getData().getPreferenceDetails().getDetails().get(1);
    Details footer = preferenceDetailsResponse.getData().getPreferenceDetails().getDetails().get(2);

    Assert.assertTrue("Title is not returned", details.getTitle().contains("Go paperless"));
    Assert.assertTrue("Subtitle is not returned", details.getSubtitle().contains("receipts"));
    Assert.assertTrue("Body is not returned", details.getBody().contains("receipts"));
    Assert.assertTrue("Image URL is not returned", details.getImageUrl().contains("ereceipt_preference_header.png"));

    Assert.assertTrue("Toggle title is not returned", groupDetails.getToggles().get(0).getTitle().contains("Woolworths"));
    Assert.assertTrue("Toggle subtitle is not returned", groupDetails.getToggles().get(0).getSubtitle().contains("eReceipts"));
    Assert.assertTrue("Toggle Id is not returned", groupDetails.getToggles().get(0).getId().contains("30101"));
    Assert.assertTrue("Toggle analytics label is not returned", groupDetails.getToggles().get(0).getAnalytics().getLabel().contains("Opt Out Paper"));
    Assert.assertTrue("Footer body is not returned", footer.getBody().contains("paper."));
    Assert.assertTrue("Footer terms and conditions is not returned", footer.getButton().getLabel().contains("T&Cs"));
    Assert.assertTrue("Footer terms is not returned", footer.getButton().getUrl().contains("terms.html"));

  }
}
