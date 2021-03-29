package au.com.woolworths.model.scango.kiosk;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class KioskPaymentinfo {
  private Double amountpurchase;
  private String responsecode;
  private String responsetext;
  private String authcode;
  private String stan;
  private String rrn;
  private String tokenizedpan;
  private String bin;
  private String txnpaidtime;
  private String txnreference;
  private String providerCode;
  private String cardType;
  private String cardSuffix;
  private String scheme;
  private String instrumentType;
  private String terminalId;
  private String txnType;
  private String merchantid;
}
