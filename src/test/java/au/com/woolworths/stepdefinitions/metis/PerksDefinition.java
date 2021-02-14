package au.com.woolworths.stepdefinitions.metis;

import au.com.woolworths.graphql.parser.GraphqlParser;
import au.com.woolworths.helpers.metis.PerksHelper;
import au.com.woolworths.model.metis.perks.Banner;
import au.com.woolworths.model.metis.perks.Perk;
import au.com.woolworths.model.metis.perks.RewardsPerksResponse;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.logging.Logger;

public class PerksDefinition extends PerksHelper {

  private final static Logger logger = Logger.getLogger("PerksDefinition.class");
  private RewardsPerksResponse response;

  @When("^the user taps on the extra page$")
  public void theUserTapsOnTheExtraPage() throws IOException {
    InputStream iStream = PerksDefinition.class.getResourceAsStream("/gqlQueries/metis/queries/rewards/perks.graphql");
    String graphqlQuery = GraphqlParser.parseGraphql(iStream, null);
    response = iRetrievePerks(graphqlQuery);

    logger.info("Calling perks section");
  }

  @Then("^the user should be able to see his perks$")
  public void theUserShouldBeAbleToSeeHisPerks() {
    Banner banner = response.getData().getRewardsPerks().getBanner();
    Assert.assertTrue("The shore banner is not returned", banner.getBannerImageUrl().contains(".jpg"));
    Assert.assertEquals("No deeplink found", "DEEP_LINK", banner.getLink().getAction());
    Assert.assertTrue("The shore banner is not returned", banner.getLink().getUrl().contains("shoreCampaign"));

    Assert.assertEquals("Member exclusives title is not returned", response.getData().getRewardsPerks().getTitle(), "Member exclusives");
    List<Perk> perks = response.getData().getRewardsPerks().getPerks();

    for (Perk perk : perks) {
      Assert.assertNotNull("Partner name is not returned", perk.getPartnerName());
      Assert.assertNotNull("Perks title is not returned", perk.getTitle());
      Assert.assertNotNull("Description is not returned", perk.getDescription());
      Assert.assertNotNull("Title for t&d is not returned", perk.getTermsAndConditions().getTitle());
      Assert.assertNotNull("Terms and conditions is not returned", perk.getTermsAndConditions().getTermsAndConditions());
      Assert.assertNotNull("Card image url is incorrect", perk.getCardImageUrl());
      Assert.assertNotNull("Header image url is not returned", perk.getHeaderImageUrl());
      Assert.assertNotNull("Title for button is not returned for Perks", perk.getButton().getTitle());
      Assert.assertNull("Internal url should be null", perk.getButton().getInternalUrl());
      Assert.assertNotNull("Colour for branding not returned", perk.getBrand().getBgColour());
      Assert.assertNotNull("Logo url is not returned", perk.getBrand().getLogoUrl());
      Assert.assertNotNull("Text colour is not returned", perk.getBrand().getTextColour());
    }
  }

}
