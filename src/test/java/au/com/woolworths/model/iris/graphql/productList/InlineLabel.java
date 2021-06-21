package au.com.woolworths.model.iris.graphql.productList;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;

@lombok.Data
@EqualsAndHashCode
@JsonInclude(JsonInclude.Include.NON_NULL)
public class InlineLabel {
  @JsonProperty("__typename")
  private String __typename;
  private String type;
  private String label;
  private Integer priority;
}