package au.com.woolworths.stepdefinitions.metis;

import au.com.woolworths.helpers.metis.SpecialsHelper;
import au.com.woolworths.model.metis.specials.Partner;
import au.com.woolworths.model.metis.specials.Product;
import au.com.woolworths.model.metis.specials.SpecialsResponse;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import junit.framework.Assert;


import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

public class SpecialsDefinition extends SpecialsHelper {

  private SpecialsResponse response;
  private final static Logger logger = Logger.getLogger("SpecialsDefinition.class");

  @When("^the user selects his favourites products$")
  public void theUserSelectsHisFavouritesProducts() throws IOException {
    response = getSpecialsResponse();
    logger.info("Showing favourite products");
  }

  @Then("^the user should see all his favourites product$")
  public void theUserShouldSeeAllHisFavouritesProduct() {

    for (int j = 0; j < response.getData().getPartners().size(); j++) {
      List<Product> products = response.getData().getPartners().get(j).getData().getProducts();
      for (Product product : products) {
        Assert.assertNotNull("product id ", product.getData().getProductId());
        Assert.assertTrue("price not returned for productId " + product.getData().getProductId(), response.getData().getPartners().get(0).getData().getProducts().get(0).getData().getPrice() >= 0);
        Assert.assertNotNull("product image ", product.getData().getProductImage());
        Assert.assertNotNull("price description ", product.getData().getUnitPriceDescription());
      }
    }
  }

  @And("^if no products are present the user should see a message$")
  public void ifNoProductsArePresentTheUserShouldSeeAMessage() {

    for (int i = 0; i < response.getData().getPartners().size(); i++) {
      Partner partner = response.getData().getPartners().get(i);
      if (partner.getData().getProducts().size() == 0) {
        Assert.assertNotNull("Partner image is not shown ", partner.getData().getMessage().getData().getImageUrl());
        Assert.assertNotNull("Partner title is not shown ", partner.getData().getMessage().getData().getTitle());
      }
    }
  }

  @And("^if the partner is Woolworths it should display the action button$")
  public void ifPartnerIsWoolworthsShowActionButton() {
    Partner woolworthsPartner = response.getData().getPartners().get(0);
    if (woolworthsPartner.getData().getProducts().size() == 0) {
      Assert.assertNotNull("Woolworth image is not displayed", woolworthsPartner.getData().getMessage().getData().getImageUrl());
      Assert.assertNotNull("Woolworths title is not shown", woolworthsPartner.getData().getMessage().getData().getTitle());
    }
  }
}
