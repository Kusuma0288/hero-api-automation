package au.com.woolworths.stepdefinitions.scango;

import au.com.woolworths.helpers.scango.PaymentHelper;
import au.com.woolworths.model.scango.kiosk.KioskPaymentResponse;
import au.com.woolworths.model.scango.payment.ListInstrumentsResponse;
import au.com.woolworths.model.scango.payment.PaymentResponse;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.testng.Assert;

public class KioskPaymentDefinition extends PaymentHelper {

    @Then("^I verify payment is successfully completed through Kiosk Payment API$")
    public void i_verify_payment_is_successfully_completed_through_kiosk_Payment_API() throws Throwable {
        Double balanceDue = sharedData.kioskCheckoutResponse.getBalancedue();
        KioskPaymentResponse kioskPaymentResponse = iCallKioskPaymentAPI(balanceDue);

        sharedData.responseStatusCode = kioskPaymentResponse.getStatusCode();
        Assert.assertTrue(sharedData.responseStatusCode.contains("200"), "Connection not successful::" + sharedData.responseStatusCode);

        System.out.println("PaymentResponse  file " + kioskPaymentResponse.toString());
    }

}
