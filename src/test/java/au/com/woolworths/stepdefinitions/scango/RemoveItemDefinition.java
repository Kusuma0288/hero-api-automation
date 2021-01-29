package au.com.woolworths.stepdefinitions.scango;

import au.com.woolworths.helpers.scango.RemoveItemHelper;
import au.com.woolworths.model.scango.scanitems.RemoveItemResponse;

import cucumber.api.java.en.When;
import org.testng.Assert;

import java.io.IOException;

public class RemoveItemDefinition extends RemoveItemHelper {

    @When("^I call Remove Item API$")
    public void i_Call_Remove_Item_API() throws IOException {
        RemoveItemResponse removeItemResponse = iClickOnRemoveItemAPI();

        Assert.assertTrue(sharedData.responseStatusCode.contains("200"), "Connection not successful::" + sharedData.responseStatusCode);

        System.out.println("RemoveItemResponse  file " +removeItemResponse.toString());
    }

}
