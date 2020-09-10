package au.com.woolworths.stepdefinitions.trader;

import au.com.woolworths.helpers.trader.ProductsHelper;
import au.com.woolworths.model.trader.GroupResponse;
import au.com.woolworths.model.trader.SpecialsGroupDetailsResponse;
import au.com.woolworths.model.trader.SpecialsGroupResponse;
import cucumber.api.java.en.When;
import org.testng.Assert;

import java.util.HashMap;
import java.util.logging.Logger;

public class ProductsDefinition extends ProductsHelper {
  private final static Logger logger = Logger.getLogger("ProductsDefinition.class");

  @When("^I get the list of specials groups and verify the product count and total record count of each group$")
  public void iGetSpecialsGroup() throws Throwable {

    SpecialsGroupResponse specialsGroupResponse = getSpecialsGroup();

    HashMap<String, Integer> groupCount = new HashMap<String, Integer>();

    for (int i = 0; i < 3 && i < specialsGroupResponse.getItems().length; i++) {

      GroupResponse item = specialsGroupResponse.getItems()[i];
      String id = item.getId();
      int productCount = item.getProductCount();
      groupCount.put(id, productCount);
      sharedData.specialGroupCount = groupCount;
      SpecialsGroupDetailsResponse specialsGroupDetailsResponse = new SpecialsGroupDetailsResponse();

      int productTotal = 0;

      for (int j = 0; j < (productCount / 50) + 1; j++) {
        specialsGroupDetailsResponse = iGetSpecialsGroupDetails(id, j + 1);
        productTotal = specialsGroupDetailsResponse.getBundles().length + productTotal;

      }
      Assert.assertEquals(productTotal, item.getProductCount(), "Product count of group: " + item.getId() + " does not equal");
      Assert.assertEquals(specialsGroupDetailsResponse.getTotalRecordCount(), item.getProductCount(), "Total Record count of group: " + item.getId() + " does not equal");
    }
  }

}
