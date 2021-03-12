package au.com.woolworths.stepdefinitions.scango;

import au.com.woolworths.helpers.scango.DeleteCartHelper;
import au.com.woolworths.model.scango.scanitems.DeleteCartResponse;
import cucumber.api.java.en.And;
import org.testng.Assert;

import java.io.IOException;

public class DeleteCartDefinition extends DeleteCartHelper {

  @And("^I call Delete Transaction API$")
  public void iCallDeleteCartApi() throws IOException {

    DeleteCartResponse deleteCartResponse = iClickOnDeleteCartAPI();


    Assert.assertTrue(sharedData.responseStatusCode.contains("200"), "Connection not successful::" + sharedData.responseStatusCode);

    System.out.println("DeleteCartResponse  file " + deleteCartResponse.toString());
  }
}
