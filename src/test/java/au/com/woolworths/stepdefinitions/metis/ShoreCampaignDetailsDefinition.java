package au.com.woolworths.stepdefinitions.metis;

import au.com.woolworths.graphql.parser.GraphqlParser;
import au.com.woolworths.helpers.metis.ShoreCampaignHelper;
import au.com.woolworths.model.metis.shorecampaigndetails.Footer;
import au.com.woolworths.model.metis.shorecampaigndetails.Header;
import au.com.woolworths.model.metis.shorecampaigndetails.Product;
import au.com.woolworths.model.metis.shorecampaigndetails.RewardsShoreCampaignDetailsResponse;
import au.com.woolworths.utils.Utilities;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import junit.framework.Assert;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.logging.Logger;

public class ShoreCampaignDetailsDefinition extends ShoreCampaignHelper {

  private final static Logger logger = Logger.getLogger("ShoreCampaignDetailsDefinition.class");
  private RewardsShoreCampaignDetailsResponse rewardsShoreCampaignDetailsResponse;

  @When("^the user selects the shore collection banner$")
  public void theUserSelectsTheShoreCollectionBanner() throws IOException {
    InputStream iStream = ShoreCampaignDetailsDefinition.class.getResourceAsStream("/gqlQueries/metis/queries/rewards/shoreCampaignDetails.graphql");
    String graphqlQuery = GraphqlParser.parseGraphql(iStream, null);
    rewardsShoreCampaignDetailsResponse = iRetrieveShoreCampaignDetails(graphqlQuery);
    logger.info("Returned the campaign for the collection banner");
  }

  @Then("^the user should see the all the collection containers details$")
  public void theUserShouldSeeTheAllTheCollectionContainersDetails() {
    Header header = rewardsShoreCampaignDetailsResponse.getData().getRewardsShoreCampaignDetails().getHeader();
    Assert.assertNotNull("shore header is not returned ", header.getContent().getHeading());
    Assert.assertTrue("shore image is not returned ", header.getImageUrl().contains("extra_banner_no_credits_after_xmas_v2.jpg"));
    Assert.assertNotNull("shore alternate text not shown ", header.getImageAlt());
    Assert.assertTrue("shore body text is not correct ", header.getContent().getBody().contains("Container Credit"));
    Assert.assertTrue("shore credit field is not numeric ", Utilities.isNumeric(header.getContent().getCredits()));
    Assert.assertNotNull("date is not return for shore ", header.getContent().getExpiry());


    List<Product> products = rewardsShoreCampaignDetailsResponse.getData().getRewardsShoreCampaignDetails().getProducts();
    for (Product product : products) {
      Assert.assertNotNull("Shore product description is not returned ", product.getDescription());
      Assert.assertNotNull("Shore alternate text not returned for product ", product.getImageAlt());
      Assert.assertTrue("Shore image url is not returned for product ", product.getImageUrl().contains("shore"));
      Assert.assertTrue("price is not numeric ", Utilities.isNumeric(product.getPrice().substring(1)));
      if (product.getRedemptionStatus().getStatus().contains("enough")) {
        Assert.assertEquals("Colour for the product redemption text is not green ", product.getRedemptionStatus().getColour(), "#02AC45");
      } else {
        Assert.assertEquals("Colour for the product redemption text is not orange ", product.getRedemptionStatus().getColour(), "#FF5200");
      }
    }

    Footer footer = rewardsShoreCampaignDetailsResponse.getData().getRewardsShoreCampaignDetails().getFooter();
    Assert.assertTrue("Shore footer button is not returned", footer.getButton().getLabel().equalsIgnoreCase("Shop now"));
    Assert.assertEquals("Shore Footer url is not returned", footer.getButton().getUrl(), "https://www.woolworths.com.au/glasscontainers");
    Assert.assertNotNull("Shore footer alternative image text not returned", footer.getImageAlt());
    Assert.assertTrue("Shore footer image not returned ", footer.getImageUrl().contains("shore"));
    Assert.assertNotNull("Footer text is not returned ", footer.getTermsAndConditions());
    Assert.assertTrue("Footer title Terms and Conditions not returned ", footer.getTitle().equalsIgnoreCase("Terms and Conditions"));
  }
}