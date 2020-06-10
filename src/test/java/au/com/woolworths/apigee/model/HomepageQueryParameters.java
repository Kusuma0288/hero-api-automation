package au.com.woolworths.apigee.model;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class HomepageQueryParameters {

  private String groupId;

  @Override
  public String toString() {
    return "HomepageQueryParameters: {" +
            "groupId=" + groupId + "}";
  }

  public String getGroupId() {
    return groupId;
  }

  public void setGroupId(String groupId) {
    this.groupId = groupId;
  }

}