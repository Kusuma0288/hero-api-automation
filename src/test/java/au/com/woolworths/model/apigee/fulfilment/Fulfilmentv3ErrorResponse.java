package au.com.woolworths.model.apigee.fulfilment;

import lombok.Data;

@Data
public class Fulfilmentv3ErrorResponse {
  private int httpStatusCode;
  private String errorCode;
  private String errorMessage;
  private Object errorDetail;
}
