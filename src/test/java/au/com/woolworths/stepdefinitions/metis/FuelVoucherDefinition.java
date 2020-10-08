package au.com.woolworths.stepdefinitions.metis;

import au.com.woolworths.graphql.parser.GraphqlParser;
import au.com.woolworths.helpers.metis.FuelVoucherHelper;
import au.com.woolworths.model.metis.fuelvoucher.FuelVoucherResponse;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import junit.framework.Assert;

import java.io.IOException;
import java.io.InputStream;

public class FuelVoucherDefinition extends FuelVoucherHelper {

  private FuelVoucherResponse fuelVoucherResponse;

  @When("^the user selects fuel vouchers$")
  public void theUserSelectsFuelVouchers() throws IOException {
    InputStream iStream = FuelVoucherDefinition.class.getResourceAsStream("/gqlQueries/metis/queries/fuelVoucher.graphql");
    String graphqlQuery = GraphqlParser.parseGraphql(iStream, null);
    fuelVoucherResponse = iRetrieveFuelVoucher(graphqlQuery);
  }

  @Then("^the user should see the terms and conditions link for fuel vouchers$")
  public void theUserShouldSeeAllHisFuelVouchersEarned() {
    Assert.assertEquals("Incorrect instructions", "To redeem, simply scan your Rewards card at participating fuel outlets", fuelVoucherResponse.getData().getFuelVoucherList().getInstructions());
    Assert.assertEquals("Terms and Conditions is not returned", "Terms & Conditions", fuelVoucherResponse.getData().getFuelVoucherList().getInstructionsButton().getTitle());
    Assert.assertNotNull(fuelVoucherResponse.getData().getFuelVoucherList().getInstructionsButton().getTitle());
  }

  @And("^the user does not have a fuel voucher$")
  public void theUserDoesNotHaveAFuelVoucher() {
    Assert.assertEquals(0, fuelVoucherResponse.getData().getFuelVoucherList().getItems().size());
  }

  @And("^the user should see a fuel icon$")
  public void theUserShouldSeeAFuelIcon() {
    Assert.assertEquals("Fuel pump icon is not returned", "fuel_pump", fuelVoucherResponse.getData().getFuelVoucherList().getMessage().getIcon());
  }
}