package au.com.woolworths.model.scango.kiosk;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class KioskReasonCodeConfigResponse {
  private List<String> voidtransaction = null;
  private List<String> skipbagcheck = null;
  private List<String> intervnAgeMessage = null;
  private List<String> intervnAgeConfimation = null;
  private List<String> intervnQtyMessage = null;
  private List<String> intervnUnknownMessage = null;
  private List<String> intervnUnknownConfirmation = null;
  private List<String> intervnNotforsaleMessage = null;
  private List<String> intervnNotforsaleConfirmation = null;
  private String statusCode;
}
