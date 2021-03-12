package au.com.woolworths.model.scango.kiosk;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class KioskDeleteCartResponse {
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
  private Object totalsavings;
  private String appId;
  private String checkouttitle;
  private String checkoutmessage;
  private List<Object> items = null;
  private List<Object> notifications = null;
  private List<Object> coupons = null;
  private List<Object> offers = null;
  private String statusCode;
}
