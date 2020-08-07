package au.com.woolworths.stepdefinitions.apigee;

import au.com.woolworths.helpers.apigee.SpecialsHelper;
import au.com.woolworths.model.apigee.products.SpecialspageResponse;
import cucumber.api.java.en.Then;
import junit.framework.Assert;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class Specialspage extends SpecialsHelper {

  private final static Logger logger = Logger.getLogger("Homepage.class");

  @Then("^I make a request to Specials page in \"([^\"]*)\" mode and verify the response$")
  public void verifySpecialspage(String shoppingMode) throws Throwable {
    Map<String, String> queryParams = new HashMap<String, String>();
    queryParams.put("mode", shoppingMode);
    SpecialspageResponse speicalspageResponse = iRetrieveSpecialspageWithOnlineMode(queryParams);

    sharedData.specialspageResponse = speicalspageResponse;
    //Assert Response is not Null
    Assert.assertNotNull("wow/v2/specials ONLINE response has Categories set as NULL", speicalspageResponse.getCategories());

  }

  @Then("^I make a request to Specials page in IN-STORE mode and with store id \"([^\"]*)\" verify the response$")
  public void iMakeARequestToSpecialsPageInINSTOREModeAndWithStoreIdVerifyTheResponse(String store) throws Throwable {
    Map<String, String> queryParams = new HashMap<String, String>();
    queryParams.put("store", store);
    SpecialspageResponse speicalspageResponse = iRetrieveSpecialspageWithOnlineMode(queryParams);

    sharedData.specialspageResponse = speicalspageResponse;
    //Assert Response is not Null
    Assert.assertNotNull("wow/v2/specials IN-STORE response has Categories set as NULL", speicalspageResponse.getCategories());
  }
}
