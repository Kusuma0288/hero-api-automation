package au.com.woolworths.helpers.metis;

import au.com.woolworths.graphql.parser.GraphqlParser;
import au.com.woolworths.helpers.apigee.IFrameCardHelper;
import au.com.woolworths.model.metis.card.add_gift_card.AddGiftCardResponse;
import au.com.woolworths.model.metis.card.add_scheme_card.FetchAddSchemeCardURLResponse;
import au.com.woolworths.model.metis.card.default_payment_settings_sc.SetPrimarySchemeCardResponse;
import au.com.woolworths.model.metis.card.delete_scheme_card.DeleteSchemeCardResponse;
import au.com.woolworths.model.metis.card.home_page_with_wallet.RewardsCardHomePageWithWalletResponse;
import au.com.woolworths.model.metis.card.payment_instruments.FetchPaymentInstrumentsResponse;
import au.com.woolworths.model.metis.card.update_scheme_card.FetchUpdateSchemeCardURLResponse;
import au.com.woolworths.model.metis.card.verify_scheme_card.FetchVerifySchemeCardResponse;
import au.com.woolworths.model.metis.card.view_gift_card.ViewGiftCardResponse;
import au.com.woolworths.model.metis.card.view_gc_payment_preferences.ViewGCPaymentPreferencesResponse;
import au.com.woolworths.model.metis.card.view_sc_payment_preferences.ViewSCPaymentPreferencesResponse;
import au.com.woolworths.model.metis.card.view_user_preference.FetchUserPreferencesResponse;
import au.com.woolworths.model.metis.digipay_payment.*;
import au.com.woolworths.model.metis.digipay_payment.pos.PosPayload;
import au.com.woolworths.model.metis.payment_session.SessionData;
import au.com.woolworths.model.metis.payment_session.SessionMeta;
import au.com.woolworths.model.metis.payment_session.SessionRequest;
import au.com.woolworths.model.metis.scan_qr_code.QRIDResponse;
import au.com.woolworths.model.metis.transactions.TransactionsResponse;
import au.com.woolworths.stepdefinitions.common.ServiceHooks;
import au.com.woolworths.stepdefinitions.metis.WalletDefinition;
import au.com.woolworths.utils.RestInvocationUtil;
import au.com.woolworths.utils.TestProperties;
import au.com.woolworths.utils.URLResources;
import au.com.woolworths.utils.Utilities;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class RewardsCardWithWalletHelper extends IFrameCardHelper {
  RestInvocationUtil invocationUtil;

  public RewardsCardWithWalletHelper() {
    this.invocationUtil = ServiceHooks.restInvocationUtil;
  }

  public RewardsCardHomePageWithWalletResponse iRetrieveMyRewardsCardWithWallet(String query) throws IOException {
    return mapper.readValue(postQuery(query), RewardsCardHomePageWithWalletResponse.class);
  }

  public FetchAddSchemeCardURLResponse iRetrieveAddSchemeCardURL(String query) throws IOException {
    return mapper.readValue(postQuery(query), FetchAddSchemeCardURLResponse.class);
  }

  public FetchUpdateSchemeCardURLResponse iRetrieveUpdateSchemeCardURL(String query) throws IOException {
    return mapper.readValue(postQuery(query), FetchUpdateSchemeCardURLResponse.class);
  }

  public FetchPaymentInstrumentsResponse iRetrievePaymentInstruments(String query) throws IOException {
    return mapper.readValue(postQuery(query), FetchPaymentInstrumentsResponse.class);
  }

  public DeleteSchemeCardResponse iRemoveSchemeCard(String query) throws IOException {
    return mapper.readValue(postQuery(query), DeleteSchemeCardResponse.class);
  }

  public FetchUserPreferencesResponse iRetrieveViewWallet(String query) throws IOException {
    return mapper.readValue(postQuery(query), FetchUserPreferencesResponse.class);
  }

  public AddGiftCardResponse iAddGiftCard(String query) throws IOException {
    return mapper.readValue(postQuery(query), AddGiftCardResponse.class);
  }

  public FetchVerifySchemeCardResponse iRetreieveVerifyCard(String query) throws IOException {
    return mapper.readValue(postQuery(query), FetchVerifySchemeCardResponse.class);
  }

  public ViewGiftCardResponse iRetrieveViewGiftCardResponse(String query) throws IOException {
    return mapper.readValue(postQuery(query), ViewGiftCardResponse.class);
  }

  public ViewGCPaymentPreferencesResponse iRetrieveViewGCPaymentPreferencesResponse(String query) throws IOException {
    return mapper.readValue(postQuery(query), ViewGCPaymentPreferencesResponse.class);
  }

  public ViewSCPaymentPreferencesResponse iRetrieveViewSCPaymentPreferencesResponse(String query) throws IOException {
    return mapper.readValue(postQuery(query), ViewSCPaymentPreferencesResponse.class);
  }

  public SetPrimarySchemeCardResponse iRetrieveSetPrimarySchemeCardResponse(String query) throws IOException {
    return mapper.readValue(postQuery(query), SetPrimarySchemeCardResponse.class);
  }

  private String postQuery(String query) {
    String endPoint = URLResources.METIS_REWARDS_GRAPHQL;
    Map<String, String> mapWebserviceResponse;

    mapWebserviceResponse = invocationUtil.invokePostWithHeaders(endPoint, query, headerListRewards);
    return mapWebserviceResponse.get("response");
  }

  public PaymentSessionResponse createPaymentSession() throws IOException {
    String endPoint = URLResources.DIGIPAY_IN_STORE_PAYMENT_SESSION;

    Payload payload = new Payload(TestProperties.get("DIGIPAY_STORE_ID"), TestProperties.get("DIGIPAY_LANE_ID"));
    MerchantInfo merchantInfo = new MerchantInfo(TestProperties.get("DIGIPAY_MERCHANT_SCHEMA_ID"), payload);
    PaymentSessionData paymentSessionData = new PaymentSessionData(TestProperties.get("DIGIPAY_STORE_LOCATION"), merchantInfo, true, 60, 300);
    Meta meta = new Meta();
    PaymentSessionRequest paymentSessionRequest = new PaymentSessionRequest(paymentSessionData, meta);

    // Need this as we are passing an empty Meta object
    mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
    String requestStr = mapper.writeValueAsString(paymentSessionRequest);

    Map<String, String> mapWebserviceResponse = invocationUtil.invokePostWithHeaders(endPoint, requestStr, headerListDigipay);
    String response = mapWebserviceResponse.get("response");

    return mapper.readValue(response, PaymentSessionResponse.class);
  }

  public QRIDResponse scanQRCode(String qrID) throws IOException {
    String endPoint = URLResources.METIS_QRID;
    endPoint = endPoint.replace(":qrId", qrID);

    Map<String, String> mapWebserviceResponse = invocationUtil.invokeGetWithoutParam(endPoint, headerListRewards);
    String response = mapWebserviceResponse.get("response");

    return mapper.readValue(response, QRIDResponse.class);
  }

  public void putPreAuthPaymentSession(String paymentSessionID, String instrument) throws JsonProcessingException {
    String endPoint = URLResources.METIS_PAYMENT_SESSION;
    endPoint = endPoint.replace(":paymentSessionId", paymentSessionID);
    String[] emptyArray = new String[0];

    SessionData paymentSessionData = new SessionData(instrument, emptyArray, Utilities.generateRandomUUIDString());
    SessionMeta meta = new SessionMeta();
    SessionRequest sessionRequest = new SessionRequest(paymentSessionData, meta);
    String requestStr = mapper.writeValueAsString(sessionRequest);

    invocationUtil.invokePut(endPoint, requestStr, headerListRewards);
  }

  public PaymentResponse postPaymentRequest() throws IOException {
    String endPoint = URLResources.DIGIPAY_IN_STORE_PAYMENTS;

    au.com.woolworths.model.metis.digipay_payment.pos.Payload payload = new au.com.woolworths.model.metis.digipay_payment.pos.Payload(TestProperties.get("DIGIPAY_STORE_ID"), "Test Lane", TestProperties.get("DIGIPAY_RNN"), 1598310169964L);
    PosPayload posPayload = new PosPayload(TestProperties.get("DIGIPAY_POS_SCHEMA_ID"), payload);
    PaymentRequestData paymentRequestData = new PaymentRequestData(Utilities.generateRandomUUIDString(), Integer.parseInt(TestProperties.get("DIGIPAY_TRANSACTION_AMOUNT")), false, 3, 300, 60, "", posPayload);
    Meta meta = new Meta();
    PaymentRequest paymentRequest = new PaymentRequest(paymentRequestData, meta);

    String requestStr = mapper.writeValueAsString(paymentRequest);
    Map<String, String> mapWebserviceResponse = invocationUtil.invokePostWithHeaders(endPoint, requestStr, headerListDigipay);
    String response = mapWebserviceResponse.get("response");

    return mapper.readValue(response, PaymentResponse.class);
  }

  public void addPaymentRequestToSession(String requestID, String sessionID) throws JsonProcessingException {
    String endPoint = URLResources.DIGIPAY_IN_STORE_PAYMENT_SESSION + "/" + requestID;

    Payload payload = new Payload(TestProperties.get("DIGIPAY_STORE_ID"), TestProperties.get("DIGIPAY_LANE_ID"));
    MerchantInfo merchantInfo = new MerchantInfo(TestProperties.get("DIGIPAY_MERCHANT_SCHEMA_ID"), payload);
    AddPaymentRequestData addPaymentRequestData = new AddPaymentRequestData(sessionID, TestProperties.get("DIGIPAY_STORE_LOCATION"), merchantInfo, requestID);
    Meta meta = new Meta();
    AddPaymentRequest addPaymentRequest = new AddPaymentRequest(addPaymentRequestData, meta);

    String requestStr = mapper.writeValueAsString(addPaymentRequest);
    invocationUtil.invokePostWithHeaders(endPoint, requestStr, headerListDigipay);
  }

  public TransactionsResponse getTransactionDetails(String requestID) throws IOException {
    String endPoint = URLResources.METIS_TRANSACTIONS;

    Map<String, String> queryParams = new HashMap<>();
    queryParams.put("paymentRequestId", requestID);

    Map<String, String> mapWebserviceResponse = invocationUtil.invokeGetWithHeaders(endPoint, queryParams, headerListRewards);
    String responseStr = mapWebserviceResponse.get("response");

    return mapper.readValue(responseStr, TransactionsResponse.class);
  }

  public String getInstrument() throws IOException {
    InputStream iStream = WalletDefinition.class.getResourceAsStream("/gqlQueries/metis/queries/wallet/fetchPaymentInstruments.graphql");
    String graphqlQuery = GraphqlParser.parseGraphql(iStream, null);

    FetchPaymentInstrumentsResponse fetchPaymentInstrumentsResponse = iRetrievePaymentInstruments(graphqlQuery);

    return fetchPaymentInstrumentsResponse.getData().getPaymentInstruments()[0].getId();
  }
}
