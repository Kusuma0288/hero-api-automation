package au.com.woolworths.model.iris.graphql.productList;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class InStoreDetails {
  private String locationText;
  private String locationType;
}