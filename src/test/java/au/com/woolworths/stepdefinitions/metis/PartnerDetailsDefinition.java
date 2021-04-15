package au.com.woolworths.stepdefinitions.metis;

import au.com.woolworths.helpers.metis.PartnerHelper;
import au.com.woolworths.model.metis.partnerdetails.Facility;
import au.com.woolworths.model.metis.partnerdetails.HeaderData;
import au.com.woolworths.model.metis.partnerdetails.Item;
import au.com.woolworths.model.metis.partnerdetails.PartnerDetailsResponse;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.List;
import java.util.logging.Logger;

import static junit.framework.Assert.*;

public class PartnerDetailsDefinition extends PartnerHelper {
  private final static Logger logger = Logger.getLogger("PartnerDetailsDefinition.class");
  private PartnerDetailsResponse partnerDetailsResponse;


  @When("^the user selects \"([^\"]*)\" details with \"([^\"]*)\"$")
  public void theUserMakesARequestForThePartnerDetails(String partner, String store) throws Throwable {
    logger.info("the partner details for requested partner");
    partnerDetailsResponse = getPartnerDetails(partner, store);
    logger.info("User was able to see the partner details successfully");
  }

  @Then("^the user should be able to see the partner details \"([^\"]*)\"$")
  public void partnerDetailsAreDisplayed(String partner) {
    assertEquals("Status code for partner details NOT successful", "200", partnerDetailsResponse.getStatusCode());
    HeaderData data = partnerDetailsResponse.getData().getHeader().getData();
    assertNotNull("Store title should be non empty", data.getTitle());
    assertNotNull("Store details should be non empty", data.getContent());
    List<Item> items = partnerDetailsResponse.getData().getItems();
    assertTrue("Title 0 does not contain Trading Hours", items.get(0).getData().getTitle().contains("Trading Hours"));
    if (partner.equals("SUPERMARKETS")) {
      assertNotNull("Url is missing", data.getIcon().contains("woolworths"));
      assertTrue("Logo is missing from partner details iconUrl", data.getIconUrl().contains("supermarkets_division_logo.png"));
      assertTrue("Partners url is missing", data.getButton().getData().getUrl().contains("woolworths"));
      assertTrue("Call to Action button is missing", data.getButton().getData().getTitle().contentEquals("Shop Online"));
    } else if (partner.equals("BWS")) {
      assertNotNull("Url is missing", data.getIcon().contains("bws"));
      assertTrue("Logo is missing from partner details iconUrl", data.getIconUrl().contains("bws_division_logo.png"));
      assertTrue("Partners url is missing", data.getButton().getData().getUrl().contains("bws"));
      assertTrue("Call to Action button is missing", data.getButton().getData().getTitle().contentEquals("Shop Online"));
    } else if (partner.equals("CALTEXWOW")) {
      assertNotNull("Url is missing", data.getIcon().contains("caltex"));
      assertTrue("Logo is missing from partner details iconUrl", data.getIconUrl().contains("caltexwow_division_logo.png"));
      assertTrue("Partners url is missing", data.getButton().getData().getUrl().contains("caltex"));
      assertTrue("Call to Action button is missing", data.getButton().getData().getTitle().contentEquals("Find out more"));
    } else if (partner.equals("BIGW")) {
      assertNotNull("Url is missing", data.getIcon().contains("bigw"));
      assertTrue("Logo is missing from partner details iconUrl", data.getIconUrl().contains("bigw_division_logo.png"));
      assertTrue("Partners url is missing", data.getButton().getData().getUrl().contains("bigw"));
      assertTrue("Call to Action button is missing", data.getButton().getData().getTitle().contentEquals("Shop Online"));
    }
    for (int i = 0; i < items.get(0).getData().getItems().size(); i++) {
      assertNotNull("Partner trading day should not non empty", items.get(0).getData().getItems().get(i).getData().getDay());
      assertNotNull("Partner trading hours should not non empty", items.get(0).getData().getItems().get(i).getData().getOpenHours());
    }
    assertTrue("Title 1 does not contain Phone", items.get(1).getData().getTitle().contains("Phone"));
    assertNotNull("Title 1 does not contain Phone number", items.get(1).getData().getPhoneNumber());
    assertTrue("Title 2 does not contain Phone", items.get(2).getData().getTitle().contains("Phone"));
    assertNotNull("Title 2 does not contain display address", items.get(2).getData().getDisplayAddress());
    assertTrue("Title 3 does not contain Facilities", items.get(3).getData().getTitle().contains("Facilities"));
    List<Facility> facilities = items.get(3).getData().getFacilities();
    for (int i = 0; i < facilities.size(); i++) {
      assertNotNull("Partner facility should not non empty", facilities.get(i).getData().getFacility());
    }
  }
}
