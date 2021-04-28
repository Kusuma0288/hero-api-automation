package au.com.woolworths.model.iris.graphql.list;
import com.fasterxml.jackson.annotation.JsonInclude;

@lombok.Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Data {
  private SyncLists syncLists;
  private Lists[] lists;
  private Lists list;
  private Lists syncListItems;
}