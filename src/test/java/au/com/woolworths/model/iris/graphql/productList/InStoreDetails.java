package au.com.woolworths.model.iris.graphql.productList;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class InStoreDetails {
  private String locationText;
  private String locationType;
}