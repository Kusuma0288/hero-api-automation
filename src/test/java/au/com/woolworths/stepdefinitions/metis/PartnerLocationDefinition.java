package au.com.woolworths.stepdefinitions.metis;


import au.com.woolworths.helpers.metis.PartnerLocationHelper;
import au.com.woolworths.model.metis.partnerlocation.Locations;
import au.com.woolworths.model.metis.partnerlocation.PartnerLocationDetailsResponse;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import junit.framework.Assert;

import java.util.List;
import java.util.logging.Logger;

public class PartnerLocationDefinition extends PartnerLocationHelper {
  private final static Logger logger = Logger.getLogger("PartnerDetailsDefinition.class");
  private PartnerLocationDetailsResponse partnerLocationDetailsResponse;


  @When("^the user selects \"([^\"]*)\" details with \"([^\"]*)\" and \"([^\"]*)\"$")
  public void theUserMakesARequestForThePartnerLocationDetails(String partner, Float latitude, Float longitude) throws Throwable {
    logger.info("the partner details for requested partner");
    partnerLocationDetailsResponse = getPartnerLocationDetails(partner, latitude, longitude);
    logger.info("User was able to see the partner details successfully");
  }

  @Then("^the user should be able to see the partner location \"([^\"]*)\"$")
  public void partnerLocationDetailsAreDisplayed(String partner) throws Throwable {
    Assert.assertEquals("Status code for partner details NOT successful", "200", partnerLocationDetailsResponse.getStatusCode());
    Assert.assertTrue("Partner name ", partnerLocationDetailsResponse.getType().equals("RewardsPartnerLocations"));
    List<Locations> items = partnerLocationDetailsResponse.getData().getLocations();
    logger.info("No of locations for each partner " + items.size() + " " + partner);

    for (int i = 0; i < items.size(); i++) {
      Assert.assertEquals("Partner name ", items.get(i).getData().getPartnerId(), partner);
      Assert.assertEquals("Partner name ", items.get(i).getType(), "RewardsPartnerLocationItem");
      Assert.assertNotNull("Partner name ", items.get(i).getData().getLatitude());
      Assert.assertNotNull(items.get(i).getData().getLongitude());
      Assert.assertNotNull(items.get(i).getData().getName(), "Store name is not null ");
      Assert.assertNotNull("Partner name ", items.get(i).getData().getStoreNo());
      Assert.assertNotNull("Partner name ", items.get(i).getData().getAddress());
      Assert.assertNotNull("Partner name ", items.get(i).getData().getDistance());

      if (partner == "WOOLWORTHS") {
        Assert.assertTrue("Partner name ", items.get(i).getData().getDivision().equals("SUPERMARKETS"));
        Assert.assertTrue("Partner name ", items.get(i).getData().getMapClusterColour().equals("#00AB4E"));
        Assert.assertTrue("Partner name ", items.get(i).getData().getIcon().equals("woolworths"));
        Assert.assertTrue("Partner name ", items.get(i).getData().getDivision().contains("supermarkets_division_logo.png"));
      } else if (partner == "BWS") {
        Assert.assertTrue("Partner name ", items.get(i).getData().getDivision().equals("BWS"));
        Assert.assertTrue("Partner name ", items.get(i).getData().getMapClusterColour().equals("#F3632A"));
        Assert.assertTrue("Partner name ", items.get(i).getData().getIcon().equals("bws"));
        Assert.assertTrue("Partner name ", items.get(i).getData().getDivision().contains("bws_division_logo.png"));
      } else if (partner == "CALTEX") {
        Assert.assertTrue("Partner name ", items.get(i).getData().getDivision().equals("CALTEXWOW"));
        Assert.assertTrue("Partner name ", items.get(i).getData().getMapClusterColour().equals("#004C63"));
        Assert.assertTrue("Partner name ", items.get(i).getData().getIcon().equals("caltex"));
        Assert.assertTrue("Partner name ", items.get(i).getData().getDivision().contains("caltexwow.png"));
      } else if (partner == "BIGW") {
        Assert.assertTrue("Partner name ", items.get(i).getData().getDivision().equals("BIGW"));
        Assert.assertTrue("Partner name ", items.get(i).getData().getMapClusterColour().equals("#274AD3"));
        Assert.assertTrue("Partner name ", items.get(i).getData().getIcon().equals("bigw"));
        Assert.assertTrue("Partner name ", items.get(i).getData().getDivision().contains("bigw_division_logo.png"));
      }
    }

  }
}
