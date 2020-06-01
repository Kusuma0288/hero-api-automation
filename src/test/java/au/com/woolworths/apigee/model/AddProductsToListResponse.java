package au.com.woolworths.apigee.model;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class AddProductsToListResponse {
  private AddProdListDetailsResponse update;
  private Object changes;

  @Override
  public String toString() {
    return "AddProductsToListResponse{" +
            "update='" + update + '\'' +
            "changes='" + changes + '\'' +
            '}';
  }

  public AddProdListDetailsResponse getUpdate() {
    return update;
  }

  public void setUpdate(AddProdListDetailsResponse update) {
    this.update = update;
  }

  public Object getChanges() {
    return changes;
  }

  public void setChanges(Object changes) {
    this.changes = changes;
  }
}
