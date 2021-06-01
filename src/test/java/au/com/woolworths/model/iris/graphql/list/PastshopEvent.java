package au.com.woolworths.model.iris.graphql.list;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.List;

@lombok.Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PastshopEvent {
  private List<Items> Items;
  private int totalItemCount;
  private int nextPage;
}