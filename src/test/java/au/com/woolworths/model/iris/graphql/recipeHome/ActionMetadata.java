
package au.com.woolworths.model.iris.graphql.recipeHome;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ActionMetadata {
  private String __typename;
  private String source;
  private List <String> tags;
}