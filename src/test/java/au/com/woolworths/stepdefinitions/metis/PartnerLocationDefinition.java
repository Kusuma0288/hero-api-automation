package au.com.woolworths.stepdefinitions.metis;


import au.com.woolworths.helpers.metis.PartnerLocationHelper;
import au.com.woolworths.model.metis.partnerlocation.Locations;
import au.com.woolworths.model.metis.partnerlocation.PartnerLocationDetailsResponse;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import junit.framework.Assert;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.logging.Logger;

public class PartnerLocationDefinition extends PartnerLocationHelper {
  private final static Logger logger = Logger.getLogger("PartnerDetailsDefinition.class");
  private PartnerLocationDetailsResponse partnerLocationDetailsResponse;


  @When("^the user selects \"([^\"]*)\" details with \"([^\"]*)\" and \"([^\"]*)\"$")
  public void theUserMakesARequestForThePartnerLocationDetails(String partner, Float latitude, Float longitude) throws Throwable {
    logger.info("the partner location for requested partner");
    partnerLocationDetailsResponse = getPartnerLocationDetails(partner, latitude, longitude);
    logger.info("User was able to see the partner locations based on latitude and longitude successfully");
  }

  @Then("^the user should be able to see the partner location \"([^\"]*)\"$")
  public void partnerLocationDetailsAreDisplayed(String partner) throws Throwable {
    Assert.assertEquals("Status code for partner location NOT successful", "200", partnerLocationDetailsResponse.getStatusCode());
    Assert.assertEquals("Partner Locations type is missing ", "RewardsPartnerLocations", partnerLocationDetailsResponse.getType());
    List<Locations> items = partnerLocationDetailsResponse.getData().getLocations();
    logger.info("No of locations for each partner " + items.size() + " " + partner);
    for (int i = 0; i < items.size(); i++) {
      Assert.assertEquals("PartnerId is missing ", items.get(i).getData().getPartnerId(), partner);
      Assert.assertEquals("RewardsPartnerLocation type is missing ", "RewardsPartnerLocationItem", items.get(i).getType());
      Assert.assertNotNull("Latitude should not be empty", items.get(i).getData().getLatitude());
      Assert.assertNotNull("Longitude should not be empty", items.get(i).getData().getLongitude());
      Assert.assertNotNull("Store name should not be not null ", items.get(i).getData().getName());
      Assert.assertNotNull("Store Number should not be null ", items.get(i).getData().getStoreNo());
      Assert.assertNotNull("Store Address should not be null ", items.get(i).getData().getAddress());
      Assert.assertNotNull("Distance to store should not be null ", items.get(i).getData().getDistance());

      if (partner.equals("WOOLWORTHS")) {
        Assert.assertEquals("Woolworths division name is missing ", "SUPERMARKETS", items.get(i).getData().getDivision());
        Assert.assertEquals("Woolworths cluster colour is missing ", "#00AB4E", items.get(i).getData().getMapClusterColour());
        Assert.assertEquals("Woolworths icon is missing ", "woolworths", items.get(i).getData().getIcon());
        Assert.assertTrue("Woolworths icon url is missing the division logo ", items.get(i).getData().getIconUrl().contains("supermarkets_division_logo.png"));
      } else if (partner.equals("BWS")) {
        Assert.assertEquals("BWS division name is missing ", "BWS", items.get(i).getData().getDivision());
        Assert.assertEquals("BWS cluster colour is missing ", "#F3632A", items.get(i).getData().getMapClusterColour());
        Assert.assertEquals("BWS icon is missing ", "bws", items.get(i).getData().getIcon());
        Assert.assertTrue("BWS icon url is missing the division logo ", items.get(i).getData().getIconUrl().contains("bws_division_logo.png"));
      } else if (partner.equals("CALTEX")) {
        Assert.assertTrue("Caltex division name is missing ", StringUtils.equalsAny(items.get(i).getData().getDivision(), "CALTEXWOW", "PETROL"));
        Assert.assertEquals("Caltex cluster colour is missing  ", "#004C63", items.get(i).getData().getMapClusterColour());
        Assert.assertTrue("Caltex icon is missing ", StringUtils.equalsAny(items.get(i).getData().getIcon(), "caltex", "caltex_woolworths"));
        if (items.get(i).getData().getBadgeUrl() != null) {
          Assert.assertEquals("Caltex badge url is missing the 4c  ", "4c.png", items.get(i).getData().getBadgeUrl());
        }
        Assert.assertTrue("Caltex icon url is missing the division logo ", StringUtils.containsAny(items.get(i).getData().getIconUrl(), "caltexwow_division_logo.png", "petrol_division_logo.png"));
      } else if (partner.equals("BIGW")) {
        Assert.assertEquals("BIGW division name is missing ", "BIGW", items.get(i).getData().getDivision());
        Assert.assertEquals("BIGW cluster colour is missing ", "#274AD3", items.get(i).getData().getMapClusterColour());
        Assert.assertEquals("BIGW icon is missing ", "bigw", items.get(i).getData().getIcon());
        Assert.assertTrue("BIGW icon url is missing the division logo ", items.get(i).getData().getIconUrl().contains("bigw_division_logo.png"));
      }
    }

  }
}
