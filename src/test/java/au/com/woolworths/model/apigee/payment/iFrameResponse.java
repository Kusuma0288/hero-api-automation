package au.com.woolworths.model.apigee.payment;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data public class iFrameResponse {
  public Status status;
  public PaymentInstrument paymentInstrument;
  public String stepUpToken;
  public FraudResponse fraudResponse;
  public String itemId;

  @Data public static class Status{
    public String responseText;
    public String responseCode;
    public String auditID;
    public long txnTime;
    public Object error;
    public Object esResponse;
  }

  @Data public static class PaymentInstrument{
    public String itemId;
    public String paymentToken;
    public String status;
    public long created;
    public String bin;
    public String suffix;
    public String expiryMonth;
    public String expiryYear;
    public String nickname;
    public String scheme;
  }

  @Data public static class FraudResponse{
    public Object fraudClientId;
    public Object fraudReasonCd;
    public Object fraudDecision;
  }
}
