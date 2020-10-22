package au.com.woolworths.model.iris.graphql.changeMyOrder;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;


@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class Items {
  @JsonProperty("__typename")
  private String typename;
  private String status;
  private String label;
  private String title;
  private String time;
  private String day;
  private String checkIn15mins;
  private String checkInNow;
  private String banner;
  private String subtitle;
  private String actionTitle;
  private String icon;
  private String cardNumber;
  private List<Total> total;
  private List<SubTotal> subTotal;
  private List<Savings> savings;
  private List<PaymentMethods> paymentMethods;
  private List<Discounts> discounts;
  private List<au.com.woolworths.model.iris.graphql.changeMyOrder.ItemInHorizontalList.Items> items;   
}
