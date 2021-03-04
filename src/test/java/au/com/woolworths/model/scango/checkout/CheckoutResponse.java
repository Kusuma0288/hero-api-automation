package au.com.woolworths.model.scango.checkout;

import au.com.woolworths.model.scango.scanitems.Discounts;
import au.com.woolworths.model.scango.scanitems.Item;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class CheckoutResponse {
  private String cartid;
  private String lastupdated;
  private String status;
  private String reason;
  private String storeid;
  private Double merchanttotal;
  private Double totalprice;
  private Double totalgst;
  private Integer savings;
  private Integer totalquantity;
  private String rewardcardnumber;
  private Integer rewardsredeemed;
  private Integer rewardsaccrued;
  private Integer intervtnprice;
  private Integer intervtnquantity;
  private Integer totalsavings;
  private Double balancedue;
  private Is is;
  private Discounts discounts;
  private List<Object> totaldiscounts = null;
  private Integer paymentattempt;
  private List<Item> items = null;
  private List<Object> notifications = null;
  private List<Object> coupons = null;
  private List<Object> offers = null;
  private List<Object> pendingpromotions = null;
  private String checkouttitle;
  private String checkoutmessage;
  private String statusCode;
}
