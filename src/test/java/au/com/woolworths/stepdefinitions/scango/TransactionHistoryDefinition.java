package au.com.woolworths.stepdefinitions.scango;

import au.com.woolworths.helpers.scango.TransactionHistoryHelper;
import au.com.woolworths.model.scango.menu.TransactionHistoryResponse;
import cucumber.api.java.en.Then;
import org.testng.Assert;

public class TransactionHistoryDefinition extends TransactionHistoryHelper {


  @Then("^I verify transaction is available in the Transaction History API$")
  public void verifyTransactionAvailableInTransactionHistoryAPI() throws Throwable {
    TransactionHistoryResponse transactionHistoryResponse = iCallTransactionHistory();
    sharedData.responseStatusCode = transactionHistoryResponse.getStatusCode();
    Assert.assertTrue(sharedData.responseStatusCode.contains("200"), "Connection not successful::" + sharedData.responseStatusCode);
  }
}