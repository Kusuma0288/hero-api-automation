package au.com.woolworths.model.metis.receipt;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class ReceiptDetails {
  private List<Detail> details;
  private Download download;
}
