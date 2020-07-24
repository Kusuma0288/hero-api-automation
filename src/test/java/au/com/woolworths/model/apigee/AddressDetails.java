package au.com.woolworths.model.apigee;

import lombok.Data;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data public class AddressDetails {
  private String id;
  private String text;
  private boolean isprimary;
  private String postalcode;
  private String street1;
  private String street2;
  private String suburbid;
  private String suburbname;
  private boolean ispartner;
  private String partnerbranchid;
  // Getter Methods

  // Setter Methods
}
