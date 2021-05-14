package au.com.woolworths.model.apigee.trolley;

import au.com.woolworths.model.apigee.search.InStorePrice;
import au.com.woolworths.model.common.Promotions;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class TrolleyItemsListResponse {
  Promotions promotions;
  private String name;
  private String article;
  private String description;
  private Object images;
  private String href;
  private int size;
  private String measure;
  private Object is;
  private String instorelocation;
  private boolean allowsubstitution;
  private String comment;
  private InStorePrice instoreprice;
  private Object footer;
  private int maxquantitylimit;
  private int defaultquantity;
  private int minquantitylimit;
  private int incrementalquantity;
  private int itemquantityintrolley;
  private int totalitemprice;
  private String supplementaryInfoTextAll;
  private boolean isrestricted;
  private boolean isdeliverypass;
  private boolean isavailable;
  private boolean ispmrestriction;
  private boolean isforcollection;
  private boolean isfordelivery;
  private Object supplementaryinfo;
  private String updated;
  private Object disclaimer;
  private String loyaltypoints;
  private boolean IsCreditsAvailable;
  private Object offers;
}
