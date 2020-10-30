package au.com.woolworths.model.metis.offers;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class Filter {
  private String label;
  private Object message;
  private List<String> offerIds;
}
