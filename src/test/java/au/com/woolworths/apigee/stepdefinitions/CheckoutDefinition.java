package au.com.woolworths.apigee.stepdefinitions;

import au.com.woolworths.apigee.context.ApigeeApplicationContext;
import au.com.woolworths.apigee.helpers.CheckoutHelper;
import au.com.woolworths.apigee.model.*;
import au.com.woolworths.apigee.model.CheckoutPackagingPreferencesResponse;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.And;
import junit.framework.Assert;

import java.util.Arrays;
import java.util.logging.Logger;

public class CheckoutDefinition extends CheckoutHelper{
    private final static Logger logger = Logger.getLogger("DeliveryAddressDefinition.class");

    private ApigeeSharedData sharedData;
    private ApigeeContainer picoContainer;

    public CheckoutDefinition(ApigeeContainer container) {
        this.sharedData = ApigeeApplicationContext.getSharedData();
        this.picoContainer = container;
    }

    @And("^I get the available pickup windows for the logged in user with storeId or addressId$")
    public void iGetAvailablePickupWindow() throws Throwable{
        CheckoutResponse checkoutResponse = getCheckoutResponse(sharedData.accessToken);
        CheckoutFulfilmentWindows[] checkoutFulfilmentWindows = checkoutResponse.getFulfilmentWindows();
        checkoutFulfilmentWindows[0] = Arrays.asList(checkoutResponse.getFulfilmentWindows()).stream().filter(i -> i.getIsAvailable().equals(true))
                .findFirst().get();
        //Get Afternoon or Evening Slot
        CheckoutWindowItems checkoutWindowItems = checkoutFulfilmentWindows[0].getAfternoon();
        CheckoutWindowSlots[] checkoutWindowSlots = checkoutWindowItems.getSlots();
        try {
            if (Arrays.asList(checkoutFulfilmentWindows[0].getAfternoon().getSlots()).stream().filter(j -> j.getIsAvailable()).count() > 0) {
                checkoutWindowSlots[0] = Arrays.asList(checkoutFulfilmentWindows[0].getAfternoon().getSlots()).stream().filter(j -> j.getIsAvailable()).findFirst().get();
                if (checkoutWindowSlots[0] != null) {
                    picoContainer.windowId = checkoutWindowSlots[0].getId();
                    picoContainer.windowStartTime = checkoutWindowSlots[0].getStartTime();
                }
            } else {
                checkoutWindowItems = checkoutFulfilmentWindows[0].getEvening();
                checkoutWindowSlots = checkoutWindowItems.getSlots();
                if (checkoutWindowSlots[0] != null) {
                    picoContainer.windowId = checkoutWindowSlots[0].getId();
                    picoContainer.windowStartTime = checkoutWindowSlots[0].getStartTime();
                }
            }
        }
        catch(Exception ex){
            logger.info("No available checkout window available.");
        }


    }
    @Then("^I set the selected available pickup window for the logged in user$")
    public void iSetAvailablePickupWindow() throws Throwable{
        CheckoutResponse checkoutResponse = postSetCheckoutWindow(picoContainer.windowId, picoContainer.windowStartTime, sharedData.accessToken);
        picoContainer.packagingPreference = checkoutResponse.getDeliveryPackagingPreferences();
        Assert.assertEquals("Selected window is not set",checkoutResponse.getResults().getSetDeliveryWindow().getHttpStatusCode(), 200);
    }

    @And("^I validate the default selected packaging preference for Delivery is (.*)$")
    public void iValidateDefaultPackagingPreference(String packagingPref) throws Throwable{
        CheckoutPackagingPreferencesResponse[] checkoutPackagingPreferences = picoContainer.packagingPreference;
        Arrays.stream(checkoutPackagingPreferences).filter(i -> i.getIsSelected()).findFirst().get().getName();
        Assert.assertTrue(Arrays.stream(checkoutPackagingPreferences).filter(i -> i.getIsSelected()).findFirst().get().getName().equalsIgnoreCase(packagingPref));
    }

    @Then("^I validate that user is able to select (.*) as packaging preference$")
    public void iSelectPackagingPreference(String packagingPref) throws Throwable{
        int packagingID;
        if(packagingPref.contains("Reusable")) {
            packagingID = Arrays.stream(picoContainer.packagingPreference).filter(i->i.getName().contains("Reusable")).findFirst().get().getId();
            CheckoutResponse checkoutResponse = postSetPackagingPreference(packagingID, sharedData.accessToken);
            Assert.assertEquals("Packaging Preference is not set",checkoutResponse.getResults().getSetPackagingOption().getHttpStatusCode(), 200);
            Assert.assertTrue("Packaging Preference not set correctly", Arrays.stream(checkoutResponse.getDeliveryPackagingPreferences()).filter(i->  i.getName().contains(packagingPref)).findFirst().get().getIsSelected());

        }
        else if(packagingPref.contains("BYO")){
            packagingID = Arrays.stream(picoContainer.packagingPreference).filter(i->i.getName().contains("BYO")).findFirst().get().getId();
            CheckoutResponse checkoutResponse = postSetPackagingPreference(packagingID, sharedData.accessToken);
            Assert.assertEquals("Packaging Preference is not set",checkoutResponse.getResults().getSetPackagingOption().getHttpStatusCode(), 200);
            Assert.assertTrue("Packaging Preference not set correctly", Arrays.stream(checkoutResponse.getDeliveryPackagingPreferences()).filter(i->  i.getName().contains(packagingPref)).findFirst().get().getIsSelected());

        }
    }
}

