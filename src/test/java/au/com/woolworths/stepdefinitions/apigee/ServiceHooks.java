package au.com.woolworths.stepdefinitions.apigee;

import au.com.woolworths.Utils.RestInvocationUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;

import java.util.logging.Logger;

public class ServiceHooks {

  private static Logger logger = Logger.getLogger("ServiceHooks.class");
  public static RestInvocationUtil restInvocationUtil;
  public String saveRequestResponse = "No";

  @Before
  public void initializeTest() {
    restInvocationUtil = new RestInvocationUtil();
    if ((System.getProperty("saveRequestResponse") != null) && System.getProperty("saveRequestResponse").equalsIgnoreCase("Yes")) {
      saveRequestResponse = "Yes";
    }
  }

  @After
  public void finaliseTest(Scenario scenario) {
    if (saveRequestResponse.equalsIgnoreCase("Yes") && restInvocationUtil.requests.size() == restInvocationUtil.responses.size()) {
      Gson gson = new GsonBuilder().setPrettyPrinting().create();
      JsonParser jp = new JsonParser();
      for (int i = 0; i < restInvocationUtil.requests.size(); i++) {
        scenario.embed(restInvocationUtil.endPoints.get(i).getBytes(), "text/html");
        try {
          scenario.embed(gson.toJson(jp.parse(restInvocationUtil.requests.get(i))).getBytes(), "application/json");
        } catch (Exception e) {
          scenario.embed(restInvocationUtil.requests.get(i).getBytes(), "application/json");
        }
        scenario.embed(gson.toJson(jp.parse(restInvocationUtil.responses.get(i).getBody().asString())).getBytes(), "application/json");
      }
    }
    if (scenario.isFailed()) {
      try {
        logger.info("Scenario failed::" + scenario.getName());
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }
}