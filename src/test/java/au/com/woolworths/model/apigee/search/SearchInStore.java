package au.com.woolworths.model.apigee.search;

import au.com.woolworths.model.apigee.store.InStores;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class SearchInStore {
  private InStores[] stores;
}
