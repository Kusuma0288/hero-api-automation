package au.com.woolworths.apigee.stepdefinitions;

import au.com.woolworths.apigee.context.ApigeeApplicationContext;
import au.com.woolworths.apigee.helpers.ApigeeInStoreHelper;
import au.com.woolworths.apigee.model.ApigeeSearchInStore;
import cucumber.api.java.en.When;
import junit.framework.Assert;

import java.util.logging.Logger;

public class InStoreDefinition extends ApigeeInStoreHelper {

  private final static Logger logger = Logger.getLogger("InStoreDefinition.class");

  private ApigeeSharedData sharedData;
  private ApigeeContainer picoContainer;

  public InStoreDefinition(ApigeeContainer container) {
    this.sharedData = ApigeeApplicationContext.getSharedData();
    this.picoContainer = container;
  }

  @When("^I select an IN-STORE shop in postcode \"([^\"]*)\"$")
  public void searchForInStore(String postcode) throws Throwable {
    int position = 1;
    ApigeeSearchInStore searchInStoresResponse = iSearchForInStore(postcode, sharedData.accessToken);

    //These assertions are to make sure there are no NULL FIELDS
    Assert.assertNotNull(searchInStoresResponse.getStores()[0].getNo());
    Assert.assertNotNull(searchInStoresResponse.getStores()[0].getName());
    Assert.assertNotNull(searchInStoresResponse.getStores()[0].getDivision());

    sharedData.searchInStoreResponse = searchInStoresResponse;
    sharedData.inStoreId = searchInStoresResponse.getStores()[position - 1].getNo();
  }

}
