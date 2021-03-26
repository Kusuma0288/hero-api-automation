package au.com.woolworths.stepdefinitions.scango;

import au.com.woolworths.helpers.scango.PaymentHelper;
import au.com.woolworths.model.scango.payment.ListInstrumentsResponse;
import au.com.woolworths.model.scango.payment.PaymentResponse;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class PaymentDefinition extends PaymentHelper {

  @When("^I call List Instruments API")
  public void iCallListInstrumentsAPI() throws Throwable {
    ListInstrumentsResponse listInstrumentsResponse = iCallListInstruments();
    sharedData.listInstrumentsResponse = listInstrumentsResponse;

    sharedData.responseStatusCode = listInstrumentsResponse.getStatusCode();
    Assert.assertTrue(sharedData.responseStatusCode.contains("200"), "Connection not successful::" + sharedData.responseStatusCode);
  }

  @Then("^I verify payment is successfully completed through In-App Payment API$")
  public void verifyPaymentIsSuccessful() throws Throwable {
    String instrumentsID = sharedData.listInstrumentsResponse.getCreditCards().get(0).getPaymentInstrumentId();
    Double balanceDue = sharedData.checkoutResponse.getBalancedue();
    PaymentResponse paymentResponse = iCallListPaymentAPI(instrumentsID, balanceDue);

    sharedData.responseStatusCode = paymentResponse.getStatusCode();
    Assert.assertTrue(sharedData.responseStatusCode.contains("200"), "Connection not successful::" + sharedData.responseStatusCode);
  }

}
