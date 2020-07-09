package au.com.woolworths.model.apigee.trolley;

import lombok.Data;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data public class Item {
  private Object article;
  private int itemquantityintrolley;
  private String allowsubstitution;
  private String comment;
}
