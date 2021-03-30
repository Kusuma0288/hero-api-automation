package au.com.woolworths.model.apigee.products;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
@Data
public class ProductsInList {
  private long id;
  private long articleId;
  private double quantity;
  private long timestamp;
  private boolean checked;
  private String description;
  private String referenceId;
}
