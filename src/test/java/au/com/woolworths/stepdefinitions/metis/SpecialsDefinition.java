package au.com.woolworths.stepdefinitions.metis;

import au.com.woolworths.helpers.metis.SpecialsHelper;
import au.com.woolworths.model.metis.specials.SpecialsResponse;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import junit.framework.Assert;

import java.io.IOException;

public class SpecialsDefinition extends SpecialsHelper {

  private SpecialsResponse response;

  @When("^the user selects his favourites products$")
  public void theUserSelectsHisFavouritesProducts() throws IOException {
    response = getSpecialsResponse();
  }

  @Then("^the user should see all his favourites product$")
  public void theUserShouldSeeAllHisFavouritesProduct() {

    for (int j = 0; j < response.getPartners().length; j++) {
      for (int i = 0; i < response.getPartners()[j].getProducts().length; i++) {
        Assert.assertNotNull("product id ", response.getPartners()[j].getProducts()[i].getProductId());
        Assert.assertTrue("price not returned for productId " + response.getPartners()[j].getProducts()[i].getProductId(), response.getPartners()[0].getProducts()[0].getPrice() >= 0);
        Assert.assertNotNull("product image ", response.getPartners()[j].getProducts()[i].getProductImage());
        Assert.assertNotNull("price description ", response.getPartners()[j].getProducts()[i].getUnitPriceDescription());
      }
    }

  }

  @And("^if no products are present the user should see a message$")
  public void ifNoProductsArePresentTheUserShouldSeeAMessage() {

    for (int i = 0; i < response.getPartners().length; i++) {
      if (response.getPartners()[i].getProducts().length == 0) {
        Assert.assertNotNull(response.getPartners()[i].getMessage().getImageUrl());
        Assert.assertNotNull(response.getPartners()[i].getMessage().getTitle());
        Assert.assertNotNull(response.getPartners()[i].getMessage().getType());
      }
    }
  }

  @And("^if the partner is Woolworths it should display the action button$")
  public void ifPartnerIsWoolworthsShowActionButton() {
    if (response.getPartners()[0].getProducts().length == 0) {
      Assert.assertNotNull(response.getPartners()[0].getMessage().getActionButton().getUrl());
      Assert.assertNotNull(response.getPartners()[0].getMessage().getActionButton().getTitle());
      Assert.assertNotNull(response.getPartners()[0].getMessage().getActionButton().getType());
    }
  }
}
