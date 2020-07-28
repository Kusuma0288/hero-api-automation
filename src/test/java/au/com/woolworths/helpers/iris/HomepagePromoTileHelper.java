package au.com.woolworths.helpers.iris;

import java.util.Map;
import java.util.logging.Logger;

import au.com.woolworths.helpers.common.BaseHelper;
import au.com.woolworths.utils.RestInvocationUtil;
import au.com.woolworths.utils.TestProperties;
import au.com.woolworths.utils.URLResources;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.MapperFeature;
import au.com.woolworths.stepdefinitions.apigee.ServiceHooks;
import com.fasterxml.jackson.databind.DeserializationFeature;
import io.restassured.response.Response;

public class HomepagePromoTileHelper extends BaseHelper {

    RestInvocationUtil invocationUtil;
    private final static Logger logger = Logger.getLogger("HomepageHelper.class");
    public Response response;

    public HomepagePromoTileHelper() {
        this.invocationUtil = ServiceHooks.restInvocationUtil;
    }

    public void getProductsByProductGroup(String query) throws Throwable {
        ObjectMapper mapper = new ObjectMapper();
        String endPoint = URLResources.V1_GRAPHQL;
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);
        Map<String, String> mapWebserviceResponse;
        mapWebserviceResponse = invocationUtil.invokePostWithHeaders(endPoint, query, headerList);
        String responseStr = mapWebserviceResponse.get("response");
        /**
         * TODO: Add pojo for response while automating actual graphql tests.
         * For now only printing the response to demo graphql parser. Print statement to be removed.
         */
        System.out.println(responseStr);
    }

}
