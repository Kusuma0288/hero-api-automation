package au.com.woolworths.model.iris.graphql.list;

import com.fasterxml.jackson.annotation.JsonInclude;

@lombok.Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SyncLists {
  private Lists[] createdLists;
  private Lists[] updatedLists;
  private String[] deletedLists;
}