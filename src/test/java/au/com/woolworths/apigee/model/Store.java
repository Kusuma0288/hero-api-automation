package au.com.woolworths.apigee.model;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Store {
  private String Id;
  private double AddressId;
  private String AddressText, Text;
  private String Name;

  @Override
  public String toString() {
    return "Store{" +
        "Id='" + Id + '\'' +
        ", AddressId=" + AddressId +
        ", AddressText='" + AddressText + '\'' +
        ", Text='" + Text + '\'' +
        ", Name='" + Name + '\'' +
        '}';
  }

  public String getId() {
    return Id;
  }

  public void setId(String id) {
    Id = id;
  }

  public double getAddressId() {
    return AddressId;
  }

  public void setAddressId(double addressId) {
    AddressId = addressId;
  }

  public String getAddressText() {
    return AddressText;
  }

  public void setAddressText(String addressText) {
    AddressText = addressText;
  }

  public String getText() {
    return Text;
  }

  public void setText(String text) {
    Text = text;
  }

  public String getName() {
    return Name;
  }

  public void setName(String name) {
    Name = name;
  }
}
