package au.com.woolworths.apigee.model;

import lombok.Data;

import com.fasterxml.jackson.annotation.JsonProperty;

@Data public class AddProductsToListRequest {
  private String articleId;
  private int quantity;
  private boolean checked;
  private long timestamp;
  private long lastsynced;

}
