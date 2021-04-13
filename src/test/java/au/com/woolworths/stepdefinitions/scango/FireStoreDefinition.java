package au.com.woolworths.stepdefinitions.scango;

import au.com.woolworths.helpers.scango.RefreshTokenHelper;
import au.com.woolworths.model.scango.firstore.ReadFirestoreDocRefreshTokenResponse;
import au.com.woolworths.model.scango.firstore.UpsertFirestoreDocRefreshResponse;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;

public class FireStoreDefinition extends RefreshTokenHelper {
  @Given("^I get Refresh Token from Firestore$")
  public void iCallReadFireStoreDocToGetRefreshToken() throws Throwable {
    ReadFirestoreDocRefreshTokenResponse response = readRefreshTokenFromFireStore();
    sharedData.refreshToken = response.getEntities()[0].getUat_refresh_token();
  }

  @And("^I Update the Refresh Token to Firestore$")
  public void iCallUpsertFireStoreDocToUpdateRefreshToken() throws Throwable {
    UpsertFirestoreDocRefreshResponse response = upsertRefreshTokenToFireStore();
  }
}
