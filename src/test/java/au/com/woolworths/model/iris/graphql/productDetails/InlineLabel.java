package au.com.woolworths.model.iris.graphql.productDetails;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class InlineLabel {
  private String type;
  private String label;
  private Integer priority;
}
