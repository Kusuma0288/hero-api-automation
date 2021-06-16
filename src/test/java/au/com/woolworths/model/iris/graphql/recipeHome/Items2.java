
package au.com.woolworths.model.iris.graphql.recipeHome;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Items2 {
  private String __typename;
  private String source;
  private List<String> tags;
  private String title;
  private String image;
  private String id;
  private List <ActionMetadata> actionMetadata;
}