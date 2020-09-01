package au.com.woolworths.model.apigee.products;

import lombok.Data;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
@Data
public class ProductsInList {
  private long id;
  private long articleId;
  private double quantity;
  private long timestamp;
  private boolean checked;
  private String description;

}
