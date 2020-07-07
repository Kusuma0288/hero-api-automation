package au.com.woolworths.apigee.model;

import au.com.woolworths.apigee.model.Products.ApigeeProductsInList;
import lombok.Data;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
@Data public class ApigeeListDetailsResponse {
  private String id;
  private String text;
  private double quantity;
  private boolean checked;
  private String status;
  private String title;
  private long timestamp;
  private long lastUpdated;
  private int count;
  private int page;
  private Object nextPage;
  private ApigeeFreeTextItem[] freeTextItems;
  private ApigeeProductsInList[] products;
}
