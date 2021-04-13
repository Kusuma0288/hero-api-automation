package au.com.woolworths.stepdefinitions.metis;

import au.com.woolworths.graphql.parser.GraphqlParser;
import au.com.woolworths.helpers.metis.FuelVoucherHelper;
import au.com.woolworths.model.metis.fuelvoucher.FuelVoucherResponse;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import junit.framework.Assert;

import java.io.IOException;
import java.io.InputStream;

public class FuelVoucherDefinition extends FuelVoucherHelper {

  private FuelVoucherResponse fuelVoucherResponse;

  @When("^the user selects fuel vouchers$")
  public void theUserSelectsFuelVouchers() throws IOException {
    InputStream iStream = FuelVoucherDefinition.class.getResourceAsStream("/gqlQueries/metis/queries/rewards/fuelVoucher.graphql");
    String graphqlQuery = GraphqlParser.parseGraphql(iStream, null);
    fuelVoucherResponse = iRetrieveFuelVoucher(graphqlQuery);
  }

  @Then("^the user should see the terms and conditions link for fuel vouchers$")
  public void theUserShouldSeeAllHisFuelVouchersEarned() {
    Assert.assertEquals("Incorrect instructions", "To redeem, simply scan your Rewards card at participating fuel outlets", fuelVoucherResponse.getData().getFuelVoucherList().getInstructions());
    Assert.assertEquals("Terms and Conditions title is not returned", "Terms & Conditions", fuelVoucherResponse.getData().getFuelVoucherList().getMessage().getButton().getTitle());
    Assert.assertTrue("Terms and Conditions url not returned", fuelVoucherResponse.getData().getFuelVoucherList().getMessage().getButton().getUrl().contains("4c-per-litre"));
  }

  @And("^the user does not have a fuel voucher$")
  public void theUserDoesNotHaveAFuelVoucher() {
    Assert.assertEquals(0, fuelVoucherResponse.getData().getFuelVoucherList().getItems().size());
  }

  @And("^the user should see a fuel icon$")
  public void theUserShouldSeeAFuelIcon() {
    Assert.assertTrue("Fuel pump icon is not returned", fuelVoucherResponse.getData().getFuelVoucherList().getMessage().getIconUrl().contains("fuel_icon.png"));
  }
}