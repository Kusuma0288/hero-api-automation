package au.com.woolworths.model.iris.graphql.list.listOfLists;
import au.com.woolworths.model.iris.graphql.list.SyncLists;
import com.fasterxml.jackson.annotation.JsonInclude;

@lombok.Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Data {
  private Lists[] lists;
}