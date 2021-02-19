package au.com.woolworths.stepdefinitions.scango;

import au.com.woolworths.helpers.scango.PaymentHelper;
import au.com.woolworths.model.scango.checkout.CheckoutResponse;
import au.com.woolworths.model.scango.payment.ListInstrumentsResponse;
import au.com.woolworths.model.scango.payment.PaymentResponse;
import au.com.woolworths.utils.TestProperties;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.testng.Assert;

public class PaymentDefinition extends PaymentHelper {

    @When("^I call List Instruments API")
    public void i_call_List_Instruments_API() throws Throwable {
        ListInstrumentsResponse listInstrumentsResponse = iCallListInstruments();
        sharedData.listInstrumentsResponse = listInstrumentsResponse;

        sharedData.responseStatusCode = listInstrumentsResponse.getStatusCode();
        Assert.assertTrue(sharedData.responseStatusCode.contains("200"), "Connection not successful::" + sharedData.responseStatusCode);

        System.out.println("ListInstrumentsResponse  file " + listInstrumentsResponse.toString());
    }

    @When("^I call Payment API with invalid Instruments ID twice")
    public void i_call_payment_api_with_invalid_instruments_id_twice() throws Throwable {
        String instrumentsID = TestProperties.get("INVALID_INSTRUMENTS_ID");
        Double balanceDue = sharedData.checkoutResponse.getBalancedue();

        for(int i=0;i<2;i++) { iCallInvalidListPaymentAPI(instrumentsID, balanceDue);
        }

    }

    @Then("^I verify payment is successfully completed through In-App Payment API$")
    public void i_verify_payment_is_successfully_completed_through_in_app__Payment_API() throws Throwable {
        String instrumentsID = sharedData.listInstrumentsResponse.getCreditCards().get(0).getPaymentInstrumentId();
        Double balanceDue = sharedData.checkoutResponse.getBalancedue();

        PaymentResponse paymentResponse = iCallListPaymentAPI(instrumentsID, balanceDue);
        sharedData.responseStatusCode = paymentResponse.getStatusCode();

        Assert.assertTrue(sharedData.responseStatusCode.contains("200"), "Connection not successful::" + sharedData.responseStatusCode);
        System.out.println("PaymentResponse  file " + paymentResponse.toString());

    }

}
