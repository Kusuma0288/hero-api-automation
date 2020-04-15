package au.com.woolworths.apigee.stepdefinitions;

import au.com.woolworths.apigee.context.ApigeeApplicationContext;
import au.com.woolworths.apigee.helpers.TrolleyHelper;
import au.com.woolworths.apigee.model.ApigeeSearchInStore;
import au.com.woolworths.apigee.model.ApigeeV3SearchResponse;
import au.com.woolworths.apigee.model.TrolleyV2Response;
import au.com.woolworths.apigee.model.TrolleyV3Response;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import junit.framework.Assert;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

public class TrolleyDefinition extends TrolleyHelper {
        private final static Logger logger = Logger.getLogger("TrolleyDefinition.class");
        private ApigeeSharedData sharedData;
        private ApigeeContainer picoContainer;

        public TrolleyDefinition(ApigeeContainer container) {
            this.sharedData = ApigeeApplicationContext.getSharedData();
            this.picoContainer = container;
        }

        @When("^I add the (.*) available products with (.*) each from the store to the V3 trolley$")
        public void i_add_the_available_products_from_the_store_to_the_V3_trolley(int availableProducts, int quantity) throws Throwable {
            ApigeeV3SearchResponse searchResponse = sharedData.searchProductResponse;
            List<String> stockCodes =  new ArrayList<String>();
            for (int i=0;i<searchResponse.getProducts().length;i++) {
                if (searchResponse.getProducts()[i].getIs().isRanged()) {
                    stockCodes.add(searchResponse.getProducts()[i].getArticle().replaceFirst("^0+(?!$)", ""));
                }
                if (stockCodes.size() == availableProducts) {
                    break;
                }
            }
            Assert.assertTrue("There are no products available in Store",stockCodes.size()!=0);
            TrolleyV3Response trolleyResponse = addStockCodesToTheV3Trolley(stockCodes, quantity, true,sharedData.accessToken);
            Assert.assertTrue("Error in Order adding to the trolley",trolleyResponse.getResults().getOrder().getHttpStatusCode() ==200);
            Assert.assertTrue("Error in Trolley Results",trolleyResponse.getResults().getTrolley().getHttpStatusCode() ==200);

        }

    @When("^I add the (.*) available products with (.*) each from the store to the V2 trolley$")
    public void i_add_the_available_products_from_the_store_to_the_V2_trolley(int availableProducts, int quantity) throws Throwable {
        ApigeeV3SearchResponse searchResponse = sharedData.searchProductResponse;
        List<String> stockCodes =  new ArrayList<String>();
        for (int i=0;i<searchResponse.getProducts().length;i++) {
            if (searchResponse.getProducts()[i].getIs().isRanged()) {
                stockCodes.add(searchResponse.getProducts()[i].getArticle().replaceFirst("^0+(?!$)", ""));
            }
            if (stockCodes.size() == availableProducts) {
                break;
            }
        }
        Assert.assertTrue("There are no products available in Store",stockCodes.size()!=0);
        TrolleyV2Response trolleyResponse = addStockCodesToTheV2Trolley(stockCodes, quantity, true,sharedData.accessToken);
        Assert.assertTrue("Products is not added as expected:"+availableProducts,trolleyResponse.getTotalproducts() == availableProducts);

    }

    @And("^I clear the trolley$")
    public void i_clear_the_trolley() throws Throwable{
        TrolleyV2Response trolleyResponse=clearTrolley(sharedData.accessToken);

        Assert.assertEquals("Some items are there in trolley", 0,trolleyResponse.getTotaltrolleyprice());
    }

    @And("^I add the stockcode with quantity \"([^\"]*)\" to trolley$")
    public void iAddStockcodeWithSpecifiedQuantity(int quantity) throws Throwable{
        TrolleyV2Response trolleyResponse = addStockCodesToTheV2Trolley(sharedData.stockCode, quantity, true,sharedData.accessToken);

        //Assert if product has been added
        Assert.assertTrue("Products is not added as expected and trolley product count:" + trolleyResponse.getTotalproducts()
                ,trolleyResponse.getTotalproducts()>0);
    }




}
