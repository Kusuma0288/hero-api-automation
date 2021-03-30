package au.com.woolworths.model.apigee.lists;

import au.com.woolworths.model.apigee.products.ProductsInList;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
@Data
public class ListDetailsResponse {
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
  private String color;
  private String referenceId;
}
