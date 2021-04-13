package au.com.woolworths.stepdefinitions.metis;

import au.com.woolworths.helpers.metis.SpecialsHelper;
import au.com.woolworths.model.metis.specials.Partner;
import au.com.woolworths.model.metis.specials.Product;
import au.com.woolworths.model.metis.specials.SpecialsResponse;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import junit.framework.Assert;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

public class SpecialsDefinition extends SpecialsHelper {

  private final static Logger logger = Logger.getLogger("SpecialsDefinition.class");
  private SpecialsResponse response;
  private String sortOptionWithUnderscore;

  @When("^the user selects his favourites products$")
  public void theUserSelectsHisFavouritesProducts() throws IOException {
    response = getSpecialsResponse();
    logger.info("Showing favourite products");
  }

  @When("^the user sorts his favourites products by (.*)$")
  public void theUserSortsHisFavouritesProducts(String sortOption) throws IOException {
    sortOptionWithUnderscore = sortOption.toUpperCase().replace(" ", "_");
    response = getSpecialsSortedResponse(sortOptionWithUnderscore);
    logger.info("Showing favourite products sorted by " + sortOption);
  }

  @Then("^the user should see all his favourites product$")
  public void theUserShouldSeeAllHisFavouritesProduct() {
    Assert.assertEquals("status code", "200", response.getStatusCode());

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
  public boolean areProductsPresent() {

    for (int i = 0; i < response.getData().getPartners().size(); i++) {
      Partner partner = response.getData().getPartners().get(i);
      if (partner.getData().getProducts().size() == 0) {
        Assert.assertNotNull("Partner image is not shown ", partner.getData().getMessage().getData().getImageUrl());
        Assert.assertNotNull("Partner title is not shown ", partner.getData().getMessage().getData().getTitle());
      }
    }
    return true;
  }

  @And("^if the partner is Woolworths it should display the action button$")
  public void ifPartnerIsWoolworthsShowActionButton() {
    Partner woolworthsPartner = response.getData().getPartners().get(0);
    if (woolworthsPartner.getData().getProducts().size() == 0) {
      Assert.assertNotNull("Woolworth image is not displayed", woolworthsPartner.getData().getMessage().getData().getImageUrl());
      Assert.assertNotNull("Woolworths title is not shown", woolworthsPartner.getData().getMessage().getData().getTitle());
    }
  }

  @Then("^the product should be sorted by Sort Option$")
  public void theProductShouldBeSortedBySortOption() {
    if (areProductsPresent()) {
      return;
    }
    Assert.assertEquals("status code", "200", response.getStatusCode());

    List<Product> products = response.getData().getPartners().get(0).getData().getProducts();
    switch (sortOptionWithUnderscore) {
      case "PRICE_LOW_TO_HIGH":
        isSortedByPrice(products, true);
        break;
      case "PRICE_HIGH_TO_LOW":
        isSortedByPrice(products, false);
        break;
      case "NAME_A_TO_Z":
        isSortedByName(products, false);
        break;
      case "NAME_Z_TO_A":
        isSortedByName(products, true);
        break;
    }


  }

  public void isSortedByPrice(List<Product> products, boolean ascending) {
    for (int i = 1; i < products.size(); i++) {
      double price = products.get(i - 1).getData().getPrice();
      double nextPrice = products.get(i).getData().getPrice();
      if (ascending) {
        Assert.assertTrue("Favourites is not sorted 0-9", price <= nextPrice);
      } else {
        Assert.assertTrue("Favourites is not sorted 9-0", price >= nextPrice);
      }
    }
  }

  public void isSortedByName(List<Product> products, boolean ascending) {
    for (int i = 1; i < products.size(); i++) {
      String name = products.get(i - 1).getData().getName();
      String nextName = products.get(i).getData().getName();
      if (ascending) {
        Assert.assertTrue("Favourites is not sorted A-Z", name.compareToIgnoreCase(nextName) >= 0);
      } else {
        Assert.assertTrue("Favourites is not sorted Z-A", name.compareToIgnoreCase(nextName) <= 0);
      }
    }
  }

}
