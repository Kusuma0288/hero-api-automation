package au.com.woolworths.stepdefinitions.metis;

import au.com.woolworths.helpers.metis.PartnerHelper;
import au.com.woolworths.model.metis.partnerdetails.Facility;
import au.com.woolworths.model.metis.partnerdetails.HeaderData;
import au.com.woolworths.model.metis.partnerdetails.Item;
import au.com.woolworths.model.metis.partnerdetails.PartnerDetailsResponse;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import junit.framework.Assert;

import java.util.List;
import java.util.logging.Logger;

public class PartnerDetailsDefinition extends PartnerHelper {
  private final static Logger logger = Logger.getLogger("PartnerDetailsDefinition.class");
  private PartnerDetailsResponse partnerDetailsResponse;


  @When("^the user selects \"([^\"]*)\" details with \"([^\"]*)\"$")
  public void theUserMakesARequestForThePartnerDetails(String partner, String store) throws Throwable {
    logger.info("the partner details for requested partner");
    partnerDetailsResponse = getPartnerDetails(partner, store);
    logger.info("User was able to see the partner details successfully");
  }

  @Then("^the user should be able to see the partner details$")
  public void partnerDetailsAreDisplayed() {
    Assert.assertEquals("Status code for partner details NOT successful", "200", partnerDetailsResponse.getStatusCode());
    HeaderData data = partnerDetailsResponse.getData().getHeader().getData();
    Assert.assertNotNull("Url is missing", data.getIcon().contains("woolworths"));
    Assert.assertTrue("Logo is missing from partner details iconUrl", data.getIconUrl().contains("supermarkets_division_logo.png"));
    Assert.assertNotNull("Store title should be non empty", data.getTitle());
    Assert.assertNotNull("Store details should be non empty", data.getContent());
    Assert.assertTrue("Call to Action button is missing", data.getButton().getData().getTitle().contentEquals("Shop Online"));
    Assert.assertTrue("Partners url is missing", data.getButton().getData().getUrl().contains("woolworths"));
    List <Item> items = partnerDetailsResponse.getData().getItems();
    Assert.assertTrue("Title 0 does not contain Trading Hours", items.get(0).getData().getTitle().contains("Trading Hours"));
    for (int i = 0; i < items.get(0).getData().getItems().size(); i++) {
      Assert.assertNotNull("Partner trading day should not non empty", items.get(0).getData().getItems().get(i).getData().getDay());
      Assert.assertNotNull("Partner trading hours should not non empty", items.get(0).getData().getItems().get(i).getData().getOpenHours());
    }
    Assert.assertTrue("Title 1 does not contain Phone", items.get(1).getData().getTitle().contains("Phone"));
    Assert.assertNotNull("Title 1 does not contain Phone number", items.get(1).getData().getPhoneNumber());
    Assert.assertTrue("Title 2 does not contain Phone", items.get(2).getData().getTitle().contains("Phone"));
    Assert.assertNotNull("Title 2 does not contain display address", items.get(2).getData().getDisplayAddress());
    Assert.assertTrue("Title 3 does not contain Facilities", items.get(3).getData().getTitle().contains("Facilities"));
    List <Facility> facilities = items.get(3).getData().getFacilities();
    for (int i = 0; i < facilities.size(); i++) {
      Assert.assertNotNull("Partner facility should not non empty", facilities.get(i).getData().getFacility());
    }
  }
}
