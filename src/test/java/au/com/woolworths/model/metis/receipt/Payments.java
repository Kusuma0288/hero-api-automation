package au.com.woolworths.model.metis.receipt;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class Payments {
  private List<Details> details;
  private String amount;
  private String description;
  private String iconUrl;
  private String altText;
}
