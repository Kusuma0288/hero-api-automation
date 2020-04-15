package au.com.woolworths.apigee.stepdefinitions;


import au.com.woolworths.apigee.context.ApigeeApplicationContext;
import au.com.woolworths.apigee.helpers.SpecialspageHelper;
import au.com.woolworths.apigee.model.*;
import cucumber.api.java.en.Then;
import junit.framework.Assert;

import java.util.logging.Logger;

public class Specialspage extends SpecialspageHelper {


    private final static Logger logger = Logger.getLogger("Homepage.class");
    private ApigeeSharedData sharedData;
    private ApigeeContainer picoContainer;

    public Specialspage(ApigeeContainer container) {
        this.sharedData = ApigeeApplicationContext.getSharedData();
        this.picoContainer = container;
    }


    @Then("^I make a request to Speicals page in \"([^\"]*)\" mode and verify the response$")
    public void verifySpecialspage(String shoppingMode) throws Throwable {
        SpecialspageResponse speicalspageResponse = iRetrieveSpecialspageWithOnlineMode(shoppingMode, sharedData.accessToken);

        //Assert Response is not Null
        Assert.assertNotNull("wow/v2/specials ONLINE response has Categories set as NULL",speicalspageResponse.getCategories());

    }

    @Then("^I make a request to Speicals page in IN-STORE mode and with store id \"([^\"]*)\" verify the response$")
    public void iMakeARequestToSpeicalsPageInINSTOREModeAndWithStoreIdVerifyTheResponse(String store) throws Throwable {
        SpecialspageResponse speicalspageResponse = iRetrieveSpecialspageWithInStoreMode(store, sharedData.accessToken);

        //Assert Response is not Null
        Assert.assertNotNull("wow/v2/specials IN-STORE response has Categories set as NULL",speicalspageResponse.getCategories());
    }
}
