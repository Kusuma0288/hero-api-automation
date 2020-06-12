package au.com.woolworths.apigee.model;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApigeeAddress {

  private String AmasID;
  private String Text;
  private String PostCode;

  @Override
  public String toString() {
    return "Address{" +
        "amasid=" + AmasID +
        ", text=" + Text +
        ", postcode=" + PostCode +
        '}';
  }

  public String getAmasID() {
    return AmasID;
  }

  public void setAmasID(String amasID) {
    AmasID = amasID;
  }

  public String getText() {
    return Text;
  }

  public void setText(String addressText) {
    Text = addressText;
  }

  public String getPostCode() {
    return PostCode;
  }

  public void setPostCode(String postCode) {
    PostCode = postCode;
  }
}
