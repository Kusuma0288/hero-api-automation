package au.com.woolworths.stepdefinitions.scango;

import au.com.woolworths.helpers.scango.LoginHelper;
import au.com.woolworths.model.scango.kiosk.KioskLoginResponse;
import cucumber.api.java.en.And;

import java.io.IOException;
import java.util.logging.Logger;

public class KioskLoginDefinition extends LoginHelper {

  private final static Logger logger = Logger.getLogger("KioskLoginDefinition.class");

  @And("^I call Team member barcode API to get team member access token$")
  public void iCallTeamMemberLoginApi() throws IOException {
    KioskLoginResponse response = iCallKioskLoginAPI();
    sharedData.kioskAccessToken = response.getAccess_token();
  }
}
