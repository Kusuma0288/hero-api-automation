package au.com.woolworths.stepdefinitions.metis;

import au.com.woolworths.graphql.parser.GraphqlParser;
import au.com.woolworths.helpers.metis.RewardsCardWithWalletHelper;
import au.com.woolworths.model.metis.card.payment_instruments.FetchPaymentInstrumentsResponse;
import au.com.woolworths.model.metis.digipay_payment.PaymentSessionResponse;
import au.com.woolworths.model.metis.scan_qr_code.QRIDResponse;
import au.com.woolworths.model.metis.transactions.TransactionsResponse;
import au.com.woolworths.utils.TestProperties;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import junit.framework.Assert;

import java.io.IOException;
import java.io.InputStream;

public class InStorePaymentDefinition extends RewardsCardWithWalletHelper {

  String qrID;
  String instrument;
  String paymentSessionID;
  String paymentRequestId;

  @Given("^the user has finished scanning items at the self-checkout service$")
  public void userHasScannedItems() throws IOException {
    PaymentSessionResponse paymentSessionResponse = createPaymentSession();

    qrID = paymentSessionResponse.getData().getQr().getQrId();
    // Get the instrument for later on in the flow
    instrument = getInstrument();
  }

  @When("^the user scans the QR code$")
  public void userGoesToScan() throws IOException {
    QRIDResponse qridResponse = scanQRCode(qrID);

    Assert.assertNotNull("Location value is missing", qridResponse.getData().getLocation());
    Assert.assertNotNull("Expiry time value is missing", qridResponse.getData().getExpiryTime());
    Assert.assertNotNull("Merchant ID value is missing", qridResponse.getData().getMerchantId());

    Assert.assertEquals("Rewards ID number is not as expected", sharedData.rewardsCard, qridResponse.getData().getCustomerInfo().getPayload().getRewardsId());
    Assert.assertEquals("Customer info schema ID is not as expected", 36, qridResponse.getData().getCustomerInfo().getSchemaId().length());

    Assert.assertNotNull("Merchant info lane ID is missing", qridResponse.getData().getMerchantInfo().getPayload().getLaneId());
    Assert.assertNotNull("Merchant info store ID is missing", qridResponse.getData().getMerchantInfo().getPayload().getStoreId());
    Assert.assertEquals("Merchant info schema ID is not as expected", 36, qridResponse.getData().getMerchantInfo().getSchemaId().length());

    Assert.assertEquals("Payment session ID is not as expected", 36, qridResponse.getData().getPaymentSessionId().length());

    paymentSessionID = qridResponse.getData().getPaymentSessionId();
  }

  @Then("^the user should be able to pay for the items using the stored card$")
  public void userCanScanToPay() throws IOException {
    putPreAuthPaymentSession(paymentSessionID, instrument);
    paymentRequestId = postPaymentRequest().getData().getPaymentRequestId();
    addPaymentRequestToSession(paymentRequestId, paymentSessionID);
    getTransactionDetails();
  }

  private String getInstrument() throws IOException {
    InputStream iStream = WalletDefinition.class.getResourceAsStream("/gqlQueries/metis/queries/wallet/fetchPaymentInstruments.graphql");
    String graphqlQuery = GraphqlParser.parseGraphql(iStream, null);

    FetchPaymentInstrumentsResponse fetchPaymentInstrumentsResponse = iRetrievePaymentInstruments(graphqlQuery);

    return fetchPaymentInstrumentsResponse.getData().getPaymentInstruments()[0].getId();
  }

  private void getTransactionDetails() throws IOException {
    TransactionsResponse transactionsResponse = getTransactionDetails(paymentRequestId);

    Assert.assertEquals("Rewards ID number is not as expected", "APPROVED", transactionsResponse.getData().getTransactions()[0].getStatus());
    Assert.assertEquals("Rewards ID number is not as expected", "ScanGoSuper", transactionsResponse.getData().getTransactions()[0].getMerchantId());
    Assert.assertEquals("Rewards ID number is not as expected", Integer.parseInt(TestProperties.get("DIGIPAY_TRANSACTION_AMOUNT")), transactionsResponse.getData().getTransactions()[0].getGrossAmount());

    Assert.assertEquals("Rewards ID number is not as expected", Integer.parseInt(TestProperties.get("DIGIPAY_TRANSACTION_AMOUNT")), transactionsResponse.getData().getTransactions()[0].getInstruments()[0].getAmount());
    Assert.assertEquals("Rewards ID number is not as expected", instrument, transactionsResponse.getData().getTransactions()[0].getInstruments()[0].getPaymentInstrumentId());
    Assert.assertEquals("Rewards ID number is not as expected", 16, transactionsResponse.getData().getTransactions()[0].getInstruments()[0].getPaymentTransactionRef().length());

    Assert.assertEquals("Rewards ID number is not as expected", "00", transactionsResponse.getData().getTransactions()[0].getStatusDetail().getCreditCards()[0].getExternalServiceCode());
    Assert.assertEquals("Rewards ID number is not as expected", instrument, transactionsResponse.getData().getTransactions()[0].getStatusDetail().getCreditCards()[0].getPaymentInstrumentId());
    Assert.assertEquals("Rewards ID number is not as expected", "APPROVED", transactionsResponse.getData().getTransactions()[0].getStatusDetail().getCreditCards()[0].getExternalServiceMessage());
    Assert.assertNull("Rewards ID number is not as expected", transactionsResponse.getData().getTransactions()[0].getStatusDetail().getFraudResponse().getReasonCode());

    Assert.assertNotNull("Rewards ID number is not as expected", transactionsResponse.getData().getTransactions()[0].getExecutionTime());
    Assert.assertNotNull("Rewards ID number is not as expected", transactionsResponse.getData().getTransactions()[0].getTransactionId());
    Assert.assertNotNull("Rewards ID number is not as expected", transactionsResponse.getData().getTransactions()[0].getClientReference());
    Assert.assertNotNull("Rewards ID number is not as expected", transactionsResponse.getData().getTransactions()[0].getPaymentRequestId());
    Assert.assertNotNull("Rewards ID number is not as expected", transactionsResponse.getData().getTransactions()[0].getMerchantReferenceId());

    Assert.assertEquals("Rewards ID number is not as expected", 1, transactionsResponse.getMeta().getRecordCount());
    Assert.assertEquals("Rewards ID number is not as expected", 1, transactionsResponse.getMeta().getTotalRecords());
  }
}