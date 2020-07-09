package au.com.woolworths.stepdefinitions.apigee;

import au.com.woolworths.helpers.apigee.ApigeeInStoreHelper;
import au.com.woolworths.model.apigee.ApigeeSearchInStore;
import cucumber.api.java.en.When;
import junit.framework.Assert;

import java.util.logging.Logger;

public class InStoreDefinition extends ApigeeInStoreHelper {

  private final static Logger logger = Logger.getLogger("InStoreDefinition.class");


  @When("^I select an IN-STORE shop in postcode \"([^\"]*)\"$")
  public void searchForInStore(String postcode) throws Throwable {
    int position = 1;
    ApigeeSearchInStore searchInStoresResponse = iSearchForInStore(postcode);

    //These assertions are to make sure there are no NULL FIELDS
    Assert.assertNotNull(searchInStoresResponse.getStores()[0].getNo());
    Assert.assertNotNull(searchInStoresResponse.getStores()[0].getName());
    Assert.assertNotNull(searchInStoresResponse.getStores()[0].getDivision());

    sharedData.searchInStoreResponse = searchInStoresResponse;
    sharedData.inStoreId = searchInStoresResponse.getStores()[position - 1].getNo();
  }

}
