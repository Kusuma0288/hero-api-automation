package au.com.woolworths.stepdefinitions.scango;

import au.com.woolworths.helpers.scango.LoginHelper;
import au.com.woolworths.model.scango.firstore.FirestoreReadDocTeamMemberBarcodeResponse;
import au.com.woolworths.model.scango.kiosk.KioskLoginResponse;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;

import java.io.IOException;
import java.util.logging.Logger;

public class KioskLoginDefinition extends LoginHelper {

  private final static Logger logger = Logger.getLogger("KioskLoginDefinition.class");

  @And("^I call Team member barcode API to get team member access token$")
  public void iCallTeamMemberLoginApi() throws IOException {
    KioskLoginResponse response = iCallKioskLoginAPI();
    sharedData.kioskAccessToken = response.getAccess_token();
  }

  @When("^I get Team Member barcode from Firestore Document$")
  public void iGetTeamMemberBarcodeFromFirestoreDocument() throws Throwable {
    FirestoreReadDocTeamMemberBarcodeResponse response = iCallFireStoreTeamMemberBarcodeAPI();
    sharedData.teamMemberBarcode = response.getEntities()[0].get_2701();
    System.out.println(sharedData.teamMemberBarcode);
  }

}
