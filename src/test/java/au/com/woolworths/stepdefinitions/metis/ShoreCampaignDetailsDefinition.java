package au.com.woolworths.stepdefinitions.metis;

import au.com.woolworths.graphql.parser.GraphqlParser;
import au.com.woolworths.helpers.metis.ShoreCampaignHelper;
import au.com.woolworths.model.metis.shorecampaigndetails.Footer;
import au.com.woolworths.model.metis.shorecampaigndetails.Header;
import au.com.woolworths.model.metis.shorecampaigndetails.Product;
import au.com.woolworths.model.metis.shorecampaigndetails.RewardsShoreCampaignDetailsResponse;
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
    Assert.assertNotNull(header.getTitle());
    Assert.assertNotNull(header.getImageUrl());
    Assert.assertNotNull(header.getImageAlt());
    Assert.assertNotNull(header.getBody());
    Assert.assertNotNull(header.getCredits());
    Assert.assertNotNull(header.getExpiry());


    List<Product> products = rewardsShoreCampaignDetailsResponse.getData().getRewardsShoreCampaignDetails().getProducts();
    for (Product product : products) {
      Assert.assertNotNull(product.getDescription());
      Assert.assertNotNull(product.getImageAlt());
      Assert.assertNotNull(product.getImageUrl());
      Assert.assertNotNull(product.getPrice());
      if (product.getRedemptionStatus().getStatus().contains("credits")) {
        Assert.assertEquals("Colour for the product redemption text is not orange", product.getRedemptionStatus().getColour(), "#FF5200");
      }
    }

    Footer footer = rewardsShoreCampaignDetailsResponse.getData().getRewardsShoreCampaignDetails().getFooter();
    Assert.assertNotNull(footer.getButton().getLabel());
    Assert.assertEquals("Footer url is not returned", footer.getButton().getUrl(), "https://www.woolworths.com.au");
    Assert.assertNotNull(footer.getImageAlt());
    Assert.assertNotNull(footer.getImageUrl());
    Assert.assertNotNull(footer.getTermsAndConditions());
    Assert.assertNotNull(footer.getTitle());
  }
}