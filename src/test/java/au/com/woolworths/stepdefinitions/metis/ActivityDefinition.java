package au.com.woolworths.stepdefinitions.metis;

import au.com.woolworths.graphql.parser.GraphqlParser;
import au.com.woolworths.helpers.metis.ActivityHelper;
import au.com.woolworths.model.metis.activity.ActivityResponse;
import au.com.woolworths.model.metis.activity.Groups;
import au.com.woolworths.model.metis.activity.Items;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import junit.framework.Assert;

import java.io.InputStream;
import java.util.List;
import java.util.logging.Logger;

public class ActivityDefinition extends ActivityHelper {
  private final static Logger logger = Logger.getLogger("ActivityDefinition.class");
  private ActivityResponse ActivityResponse = new ActivityResponse();

  @When("^the user selects an activity")
  public void theUserMakesARequestForTheActivity() throws Throwable {
    InputStream iStream = ActivityDefinition.class.getResourceAsStream("/gqlQueries/metis/queries/rewards/activity.graphql");
    String graphqlQuery = GraphqlParser.parseGraphql(iStream, null);
    ActivityResponse = iRetrieveMyActivity(graphqlQuery);
  }

  @Then("^the user should be able to see the activity details$")
  public void activityDetailsAreDisplayed() {

    Assert.assertTrue("Points balance is non positive ", ActivityResponse.getData().getRewardsActivityPage().getPointsBalance().getPointsBalance() >= 0);
    Assert.assertNotNull(ActivityResponse.getData().getRewardsActivityPage().getPointsBalance().getDisplayMessage());
    Assert.assertTrue("Points processing alert png is missing from mnemosyne", ActivityResponse.getData().getRewardsActivityPage().getPointsBalance().getStatusMarkUrl().contains("mobile-api.woolworths.com.au/zeus/mnemosyne/v1/public/points_processing_alert.png"));
    Assert.assertNotNull("Redemption percentage not displayed", ActivityResponse.getData().getRewardsActivityPage().getPointsBalance().getRedemptionPercentage());
    Assert.assertEquals("Check the number of groups inside activity is 1", 1, ActivityResponse.getData().getRewardsActivityPage().getList().getGroups().size());

    for (int i = 0; i < ActivityResponse.getData().getRewardsActivityPage().getList().getGroups().size(); i++) {
      Groups groups = ActivityResponse.getData().getRewardsActivityPage().getList().getGroups().get(i);
      Assert.assertEquals("Activity title does not show Recent activities", "Recent activities", groups.getTitle());
      List<Items> items = groups.getItems();
      for (Items item : items) {
        Assert.assertNotNull(item.getDisplayDate());
        Assert.assertNotNull(item.getDescription());
        Assert.assertNotNull(item.getDisplayValue());
        Assert.assertNotNull(item.getIconUrl());
        Assert.assertNotNull(item.getTransactionType());
      }
    }
  }
}