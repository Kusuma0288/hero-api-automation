package au.com.woolworths.apigee.model;

import lombok.Data;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Arrays;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
@Data public class AddProdListDetailsResponse {
  private long id;
  private long articleId;
  private double quantity;
  private boolean checked;
  private long timestamp;
  private String status;

}
