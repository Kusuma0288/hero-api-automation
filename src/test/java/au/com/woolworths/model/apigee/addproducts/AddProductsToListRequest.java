package au.com.woolworths.model.apigee.addproducts;

import lombok.Data;

@Data public class AddProductsToListRequest {
  private String articleId;
  private int quantity;
  private boolean checked;
  private long timestamp;
  private long lastsynced;

}
