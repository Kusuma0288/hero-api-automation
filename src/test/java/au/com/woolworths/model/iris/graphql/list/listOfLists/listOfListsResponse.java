package au.com.woolworths.model.iris.graphql.list.listOfLists;
import com.fasterxml.jackson.annotation.JsonInclude;

@lombok.Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class listOfListsResponse {
  private Data data;
}