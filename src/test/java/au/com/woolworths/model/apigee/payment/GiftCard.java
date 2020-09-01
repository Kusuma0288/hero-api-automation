package au.com.woolworths.model.apigee.payment;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class GiftCard {
  private List<String> allowedBins;
  private String serviceStatus;
  private boolean pinAlwaysRequired;
}

