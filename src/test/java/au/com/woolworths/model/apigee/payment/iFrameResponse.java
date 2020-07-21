package au.com.woolworths.model.apigee.payment;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data public class iFrameResponse {
  private Status status;
  private Item item;
  private Object fraudResponse;

  @Data public static class Status {
    private String responseText;
    private String responseCode;
    private String auditID;
    private String messageId;
    private String txnTime;
    private String error;
    private Object esResponse;
  }

  @Data public static class Item {
    private String itemID;
    private String paymentToken;
    private String type;
    private Object[] itemFields;
    private String sequence;
    private boolean primary;
    private String createdOn;
    private String lastUpdated;
    private String lastUsed;
    private String requiresCvv;
    private String expired;
    private String allowed;
    private String status;
    private String action;
    private String updateUrl;
    private String stepUpToken;
  }

}
