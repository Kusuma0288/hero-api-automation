package au.com.woolworths.model.metis.receipt;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class Barcode {
  private String value;
  private String type;
}
