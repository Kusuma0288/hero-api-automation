package au.com.woolworths.model.iris.graphql.productList;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class InlineLabel {
  private String type;
  private String label;
  private Integer priority;
}