package au.com.woolworths.apigee.model.Trolley;

import au.com.woolworths.apigee.model.Products.InStorePrice;
import au.com.woolworths.apigee.model.SearchProducts.Promotions;
import lombok.Data;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data public class TrolleyItemsListResponse {
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
  Promotions promotions;
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

}
