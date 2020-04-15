package au.com.woolworths.apigee.stepdefinitions;

import au.com.woolworths.apigee.context.ApigeeApplicationContext;
import au.com.woolworths.apigee.helpers.CheckoutHelper;
import au.com.woolworths.apigee.model.CheckoutResponse;
import au.com.woolworths.apigee.model.CheckoutFulfilmentWindows;
import au.com.woolworths.apigee.model.CheckoutWindowSlots;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.api.java.en.And;

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

    @When("^I make a GET request to checkout api")
    public void iMakeGetCheckout() throws Throwable {
        CheckoutResponse checkoutResponse = getCheckoutResponse(sharedData.accessToken);

        CheckoutFulfilmentWindows[] checkoutFulfilmentWindows = checkoutResponse.getFulfilmentWindows();
        CheckoutFulfilmentWindows checkoutWindow;
        CheckoutWindowSlots checkoutWindowSlots;
        Arrays.asList(checkoutFulfilmentWindows).stream().filter(i-> i.getIsAvailable().equals(true)).findFirst().get();

        checkoutFulfilmentWindows[0] = Arrays.asList(checkoutResponse.getFulfilmentWindows()).stream().filter(i -> i.getIsAvailable().equals(true))
                .findFirst().get();
        checkoutWindowSlots = checkoutFulfilmentWindows[0].getAfternoon().getCheckoutWindowSlots();
        checkoutWindowSlots = checkoutFulfilmentWindows[0].getEvening().getCheckoutWindowSlots();
        checkoutWindowSlots = checkoutFulfilmentWindows[0].getMorning().getCheckoutWindowSlots();
    }
    @And("I get the available pickup windows for the logged in user with storeId or addressId$")
    public void iGetAvailablePickupWindow() throws Throwable{


    }
    @Then("I set the selected available pickup window for the logged in user$")
    public void iSetAvailablePickupWindow() throws Throwable{

    }
}

