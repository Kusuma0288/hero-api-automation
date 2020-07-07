package au.com.woolworths.apigee.model.Products;

import lombok.Data;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
@Data public class ApigeeProductsInList {
  private long id;
  private long articleId;
  private double quantity;
  private long timestamp;
  private boolean checked;
  private String description;

}
