package au.com.woolworths.model.metis.activity;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data

public class Items {
  private String displayDate;
  private String description;
  private Message message;
  private String displayValue;
  private String iconUrl;
  private Object receipt;
  private String transactionType;
}