package au.com.woolworths.model.scango.kiosk;

import au.com.woolworths.model.scango.menu.Offer;
import au.com.woolworths.model.scango.payment.PaymentInfo;
import au.com.woolworths.model.scango.scanitems.Item;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class KioskPaymentResponse {
  private String cartid;
  private String lastupdated;
  private String status;
  private String reason;
  private String storeid;
  private Integer merchanttotal;
  private Integer totalprice;
  private Double totalgst;
  private Integer savings;
  private Integer totalquantity;
  private String rewardcardnumber;
  private Integer rewardsredeemed;
  private Integer rewardsaccrued;
  private Integer intervtnprice;
  private Integer intervtnquantity;
  private Integer totalsavings;
  private String checkouttitle;
  private String checkoutmessage;
  private List<Item> items = null;
  private List<Object> notifications = null;
  private List<Object> coupons = null;
  private List<Offer> offers = null;
  private KioskIs is;
  private List<PaymentInfo> paymentInfo = null;
  private String statusCode;
}
