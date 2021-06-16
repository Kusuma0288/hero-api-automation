
package au.com.woolworths.model.iris.graphql.recipeHome;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Items {
  private String image;
  private String __typename;
  private String source;
  private String title;
  private String subtitle;
  private List <String> tags;
  private List <Items2> items;
  private String actionTitle;
  private List <ActionMetadata> actionMetadata;
  private String altText;
  private String textImageUrl;
  private String coverImageUrl;
}