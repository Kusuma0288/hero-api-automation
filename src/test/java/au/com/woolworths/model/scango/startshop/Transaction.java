package au.com.woolworths.model.scango.startshop;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class Transaction {
  private String id;
  private Integer seqnumber;
  private String date;
  private String txntime;
  private String txndatetime;
  private String datestring;
  private String time;
}
