package au.com.woolworths.apigee.model;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class ApigeeGetListResponse {
  private ApigeeLists[] lists;

  @Override
  public String toString() {
    return "ApigeeGetLists{" +
            "lists='" + lists + '\'' +
            '}';
  }

  public ApigeeLists[] getLists() {
    return lists;
  }

  public void setLists(ApigeeLists[] lists) {
    this.lists = lists;
  }
}
