package au.com.woolworths.model.iris.graphql.productList;

import lombok.EqualsAndHashCode;

@lombok.Data
@EqualsAndHashCode
public class InlineLabel {
  private String type;
  private String label;
  private Integer priority;
}