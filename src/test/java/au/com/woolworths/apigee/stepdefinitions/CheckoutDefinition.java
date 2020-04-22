package au.com.woolworths.apigee.stepdefinitions;

import au.com.woolworths.apigee.context.ApigeeApplicationContext;
import au.com.woolworths.apigee.helpers.CheckoutHelper;
import au.com.woolworths.apigee.model.CheckoutResponse;
import au.com.woolworths.apigee.model.CheckoutFulfilmentWindows;
import au.com.woolworths.apigee.model.CheckoutWindowItems;
import au.com.woolworths.apigee.model.CheckoutWindowSlots;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
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
    @Then("I set the selected available pickup window for the logged in user$")
    public void iSetAvailablePickupWindow() throws Throwable{
        CheckoutResponse checkoutResponse = postSetCheckoutWindow(picoContainer.windowId, picoContainer.windowStartTime, sharedData.accessToken);
        Assert.assertEquals("Selected window is not set",checkoutResponse.getResults().getSetDeliveryWindow().getHttpStatusCode(), 200);
    }
}

