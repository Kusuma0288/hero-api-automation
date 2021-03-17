package au.com.woolworths.stepdefinitions.iris.graphql;

import au.com.woolworths.graphql.parser.GraphqlParser;
import au.com.woolworths.helpers.common.BaseHelper;
import au.com.woolworths.helpers.iris.graphql.GraphqlHelper;
import au.com.woolworths.helpers.iris.graphql.ProductCategoriesResponseHelper;
import au.com.woolworths.model.iris.graphql.productCategories.ProductCategoriesResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import cucumber.api.java.en.When;
import junit.framework.Assert;

import java.io.InputStream;

public class ProductCategoriesDefinition extends BaseHelper {

    ObjectMapper mapper = new ObjectMapper();
    public GraphqlHelper graphqlHelper = new GraphqlHelper();

    @When("^user requests for \"([^\"]*)\" sub-categories for store: \"([^\"]*)\"$")
    public void getAvailableProductCategories(String categoryType, String storeId) throws Throwable {
        if (sharedData.productCategories == null) {
            // First call
            sharedData.productCategories = getAvailableProductCategoriesNextLevel(categoryType, "", storeId);
        } else {
            sharedData.productCategories = getAvailableProductCategoriesNextLevel(categoryType, sharedData.productCategories, storeId);
        }
    }

    private String getAvailableProductCategoriesNextLevel(String categoryType, String SubCategory, String StoreID) throws Throwable {
        InputStream iStream = ProductCategoriesDefinition.class.getResourceAsStream("/gqlQueries/iris/productCategories.graphql");
        variables.put(ProductCategoriesResponseHelper.ProductsByCategoriesArgs.CATEGORY_ID.get(), SubCategory);
        variables.put(ProductCategoriesResponseHelper.ProductsByCategoriesArgs.CATEGORIES_TYPE.get(), categoryType);
        variables.put(ProductCategoriesResponseHelper.ProductsByCategoriesArgs.STORE_ID.get(), StoreID);
        String productsByCategoriesQuery = GraphqlParser.parseGraphql(iStream, variables);
        String productsByCategoriesResponseString = graphqlHelper.postGraphqlQuery(productsByCategoriesQuery);
        ProductCategoriesResponse productCategoriesResponse = mapper.readValue(productsByCategoriesResponseString, ProductCategoriesResponse.class);

        Assert.assertNotNull(productCategoriesResponse.getData().getProductCategories().getCategories().get(0).getTitle());
        Assert.assertNotNull(productCategoriesResponse.getData().getProductCategories().getCategories().get(0).getIsFinalLevel());
        Assert.assertNotNull(productCategoriesResponse.getData().getProductCategories().getCategories().get(0).getCategoryId());

        if (productCategoriesResponse.getData().getProductCategories().getCategories().get(0).getIsFinalLevel()) {
            return null;
        } else return productCategoriesResponse.getData().getProductCategories().getCategories().get(0).getCategoryId();
    }
}