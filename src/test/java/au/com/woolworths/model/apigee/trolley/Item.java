package au.com.woolworths.model.apigee.trolley;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class Item {
  private Object article;
  private int itemquantityintrolley;
  private String allowsubstitution;
  private String comment;
}
