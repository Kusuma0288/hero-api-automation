package au.com.woolworths.model.scango.payment;

import au.com.woolworths.model.scango.scanitems.Discounts;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class PaymentResponse {
  private String cartid;
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
  private Integer totalsavings;
  private String checkouttitle;
  private String checkoutmessage;
  private Double balancedue;
  private List<Object> notifications = null;
  private List<Object> coupons = null;
  private List<Object> offers = null;
  private Discounts discounts;
  private List<PaymentInfo> paymentInfo = null;
  private String statusCode;
}
