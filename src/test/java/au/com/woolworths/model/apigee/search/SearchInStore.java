package au.com.woolworths.model.apigee.search;

import au.com.woolworths.model.apigee.store.InStores;
import lombok.Data;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class SearchInStore {
  private InStores[] stores;
}
