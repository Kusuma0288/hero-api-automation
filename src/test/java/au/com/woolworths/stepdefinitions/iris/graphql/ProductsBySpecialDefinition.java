package au.com.woolworths.stepdefinitions.iris.graphql;

import au.com.woolworths.helpers.common.BaseHelper;
import au.com.woolworths.helpers.iris.graphql.GraphqlHelper;
import au.com.woolworths.helpers.iris.graphql.SearchHelper;
import au.com.woolworths.model.iris.graphql.productList.ProductsByCategoryResponse;
import au.com.woolworths.model.iris.graphql.productList.ProductsBySpecialResponse;
import io.cucumber.java.en.Then;
import junit.framework.Assert;

import java.io.InputStream;

import static au.com.woolworths.graphql.parser.GraphqlParser.parseGraphql;
import static au.com.woolworths.helpers.iris.graphql.SpecialsHelper.Typename.*;

public class ProductsBySpecialDefinition extends BaseHelper {
  private final InputStream iStreamSpecials = this.getClass().getResourceAsStream("/gqlQueries/iris/productsBySpecialsGroup.graphql");
  private final InputStream iStreamSpecialsCategories = this.getClass().getResourceAsStream("/gqlQueries/iris/specialCategories.graphql");
  private final GraphqlHelper graphqlHelper = new GraphqlHelper();

  @Then("user visit Specials and applies sort option {string} and {string} specials group")
  public void getSortOptionForOnlineProductsBySpecials(String sortOption, String specialsGroup) throws Throwable {
    boolean sortNotMatching = false;
    variables.put(SearchHelper.Typename.SORT_OPTION.get(), sortOption);
    variables.put(SPECIALS_GROUP.get(), specialsGroup);
    String productsBySpecialsQuery = parseGraphql(iStreamSpecials, variables);
    String productsBySpecialResponseString = graphqlHelper.postGraphqlQuery(productsBySpecialsQuery);
    ProductsBySpecialResponse productsBySpecialResponse = mapper.readValue(productsBySpecialResponseString, ProductsBySpecialResponse.class);
    for (int i = 0; i <= productsBySpecialResponse.getData().getProductsBySpecialsGroup().getSortOptions().size(); i++) {
      if (productsBySpecialResponse.getData().getProductsBySpecialsGroup().getSortOptions().get(i).getKey().equals(sortOption)) {
        if (productsBySpecialResponse.getData().getProductsBySpecialsGroup().getSortOptions().get(i).getIsApplied().equals(true)) {
          sortNotMatching = true;
          break;
        }
      }
    }
    Assert.assertTrue(sortNotMatching);
  }

  @Then("user visit Specials Categories and applies sort option {string} and {string} as a category")
  public void getSortOptionForOnlineProductsBySpecialsCategories(String sortOption, String category) throws Throwable {
    boolean sortNotMatching = false;
    variables.put(IS_SPECIAL.get(), true);
    variables.put(CATEGORY_ID.get(), category);
    String productsBySpecialsCategoryQuery = parseGraphql(iStreamSpecialsCategories, variables);
    String productsBySpecialCategoryResponseString = graphqlHelper.postGraphqlQuery(productsBySpecialsCategoryQuery);
    ProductsByCategoryResponse productsBySpecialCategoryResponse = mapper.readValue(productsBySpecialCategoryResponseString, ProductsByCategoryResponse.class);
    for (int i = 0; i <= productsBySpecialCategoryResponse.getData().getProductsByCategory().getSortOptions().size(); i++) {
      if (productsBySpecialCategoryResponse.getData().getProductsByCategory().getSortOptions().get(i).getKey().equals(sortOption)) {
        if (productsBySpecialCategoryResponse.getData().getProductsByCategory().getSortOptions().get(i).getIsApplied().equals(true)) {
          sortNotMatching = true;
          break;
        }
      }
    }
    Assert.assertTrue(sortNotMatching);
  }
}
