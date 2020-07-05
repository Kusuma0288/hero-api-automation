package au.com.woolworths.apigee.model;

import lombok.Data;

@Data public class AddressesV2ErrorResponse {
  private int httpStatusCode;
  private String errorCode;
  private String errorMessage;
  private Object errorDetail;
}
