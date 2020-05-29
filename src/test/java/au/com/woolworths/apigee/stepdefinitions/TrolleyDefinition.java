package au.com.woolworths.apigee.stepdefinitions;

import au.com.woolworths.apigee.context.ApigeeApplicationContext;
import au.com.woolworths.apigee.helpers.SearchHelper;
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


import java.util.*;
import java.util.logging.Logger;

public class TrolleyDefinition extends TrolleyHelper {
        private final static Logger logger = Logger.getLogger("TrolleyDefinition.class");
        private ApigeeSharedData sharedData;
        private ApigeeContainer picoContainer;
        private SearchHelper searchHelper = new SearchHelper();
        private double expectedTotalPrice = 0;
        private List<String> productNames = new ArrayList();

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

    @When("^I add some items to the V3 trolley for (.*)$")
    public void iAddSomeItemsToTheV3Trolley(String mode) throws Throwable {
        Map<String, Integer> productsToAdd = new HashMap<>();

        productsToAdd.put("milk", 2);
        productsToAdd.put("bread", 1);
        productsToAdd.put("pasta", 2);

        for (String product : productsToAdd.keySet()) {
            ApigeeV3SearchResponse v3SearchResponse = searchHelper.getProductItems(product, mode, sharedData.accessToken);
            sharedData.searchProductResponse = v3SearchResponse;

            List<String> stockCodes =  new ArrayList<String>();

            for (int i = 0; i < v3SearchResponse.getProducts().length; i++) {
                if (v3SearchResponse.getProducts()[i].getIs().isRanged()) {
                    stockCodes.add(v3SearchResponse.getProducts()[i].getArticle().replaceFirst("^0+(?!$)", ""));
                }
                if (stockCodes.size() == 1) {
                    break;
                }
            }

            // Update the productNames list with the products that have been added so we can verify later
            String updatedProductName = v3SearchResponse.getProducts()[0].getDescription();
            productNames.add(updatedProductName);

            // Update the expected price so we can verify later
            expectedTotalPrice = expectedTotalPrice + (productsToAdd.get(product) * v3SearchResponse.getProducts()[0].getInstoreprice().getPriceGst());
            addStockCodesToTheV3Trolley(stockCodes, productsToAdd.get(product), true,sharedData.accessToken);
        }
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

    @Then("^I remove (.*) product from V3 trolley and verify it is deleted$")
    public void i_remove_product_from_V3_trolley_and_verify_it_is_deleted(int itemsToBeDeleted) throws Throwable {
    	
    	TrolleyV3Response trolleyResponse = retriveV3Trolley(sharedData.accessToken); //Get the latest items from trolley.
    	int prodCountBefore = trolleyResponse.getTrolley().getTotalProducts(); //Check if the trolley is null
    	Assert.assertTrue("Trolley is null. There are no products in Trolley for Deletion",prodCountBefore!=0);
    	/*  
		 * Go through the list of items to be deleted. Ignores the number, if the provided count of items to be deleted is more than available items.
		 *  for e.g.  if available items is 5 and user has provided 6 for deletion. It will ignore 6th item and will delete only 5 items.
	 	 */
    	for(int i=0;i<itemsToBeDeleted &&i<trolleyResponse.getTrolley().getTotalProducts();i++) 
    	{  
       		trolleyResponse = retriveV3Trolley(sharedData.accessToken); //Get the latest items from trolley.
    		prodCountBefore = trolleyResponse.getTrolley().getTotalProducts();	
    		String stockCode = trolleyResponse.getTrolley().getTrolleyitemsListResp().get(0).getArticle();
    		trolleyResponse = delStockCodesFromV3Trolley(stockCode,sharedData.accessToken);
    		int prodCountAfter = trolleyResponse.getTrolley().getTotalProducts(); 
    		for (int j = 0; j < trolleyResponse.getTrolley().getTotalProducts(); j++) 
    		{
                //Verify the deleted item is not present in trolley
                Assert.assertTrue( "StockCode "+stockCode+" not deleted from trolley. Error in deletion",!trolleyResponse.getTrolley().getTrolleyitemsListResp().get(j).getArticle().equals(stockCode));
 
    		}
    		//Verify item count after  deletion
        	Assert.assertTrue("Error in item deletion",prodCountAfter == (prodCountBefore-1));	
    		
    	}
    	
    }
        
    @Then("^I remove (.*) product from V2 trolley and verify it is deleted$")
    public void i_remove_product_from_V2_trolley_and_verify_it_is_deleted(int itemsToBeDeleted) throws Throwable {
    
    	TrolleyV2Response trolleyResponse = retriveV2Trolley(sharedData.accessToken); //Get the latest items from trolley. 	
    	int prodCountBefore = trolleyResponse.getTotalproducts(); //Check if the trolley is null
    	Assert.assertTrue("Trolley is null. There are no products in Trolley for Deletion",prodCountBefore!=0);
    	/*
    	 *  Go through the list of items to be deleted. Ignores the number, if the provided count of items to be deleted is more than available items.
		 *	for e.g.  if available items is 5 and user has provided 6 for deletion. It will ignore 6th item and will delete only 5 items.
	 	 */
    	for(int i=0;i<itemsToBeDeleted && i<trolleyResponse.getTotalproducts();i++) 
    	{  
    		trolleyResponse = retriveV2Trolley(sharedData.accessToken); //Get the latest items from trolley.
    		prodCountBefore = trolleyResponse.getTotalproducts();	
    		String stockCode=trolleyResponse.getItems().get(0).getArticle();
    		trolleyResponse = delStockCodesFromV2Trolley(stockCode,sharedData.accessToken);
    		int prodCountAfter = trolleyResponse.getTotalproducts();
    		for (int j = 0; j < trolleyResponse.getTotalproducts(); j++) 
    		{
            //Verify the deleted item is not present in trolley
                Assert.assertTrue( "StockCode "+stockCode+" not deleted from trolley. Error in deletion",!trolleyResponse.getItems().get(j).getArticle().equals(stockCode));
               
    		}
    		//Verify item count after  deletion
        	Assert.assertTrue("Error in item deletion",prodCountAfter == (prodCountBefore-1));	
    		
    	}
    	
    }

    @Then("^I should be able to successfully view all the items in my V3 trolley$")
    public void canViewAllItemsInTrolley() throws Throwable{
        TrolleyV3Response trolleyResponse = retriveV3Trolley(sharedData.accessToken);

        Assert.assertTrue("Error in Trolley Results",trolleyResponse.getResults().getTrolley().getHttpStatusCode() == 200);
        Assert.assertTrue("The number of products in the trolley is incorrect",trolleyResponse.getTrolley().getTotalProducts() == productNames.size());
        Assert.assertTrue("Subtotal price is not correct", trolleyResponse.getTrolley().getSubtotalInclDelivery() == (expectedTotalPrice + trolleyResponse.getTrolley().getDeliveryFee()));

        // Reverse the list first
        Collections.reverse(productNames);

        // Verify all the products are correct
        for (String productName : productNames) {
            String description = trolleyResponse.getTrolley().getTrolleyitemsListResp().get(productNames.indexOf(productName)).getDescription();

            Assert.assertTrue("Product description is not correct (Expected - " + productName + ", but got - " + description, productName.equals(description));
        }
    }
}
