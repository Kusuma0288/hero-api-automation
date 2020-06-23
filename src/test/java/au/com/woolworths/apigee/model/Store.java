package au.com.woolworths.apigee.model;

import lombok.Data;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data public class Store {
  private String Id;
  private double AddressId;
  private String AddressText, Text;
  private String Name;
}
