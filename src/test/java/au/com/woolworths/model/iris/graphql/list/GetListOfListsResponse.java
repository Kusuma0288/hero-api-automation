package au.com.woolworths.model.iris.graphql.list;
import com.fasterxml.jackson.annotation.JsonInclude;

@lombok.Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GetListOfListsResponse {
  private Data data;
}