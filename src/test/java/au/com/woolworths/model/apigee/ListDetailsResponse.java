package au.com.woolworths.model.apigee;

import au.com.woolworths.model.apigee.products.ProductsInList;
import lombok.Data;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
@Data public class ListDetailsResponse {
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
  private FreeTextItem[] freeTextItems;
  private ProductsInList[] products;
}
