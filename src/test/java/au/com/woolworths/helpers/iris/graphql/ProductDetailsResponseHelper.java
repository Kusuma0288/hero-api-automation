package au.com.woolworths.helpers.iris.graphql;

import java.util.Optional;
import java.util.logging.Logger;

import au.com.woolworths.helpers.common.BaseHelper;
import au.com.woolworths.model.iris.graphql.productDetails.Feed;
import au.com.woolworths.model.iris.graphql.productDetails.ProductDetailsResponse;
import static au.com.woolworths.helpers.iris.graphql.ProductsBySearchResponseHelper.*;
import org.testng.asserts.SoftAssert;

public class ProductDetailsResponseHelper extends BaseHelper {

  private final static Logger logger = Logger.getLogger("ProductDetailsResponseHelper.class");

  public enum Typename {
    PRODUCT_CARD("ProductCard"),
    PRODUCT_NUTRITION_INFO("ProductNutritionInfo"),
    PRODUCT_ORIGIN_AND_HEALTH_INFO("ProductOriginAndHealthInfo"),
    IMAGE_CONTENT("ImageContent"),
    FORMATTED_CONTENT("FormattedContent"),
    FORMATTED_BANNER("FormattedBanner"),
    PRODUCT_DISCLAIMER("ProductDisclaimer");

    private final String typename;

    Typename(String typename) {
      this.typename = typename;
    }

    public String get() {
      return typename;
    }
  }

  public enum ProductDetailsArgs {
    PRODUCT_ID("productId"),
    BARCODE("barcode"),
    STORE_ID("storeId");

    private final String arg;

    ProductDetailsArgs(String arg) {
      this.arg = arg;
    }

    public String get() {
      return arg;
    }
  }

  private static Optional<Feed> getFeed(ProductDetailsResponse productDetailsResponse, String typename) {
    return productDetailsResponse.getData().getProductDetails()
        .getFeed()
        .stream()
        .filter(feed -> feed.getTypename().equalsIgnoreCase(typename)).findFirst();
  }

  public static void assertProductDetails(ProductDetailsResponse productDetailsResponse) {
    SoftAssert softAssert = new SoftAssert();
    Optional<Feed> productCard = getFeed(productDetailsResponse, Typename.PRODUCT_CARD.get());
    if (sharedData.productIdSource.equals(ProductIdSource.RANDOM)) {
      softAssert.assertTrue(productCard.get().getName().replaceAll(" ", "")
              .equalsIgnoreCase(sharedData.availableProduct.getName().replaceAll(" ", "")),
          "ProductName on ProductDetails didn't match ProductName from ProductList");

      softAssert.assertTrue(productCard.get().getPrice().equals(sharedData.availableProduct.getPrice()),
          "Price on ProductDetails didn't match Price from ProductList");

      softAssert.assertTrue(productCard.get().getIsAvailable().equals(sharedData.availableProduct.getIsAvailable()),
          "Availability on ProductDetails didn't match Availability from ProductList");

      if (sharedData.inStoreId != null) {

        softAssert.assertTrue(productCard.get().getInStoreDetails().getLocationText()
                .equalsIgnoreCase(sharedData.availableProduct.getInStoreDetails().getLocationText()),
            "LocationText on ProductDetails didn't match LocationText from ProductList");

        softAssert.assertTrue(productCard.get().getInStoreDetails().getLocationType()
                .equalsIgnoreCase(sharedData.availableProduct.getInStoreDetails().getLocationType()),
            "LocationType on ProductDetails didn't match LocationType from ProductList");
      }

    } else {
      softAssert.assertNotNull(productCard.get().getName().trim(),
          "ProductName was found to be null");

      softAssert.assertNotNull(productCard.get().getPrice(),
          "Price was found to be null");

      softAssert.assertTrue(productCard.get().getIsAvailable(),
          "Availability was found to be false");

      if (sharedData.inStoreId != null) {
        softAssert.assertNotNull(productCard.get().getInStoreDetails(),
            "InStoreDetails was found to be null");

        softAssert.assertNotNull(productCard.get().getInStoreDetails().getLocationText(),
            "LocationText was found to be null");

        softAssert.assertNotNull(productCard.get().getInStoreDetails().getLocationType(),
            "LocationType was found to be null");
      }

    }

    Optional<Feed> productDisclaimer = getFeed(productDetailsResponse, Typename.PRODUCT_DISCLAIMER.get());

    softAssert.assertNotNull(productDisclaimer.get().getContent(),
        "Disclaimer text not found");

    softAssert.assertAll();
  }

}
