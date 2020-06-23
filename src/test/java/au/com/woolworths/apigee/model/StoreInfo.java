package au.com.woolworths.apigee.model;

import lombok.Data;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)

@Data public class StoreInfo {
  private int Id;
  private int AddressId;
  private String Text;
  private String Name;
}
