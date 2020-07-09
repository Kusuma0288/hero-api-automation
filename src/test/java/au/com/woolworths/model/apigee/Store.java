package au.com.woolworths.model.apigee;

import lombok.Data;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data public class Store {
  private String Id;
  private double AddressId;
  private String AddressText, Text;
  private String Name;
}
