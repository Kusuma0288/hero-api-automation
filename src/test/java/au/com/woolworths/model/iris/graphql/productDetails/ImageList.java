package au.com.woolworths.model.iris.graphql.productDetails;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ImageList {
  @JsonProperty("__typename")
  private String typename;
  private String smallUrl;
  private String mediumUrl;
  private String largeUrl;
  private Object altText;
}
