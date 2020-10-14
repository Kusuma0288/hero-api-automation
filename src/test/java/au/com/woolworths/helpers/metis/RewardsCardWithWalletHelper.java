package au.com.woolworths.helpers.metis;

import au.com.woolworths.helpers.apigee.IFrameCardHelper;
import au.com.woolworths.model.metis.card.*;
import au.com.woolworths.stepdefinitions.common.ServiceHooks;
import au.com.woolworths.utils.RestInvocationUtil;
import au.com.woolworths.utils.URLResources;

import java.io.IOException;
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

  private String postQuery(String query) {
    String endPoint = URLResources.METIS_REWARDS_GRAPHQL;
    Map<String, String> mapWebserviceResponse;

    mapWebserviceResponse = invocationUtil.invokePostWithHeaders(endPoint, query, headerListRewards);
    return mapWebserviceResponse.get("response");
  }
}
