package au.com.woolworths.stepdefinitions.common;

import au.com.woolworths.helpers.common.BaseHelper;
import au.com.woolworths.utils.RestInvocationUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.testng.asserts.SoftAssert;

import java.util.logging.Logger;

public class ServiceHooks {

  public static RestInvocationUtil restInvocationUtil;
  private static Logger logger = Logger.getLogger("ServiceHooks.class");
  public String saveRequestResponse = "No";

  @Before
  public void initializeTest() {
    BaseHelper.softAssert = new SoftAssert();
    restInvocationUtil = new RestInvocationUtil();
    if ((System.getProperty("saveRequestResponse") != null) && System.getProperty("saveRequestResponse").equalsIgnoreCase("Yes")) {
      saveRequestResponse = "Yes";
    }
  }

  @After
  public void finaliseTest(Scenario scenario) {
    BaseHelper.softAssert.assertAll();
    if (saveRequestResponse.equalsIgnoreCase("Yes") && restInvocationUtil.requests.size() == restInvocationUtil.responses.size()) {
      Gson gson = new GsonBuilder().setPrettyPrinting().create();
      JsonParser jp = new JsonParser();
      for (int i = 0; i < restInvocationUtil.requests.size(); i++) {
        scenario.attach(restInvocationUtil.endPoints.get(i).getBytes(), "text/html", "endpoints");
        try {
          scenario.attach(gson.toJson(jp.parse(restInvocationUtil.requests.get(i))).getBytes(), "application/json", "requests");
        } catch (Exception e) {
          scenario.attach(restInvocationUtil.requests.get(i).getBytes(), "application/json", "requests");
        }
        scenario.attach(gson.toJson(jp.parse(restInvocationUtil.responses.get(i).getBody().asString())).getBytes(), "application/json", "responses");
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