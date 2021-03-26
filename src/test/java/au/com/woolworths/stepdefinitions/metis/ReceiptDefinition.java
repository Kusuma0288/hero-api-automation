package au.com.woolworths.stepdefinitions.metis;

import au.com.woolworths.graphql.parser.GraphqlParser;
import au.com.woolworths.helpers.metis.ReceiptHelper;
import au.com.woolworths.model.metis.receipt.*;
import au.com.woolworths.utils.TestProperties;
import au.com.woolworths.utils.Utilities;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import junit.framework.Assert;

import java.io.InputStream;
import java.util.List;
import java.util.logging.Logger;

public class ReceiptDefinition extends ReceiptHelper {

  private final static Logger logger = Logger.getLogger("ReceiptDefinition.class");
  private ReceiptDetailsResponse receiptDetailsResponse;

  @And("^the user selects the receipt with a \"([^\"]*)\"$")
  public void theUserSelectsTheReceiptWithA(String receiptId) throws Throwable {
    InputStream iStream = ReceiptDefinition.class.getResourceAsStream("/gqlQueries/metis/queries/rewards/receiptDetails.graphql");
    ObjectNode variables = new ObjectMapper().createObjectNode();

    variables.put("receiptId", TestProperties.get(receiptId));
    String graphqlQuery = GraphqlParser.parseGraphql(iStream, variables);
    receiptDetailsResponse = iRetrieveEreceipt(graphqlQuery);
  }

  @Then("^the user should be able see a BWS coupon in his receipt$")
  public void theUserShouldBeAbleSeeABWSCouponInHisReceipt() {
    List<Detail> details = receiptDetailsResponse.getData().getReceiptDetails().getDetails();
    boolean containsBWSCoupon;
    Detail BWSDetails = null;

    for (Detail detail : details) {
      containsBWSCoupon = detail.getHeaderImageUrl() != null && detail.getHeaderImageUrl().contains("bws");
      if (containsBWSCoupon) {
        BWSDetails = detail;
        break;
      }
    }

    Assert.assertNotNull("No BWS coupon was returned", BWSDetails);
    Assert.assertTrue("Section 1st title is missing for BWS ", BWSDetails.getSections().get(0).getSectionTitle().contains("BWS"));
    Assert.assertTrue("Section 2nd title is missing for BWS ", BWSDetails.getSections().get(1).getSectionTitle().contains("AND/OR"));
    Assert.assertTrue("Footer is missing for BWS Coupon ", BWSDetails.getFooter().contains("BWS"));
    Assert.assertTrue("Footer is missing over 18 text", BWSDetails.getFooter().contains("Over 18's"));
    Assert.assertTrue("Barcode for BWS is not numeric", Utilities.isNumeric(BWSDetails.getBarcode().getValue()));
    Assert.assertTrue("Barcode for BWS does not have type code 128", BWSDetails.getBarcode().getType().contains("128"));
    logger.info("BWS coupon was returned");
  }

}