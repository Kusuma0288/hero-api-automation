package au.com.woolworths.model.apigee.products;

import lombok.Data;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
@Data
public class AddProdListDetailsResponse {
  private long id;
  private long articleId;
  private double quantity;
  private boolean checked;
  private long timestamp;
  private String status;

}
