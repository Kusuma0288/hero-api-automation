package au.com.woolworths.stepdefinitions.scango;

import au.com.woolworths.helpers.scango.PaymentHelper;
import au.com.woolworths.model.scango.payment.ListInstrumentsResponse;
import au.com.woolworths.model.scango.payment.PaymentResponse;
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
    }

    @Then("^I verify payment is successfully completed through In-App Payment API$")
    public void i_verify_payment_is_successfully_completed_through_in_app__Payment_API() throws Throwable {
        String instrumentsID = sharedData.listInstrumentsResponse.getCreditCards().get(0).getPaymentInstrumentId();
        Double balanceDue = sharedData.checkoutResponse.getBalancedue();
        PaymentResponse paymentResponse = iCallListPaymentAPI(instrumentsID, balanceDue);

        sharedData.responseStatusCode = paymentResponse.getStatusCode();
        Assert.assertTrue(sharedData.responseStatusCode.contains("200"), "Connection not successful::" + sharedData.responseStatusCode);
    }

}
