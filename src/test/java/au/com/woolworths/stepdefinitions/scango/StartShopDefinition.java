package au.com.woolworths.stepdefinitions.scango;

import au.com.woolworths.helpers.scango.StartShopHelper;


import au.com.woolworths.model.scango.startshop.StartShopResponse;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.testng.Assert;

public class StartShopDefinition extends StartShopHelper {

    @When("^user calls the Start Shop API$")
    public void user_calls_the_Start_Shop_API() throws Throwable {
        StartShopResponse startShopResponse = iClickOnStartShopAPI();

        sharedData.storeID = startShopResponse.getStoreid();
        sharedData.cartID  = startShopResponse.getCartid();

        System.out.println("StartShopDefinition  file " +startShopResponse.toString());
    }
}
