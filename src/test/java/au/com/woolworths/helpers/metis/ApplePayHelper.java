package au.com.woolworths.helpers.metis;

import au.com.woolworths.helpers.common.BaseHelper;
import au.com.woolworths.model.metis.applewallet.AppleWalletResponse;
import au.com.woolworths.stepdefinitions.common.ServiceHooks;
import au.com.woolworths.utils.RestInvocationUtil;
import au.com.woolworths.utils.URLResources;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Map;

public class ApplePayHelper extends BaseHelper {
    RestInvocationUtil invocationUtil;

    public ApplePayHelper() {
        this.invocationUtil = ServiceHooks.restInvocationUtil;
    }

    public AppleWalletResponse iAddToAppleWallet(String query) throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        Map <String, String> mapWebserviceResponse;

        String endPoint = URLResources.METIS_REWARDS_GRAPHQL;

        mapWebserviceResponse = invocationUtil.invokePostWithHeaders(endPoint, query, headerListRewards);
        String responseStr = mapWebserviceResponse.get("response");
        return mapper.readValue(responseStr, AppleWalletResponse.class);
    }
}
