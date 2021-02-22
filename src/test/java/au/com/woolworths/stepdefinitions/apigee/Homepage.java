package au.com.woolworths.stepdefinitions.apigee;

import au.com.woolworths.helpers.apigee.HomepageHelper;
import au.com.woolworths.model.apigee.homepage.HomepageComponents;
import au.com.woolworths.model.apigee.homepage.HomepageComponentsData;
import au.com.woolworths.model.apigee.homepage.HomepageResponse;
import au.com.woolworths.model.apigee.productgroups.ProductGroupComponents;
import au.com.woolworths.model.apigee.productgroups.ProductGroupResponse;
import au.com.woolworths.model.apigee.productgroups.ProductGroupTrolleyData;
import cucumber.api.PendingException;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import junit.framework.Assert;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.InstanceOfAssertFactories.iterable;

public class Homepage extends HomepageHelper {

  private final static Logger logger = Logger.getLogger("Homepage.class");

  @When("^I make a request to Homepage in IN-STORE mode with store id \"([^\"]*)\" with \"([^\"]*)\" and verify the response for Offer \"([^\"]*)\"$")
  public void verifyHomepageInStoreResponse(String storeId, String EDRStatus, String Offer) throws Throwable {
    Map<String, String> queryParams = new HashMap<String, String>();
    queryParams.put("store", storeId);
    HomepageResponse homepageResponse = iRetrieveHomepageWithInStore(queryParams);

    //Assert Response is not Null
    Assert.assertNotNull(homepageResponse.getData());

    //Assert Rewards card is not attached
    if (EDRStatus.equals("NoEDR")) {
      Assert.assertTrue("Rewards card component missing", Arrays.stream(homepageResponse.getData().getItems()).anyMatch(item -> item.getType().equals("AddRewardsCard")));
      HomepageComponents item = Arrays.stream(homepageResponse.getData().getItems()).filter(x -> x.getType().equals("AddRewardsCard")).findFirst().get();
      Assert.assertEquals("Rewards title mismatch", "+ Add Rewards Card", item.getData().getTitle());
      Assert.assertEquals("Rewards subtitle mismatch", "Collect points and boost your savings.", item.getData().getSubtitle());
    }

    //Assert Catalogue details
    Assert.assertTrue("Catalogue card component missing", Arrays.stream(homepageResponse.getData().getItems()).anyMatch(item -> item.getType().equals("CatalogueCard")));
    HomepageComponentsData item = Arrays.stream(homepageResponse.getData().getItems()).filter(x -> x.getType().equals("CatalogueCard")).findFirst().get().getData();
    Assert.assertEquals("Catalogue card title mismatch", "Catalogue", item.getTitle());
    Assert.assertEquals("Catalogue card subtitle mismatch", "View current specials", item.getSubtitle());

    //Assert Horizontal list details - All Specials
    Assert.assertTrue("Horizontal list component missing " + Offer
            , Arrays.stream(homepageResponse.getData().getItems()).anyMatch(item1 -> item1.getType().equals("HorizontalList")));
    HomepageComponentsData item1 = Arrays.stream(homepageResponse.getData().getItems()).filter(x -> x.getType().equals("HorizontalList")).findFirst().get().getData();
    Assert.assertEquals("All Specials title mismatch", "All Specials", item1.getTitle());
    Assert.assertEquals("All Specials subtitle mismatch", "All the latest specials and offers", item1.getSubtitle());
    Assert.assertEquals("All Specials action title mismatch", "See All", item1.getActionTitle());
    Assert.assertNotNull("All Specials action path is not present", item1.getActionPath());
    Assert.assertNotNull("No products under All Specials", item1.getItems());

    //Assert Recipe details
    Assert.assertTrue("Recipes card component missing", Arrays.stream(homepageResponse.getData().getItems()).anyMatch(item2 -> item2.getType().equals("RecipesCard")));
    HomepageComponentsData item2 = Arrays.stream(homepageResponse.getData().getItems()).filter(x -> x.getType().equals("RecipesCard")).findFirst().get().getData();
    Assert.assertEquals("Recipes card title mismatch", "Recipes", item2.getTitle());
    Assert.assertEquals("Recipes card subtitle mismatch", "1000's of new recipes to inspire your cooking", item2.getSubtitle());
    Assert.assertNotNull("Recipes image is not present", item2.getImage());

    //Assert Promo Tile not displayed
    Assert.assertFalse("Promo card component displayed in In-Store mode", Arrays.stream(homepageResponse.getData().getItems()).anyMatch(item3 -> item3.getType().equals("PromotionCard")));

  }

  @When("^I make a request to Homepage in \"([^\"]*)\" mode with \"([^\"]*)\" and verify the response for Offer \"([^\"]*)\"$")
  public void verifyHomepageOnlinePickupResponse(String shoppingMode, String EDRStatus, String Offer) throws Throwable {
    Map<String, String> queryParams = new HashMap<String, String>();
    queryParams.put("mode", shoppingMode);
    HomepageResponse homepageResponse = iRetrieveHomepageWithInStore(queryParams);
    //Assert Response is not Null
    Assert.assertNotNull(homepageResponse.getData());

    sharedData.mode = shoppingMode;

    //Assert Rewards card is not attached
    if (EDRStatus.equals("NoEDR")) {
      Assert.assertTrue("Rewards card component missing", Arrays.stream(homepageResponse.getData().getItems()).anyMatch(item -> item.getType().equals("AddRewardsCard")));
      HomepageComponents item = Arrays.stream(homepageResponse.getData().getItems()).filter(x -> x.getType().equals("AddRewardsCard")).findFirst().get();
      Assert.assertEquals("Rewards title mismatch", "+ Add Rewards Card", item.getData().getTitle());
      Assert.assertEquals("Rewards subtitle mismatch", "Collect points and boost your savings.", item.getData().getSubtitle());
    }

    //Assert Catalogue details
    Assert.assertTrue("Catalogue card component missing", Arrays.stream(homepageResponse.getData().getItems()).anyMatch(item -> item.getType().equals("CatalogueCard")));
    HomepageComponentsData item = Arrays.stream(homepageResponse.getData().getItems()).filter(x -> x.getType().equals("CatalogueCard")).findFirst().get().getData();
    Assert.assertEquals("Catalogue card title mismatch", "Catalogue", item.getTitle());
    Assert.assertEquals("Catalogue card subtitle mismatch", "View current specials", item.getSubtitle());

    //Assert Horizontal list details - Online Only Offers
    Assert.assertTrue("Horizontal list component missing " + Offer,
            Arrays.stream(homepageResponse.getData().getItems()).anyMatch(item1 -> item1.getType().equals("HorizontalList")));
    HomepageComponentsData item1 = Arrays.stream(homepageResponse.getData().getItems()).filter(x -> x.getType().equals("HorizontalList")).findFirst().get().getData();
    Assert.assertEquals("All Specials title mismatch", "Online Only Offers", item1.getTitle());
    Assert.assertEquals("All Specials subtitle mismatch", "Specials available exclusively online", item1.getSubtitle());
    Assert.assertEquals("All Specials action title mismatch", "See All", item1.getActionTitle());
    Assert.assertNotNull("All Specials action path is not present", item1.getActionPath());
    Assert.assertNotNull("No products under All Specials", item1.getItems());

    sharedData.stockCode.add(Arrays.stream(item1.getItems()).filter(item7 -> item7.getData().getIsAvailable().equals("true")).
            findFirst().get().getData().getProductId().replaceFirst("^0+(?!$)", ""));

    //Assert Recipe details
    Assert.assertTrue("Recipes card component missing", Arrays.stream(homepageResponse.getData().getItems()).anyMatch(item2 -> item2.getType().equals("RecipesCard")));
    HomepageComponentsData item2 = Arrays.stream(homepageResponse.getData().getItems()).filter(x -> x.getType().equals("RecipesCard")).findFirst().get().getData();
    Assert.assertEquals("Recipes card title mismatch", "Recipes", item2.getTitle());
    Assert.assertEquals("Recipes card subtitle mismatch", "1000's of new recipes to inspire your cooking", item2.getSubtitle());
    Assert.assertNotNull("Recipes image is not present", item2.getImage());

    //Assert Promo card details

    Assert.assertTrue("Promotion card component missing", Arrays.stream(homepageResponse.getData().getItems()).anyMatch(item3 -> item3.getType().equals("PromoCard")));
    HomepageComponentsData item3 = Arrays.stream(homepageResponse.getData().getItems()).filter(x -> x.getType().equals("PromoCard")).findFirst().get().getData();
    sharedData.promoTileDataPath = item3.getDataPath();
    Assert.assertNotNull("Promotion card title is not present", item3.getTitle());
    Assert.assertNotNull("Promotion card promotionType is not present", item3.getPromotionType());
    Assert.assertNotNull("Promotion card dataPath is not present", item3.getDataPath());
    Assert.assertNotNull("Promotion card image is not present", item3.getImage());

  }

  @When("^I navigate to PromoTile from Homepage in \"([^\"]*)\" mode$")
  public void navigateToPromoTile(String mode) throws Throwable {
    ProductGroupResponse productGroupResponse = iRetrieveProductGroup(mode, sharedData.promoTileDataPath);

    ProductGroupComponents product = Arrays.stream(productGroupResponse.getData().getItems())
            .filter(item -> item.getData().getIsAvailable().equals("true")).findFirst().get();
    //Assert ProductGroup returns at least 1 product
    Assert.assertNotNull("Product Group has at least 1 item", product);

    //Assert the button label is ADD when the product is not added to trolley
    Assert.assertEquals("Button label of the product is not Add", "Add", product.getData().getTrolley().getData().getButtonLabel());
    Assert.assertEquals("Button state of the product is not ADD", "ADD", product.getData().getTrolley().getData().getButtonState());
    Assert.assertEquals("Trolley value of the product is not zero by default", "0", product.getData().getTrolley().getData().getInTrolley());

    sharedData.stockCode.add(product.getData().getProductId().replaceFirst("^0+(?!$)", ""));
  }

  @When("^I verify the stockcode is set to \"([^\"]*)\" when I make a request to PromoTile$")
  public void verifyProductQuantityOfPromoTile(String quantity) throws Throwable {
    ProductGroupResponse productGroupResponse = iRetrieveProductGroup(sharedData.mode, sharedData.promoTileDataPath);

    //Assert ProductGroup returns at least 1 product
    Assert.assertNotNull("Product Group has at least 1 item", productGroupResponse.getData().getItems()[0]);

    ProductGroupTrolleyData product = Arrays.stream(productGroupResponse.getData().getItems()).
            filter(item -> item.getData().getProductId().contains(sharedData.stockCode.get(0))).
            findFirst().get().getData().getTrolley().getData();

    //Assert the quantity of the product in PromoTile
    Assert.assertEquals("The button quantity of the product does not match with trolley", quantity, product.getButtonQuantity());
    Assert.assertEquals("The button state of the product is not UPDATE", "UPDATE", product.getButtonState());
    Assert.assertEquals("The button label of the product is not Update", "Update", product.getButtonLabel());
    Assert.assertEquals("The quantity of the product does not match with trolley", quantity, product.getInTrolley());
  }

  @When("^I verify the stockcode is not added to trolley when I make a request to PromoTile$")
  public void navigateToPromoTile() throws Throwable {
    ProductGroupResponse productGroupResponse = iRetrieveProductGroup(sharedData.mode, sharedData.promoTileDataPath);

    //Assert ProductGroup returns at least 1 product
    Assert.assertNotNull("Product Group has at least 1 item", productGroupResponse.getData().getItems()[0]);

    ProductGroupTrolleyData product = Arrays.stream(productGroupResponse.getData().getItems()).
            filter(item -> item.getData().getProductId().contains(sharedData.stockCode.get(0))).
            findFirst().get().getData().getTrolley().getData();

    //Assert the button label is ADD when the product is not added to trolley
    Assert.assertEquals("Button label of the product is not Add", "Add", product.getButtonLabel());
    Assert.assertEquals("Button state of the product is not ADD", "ADD", product.getButtonState());
    Assert.assertEquals("Trolley value of the product is not zero by default", "0", product.getInTrolley());
  }

  @When("^I make a request to Homepage in \"([^\"]*)\" mode with \"([^\"]*)\" and verify the response with PromoCard$")
  public void verifyHomepageOnlinePickupResponseWithPromoCard(String shoppingMode, String EDRStatus) throws Throwable {
    Map<String, String> queryParams = new HashMap<String, String>();
    queryParams.put("mode", shoppingMode);
    HomepageResponse homepageResponse = iRetrieveHomepageWithInStore(queryParams);
    //Assert Response is not Null
    Assert.assertNotNull(homepageResponse.getData());

    sharedData.mode = shoppingMode;

    //Assert Rewards card is not attached
    if (EDRStatus.equals("NoEDR")) {
      Assert.assertTrue("Rewards card component missing", Arrays.stream(homepageResponse.getData().getItems()).anyMatch(item -> item.getType().equals("AddRewardsCard")));
      HomepageComponents item = Arrays.stream(homepageResponse.getData().getItems()).filter(x -> x.getType().equals("AddRewardsCard")).findFirst().get();
      Assert.assertEquals("Rewards title mismatch", "+ Add Rewards Card", item.getData().getTitle());
      Assert.assertEquals("Rewards subtitle mismatch", "Collect points and boost your savings.", item.getData().getSubtitle());
    }

    //Assert Catalogue details
    Assert.assertTrue("Catalogue card component missing", Arrays.stream(homepageResponse.getData().getItems()).anyMatch(item -> item.getType().equals("CatalogueCard")));
    HomepageComponentsData item = Arrays.stream(homepageResponse.getData().getItems()).filter(x -> x.getType().equals("CatalogueCard")).findFirst().get().getData();
    Assert.assertEquals("Catalogue card title mismatch", "Catalogue", item.getTitle());
    Assert.assertEquals("Catalogue card subtitle mismatch", "View current specials", item.getSubtitle());

    //Assert Recipe details
    Assert.assertTrue("Recipes card component missing", Arrays.stream(homepageResponse.getData().getItems()).anyMatch(item2 -> item2.getType().equals("RecipesCard")));
    HomepageComponentsData item2 = Arrays.stream(homepageResponse.getData().getItems()).filter(x -> x.getType().equals("RecipesCard")).findFirst().get().getData();
    Assert.assertEquals("Recipes card subtitle mismatch", "1000's of new recipes to inspire your cooking", item2.getSubtitle());
    Assert.assertNotNull("Recipes image is not present", item2.getImage());

    //Assert Promo card details

    Assert.assertTrue("Promotion card component missing", Arrays.stream(homepageResponse.getData().getItems()).anyMatch(item3 -> item3.getType().equals("PromoCard")));
    HomepageComponentsData item3 = Arrays.stream(homepageResponse.getData().getItems()).filter(x -> x.getType().equals("PromoCard")).findFirst().get().getData();
    sharedData.promoTileDataPath = item3.getDataPath();
    Assert.assertNotNull("Promotion card title is not present", item3.getTitle());
    Assert.assertNotNull("Promotion card dataPath is not present", item3.getDataPath());
    Assert.assertNotNull("Promotion card image is not present", item3.getImage());
    //TODO: Verify  promotionType after decomissioning aisleCategory
  }

  @When("^I verify the stockcode is set to \"([^\"]*)\" when I make a request to Homepage$")
  public void verifyHomepageResponseWithProductAdded(String quantity) throws Throwable {
    Map<String, String> queryParams = new HashMap<String, String>();
    queryParams.put("mode", sharedData.mode);
    HomepageResponse homepageResponse = iRetrieveHomepageWithInStore(queryParams);

    //Assert Response is not Null
    Assert.assertNotNull(homepageResponse.getData());

    HomepageComponentsData productList = Arrays.stream(homepageResponse.getData().getItems()).filter(x -> x.getType().equals("HorizontalList")).findFirst().get().getData();
    ProductGroupTrolleyData product = Arrays.stream(productList.getItems()).filter(item -> item.getData().getProductId().contains(sharedData.stockCode.get(0))).
            findFirst().get().getData().getTrolley().getData();

    //Assert the quantity of the product in PromoTile
    Assert.assertEquals("The button quantity of the product does not match with trolley", quantity, product.getButtonQuantity());
    Assert.assertEquals("The button state of the product is not UPDATE", "UPDATE", product.getButtonState());
    Assert.assertEquals("The button label of the product is not Update", "Update", product.getButtonLabel());
    Assert.assertEquals("The quantity of the product does not match with trolley", quantity, product.getInTrolley());

  }

  @When("^I verify the stockcode is not added to trolley when I make a request to Homepage$")
  public void verifyHomepageResponseWithProductNotAdded() throws Throwable {
    Map<String, String> queryParams = new HashMap<String, String>();
    queryParams.put("mode", sharedData.mode);
    HomepageResponse homepageResponse = iRetrieveHomepageWithInStore(queryParams);

    //Assert Response is not Null
    Assert.assertNotNull(homepageResponse.getData());

    HomepageComponentsData productList = Arrays.stream(homepageResponse.getData().getItems()).filter(x -> x.getType().equals("HorizontalList")).findFirst().get().getData();
    ProductGroupTrolleyData product = Arrays.stream(productList.getItems()).filter(item -> item.getData().getProductId().contains(sharedData.stockCode.get(0))).
            findFirst().get().getData().getTrolley().getData();

    //Assert the button label is ADD when the product is not added to trolley
    Assert.assertEquals("Button label of the product is not Add", "Add", product.getButtonLabel());
    Assert.assertEquals("Button state of the product is not ADD", "ADD", product.getButtonState());
    Assert.assertEquals("Trolley value of the product is not zero by default", "0", product.getInTrolley());

  }

  @SuppressWarnings("checkstyle:Indentation")
  @Then("^I make a request to Homepage in Delivery mode and verify the DeliveryNowCard for \"([^\"]*)\" address$")
  public void iMakeARequestToHomepageInDeliveryModeAndVerifyTheDeliveryNowCardForAddress(String eligibility) throws Throwable {
    Map<String, String> queryParams = new HashMap<String, String>();
    queryParams.put("mode", "online");
    HomepageResponse homepageResponse = iRetrieveHomepageWithInStore(queryParams);

    //Assert Response is not Null
    assertThat(homepageResponse.getData()).isNotNull();

    //Assert Delivery Now Card details
    assertThat(homepageResponse.getData().getItems()).extracting(HomepageComponents::getType).contains("DeliveryNowCard");

    if (eligibility.equalsIgnoreCase("Eligible")) {
      assertThat(homepageResponse.getData().getItems()).filteredOn(type -> type.getType().contains("DeliveryNowCard")).extracting("data").extracting("deliveryNowStatus").first().isEqualTo("Open");
      assertThat(homepageResponse.getData().getItems()).filteredOn(type -> type.getType().contains("DeliveryNowCard")).extracting("data").extracting("status").first().isEqualTo("ClosingSoon");
      assertThat(homepageResponse.getData().getItems()).filteredOn(type -> type.getType().contains("DeliveryNowCard")).extracting("data").extracting("isEligible").first().isEqualTo("true");
    }
    else {
      assertThat(homepageResponse.getData().getItems()).filteredOn(type -> type.getType().contains("DeliveryNowCard")).extracting("data").extracting("deliveryNowStatus").first().isEqualTo("Ineligible");
      assertThat(homepageResponse.getData().getItems()).filteredOn(type -> type.getType().contains("DeliveryNowCard")).extracting("data").extracting("status").first().isEqualTo("Ineligible");
      assertThat(homepageResponse.getData().getItems()).filteredOn(type -> type.getType().contains("DeliveryNowCard")).extracting("data").extracting("isEligible").first().isEqualTo("false");
    }

  }

  @Then("^I make a request to Homepage in IN-STORE mode with store id \"([^\"]*)\" and verify the DeliveryNowCard for \"([^\"]*)\" address$")
  public void iMakeARequestToHomepageInINSTOREModeWithStoreIdWithAndVerifyTheDeliveryNowCardForAddress(String storeId, String eligibility) throws Throwable {
    Map<String, String> queryParams = new HashMap<String, String>();
    queryParams.put("store", storeId);
    HomepageResponse homepageResponse = iRetrieveHomepageWithInStore(queryParams);

    //Assert Response is not Null
    assertThat(homepageResponse.getData()).isNotNull();

    //Assert Delivery Now Card details
    assertThat(homepageResponse.getData().getItems()).extracting(HomepageComponents::getType).contains("DeliveryNowCard");

    if (eligibility.equalsIgnoreCase("Eligible")) {
      assertThat(homepageResponse.getData().getItems()).filteredOn(type -> type.getType().contains("DeliveryNowCard")).extracting("data").extracting("deliveryNowStatus").first().isEqualTo("Open");
      assertThat(homepageResponse.getData().getItems()).filteredOn(type -> type.getType().contains("DeliveryNowCard")).extracting("data").extracting("status").first().isEqualTo("ClosingSoon");
      assertThat(homepageResponse.getData().getItems()).filteredOn(type -> type.getType().contains("DeliveryNowCard")).extracting("data").extracting("isEligible").first().isEqualTo("true");
    }
    else {
      assertThat(homepageResponse.getData().getItems()).filteredOn(type -> type.getType().contains("DeliveryNowCard")).extracting("data").extracting("deliveryNowStatus").first().isEqualTo("Ineligible");
      assertThat(homepageResponse.getData().getItems()).filteredOn(type -> type.getType().contains("DeliveryNowCard")).extracting("data").extracting("status").first().isEqualTo("Ineligible");
      assertThat(homepageResponse.getData().getItems()).filteredOn(type -> type.getType().contains("DeliveryNowCard")).extracting("data").extracting("isEligible").first().isEqualTo("false");
    }

  }

  @Then("^I make a request to Homepage in \"([^\"]*)\" and verify the DeliveryNowCard for \"([^\"]*)\" address$")
  public void iMakeARequestToHomepageInAndVerifyTheDeliveryNowCardForAddress(String shoppingMode, String eligibility) throws Throwable {
    Map<String, String> queryParams = new HashMap<String, String>();
    queryParams.put("mode", shoppingMode);
    HomepageResponse homepageResponse = iRetrieveHomepageWithInStore(queryParams);

    //Assert Response is not Null
    assertThat(homepageResponse.getData()).isNotNull();

    //Assert Delivery Now Card details
    assertThat(homepageResponse.getData().getItems()).extracting(HomepageComponents::getType).contains("DeliveryNowCard");

    if (eligibility.equalsIgnoreCase("Eligible")) {
      assertThat(homepageResponse.getData().getItems()).filteredOn(type -> type.getType().contains("DeliveryNowCard")).extracting("data").extracting("deliveryNowStatus").first().isEqualTo("Open");
      assertThat(homepageResponse.getData().getItems()).filteredOn(type -> type.getType().contains("DeliveryNowCard")).extracting("data").extracting("status").first().isEqualTo("ClosingSoon");
      assertThat(homepageResponse.getData().getItems()).filteredOn(type -> type.getType().contains("DeliveryNowCard")).extracting("data").extracting("isEligible").first().isEqualTo("true");
    }
    else {
      assertThat(homepageResponse.getData().getItems()).filteredOn(type -> type.getType().contains("DeliveryNowCard")).extracting("data").extracting("deliveryNowStatus").first().isEqualTo("Ineligible");
      assertThat(homepageResponse.getData().getItems()).filteredOn(type -> type.getType().contains("DeliveryNowCard")).extracting("data").extracting("status").first().isEqualTo("Ineligible");
      assertThat(homepageResponse.getData().getItems()).filteredOn(type -> type.getType().contains("DeliveryNowCard")).extracting("data").extracting("isEligible").first().isEqualTo("false");
    }

  }
}
