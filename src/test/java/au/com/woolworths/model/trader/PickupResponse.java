package au.com.woolworths.model.trader;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class PickupResponse {
  private String DeliveryMethod;
  private int AddressId;
  private String Description;
  private String AddressText;
  private String Street1;
  private String Street2;
  private String Suburb;
  private String Area;
  private int SuburbID;
  private int AreaId;
  private int PostCode;
  private Boolean IsPrimary;
  private String PostalAddressID;
  private int Distance;
  private String StoreNumber;
  private Object TradingHours;
  private String Phone;
  private List<String> Facilities;
  private String CustomerFriendlyAddress;
  private String Latitude;
  private String Longitude;
  private String Division;
  private Boolean IsDriveUp;
  private String State;
  private String FulfilmentMethod;

  @Override
  public String toString() {
    return "PickupResponse{" +
        "DeliveryMethod='" + DeliveryMethod + '\'' +
        ", AddressId='" + AddressId + '\'' +
        ", Description='" + Description + '\'' +
        ", AddressText='" + AddressText + '\'' +
        ", Street1='" + Street1 + '\'' +
        ", Street2='" + Street2 + '\'' +
        ", Suburb='" + Suburb + '\'' +
        ", Area='" + Area + '\'' +
        ", SuburbID='" + SuburbID + '\'' +
        ", AreaId='" + AreaId + '\'' +
        ", PostCode='" + PostCode + '\'' +
        ", IsPrimary='" + IsPrimary + '\'' +
        ", PostalAddressID='" + PostalAddressID + '\'' +
        ", Distance='" + Distance + '\'' +
        ", StoreNumber='" + StoreNumber + '\'' +
        ", TradingHours='" + TradingHours + '\'' +
        ", Phone='" + Phone + '\'' +
        ", Facilities='" + Facilities + '\'' +
        ", CustomerFriendlyAddress='" + CustomerFriendlyAddress + '\'' +
        ", Latitude='" + Latitude + '\'' +
        ", Longitude='" + Longitude + '\'' +
        ", Division='" + Division + '\'' +
        ", IsDriveUp='" + IsDriveUp + '\'' +
        ", State='" + State + '\'' +
        ", FulfilmentMethod='" + FulfilmentMethod + '\'' +
        '}';

  }

  //[{,"CustomerFriendlyAddress":"125 James Cook Drive  Kings Langley","Latitude":"-33.74207899","Longitude":"150.922711","Division":"SUPERMARKETS","IsDriveUp":false,"State":"NSW"}]

  public String getDeliveryMethod() {
    return DeliveryMethod;
  }

  public void setDeliveryMethod(String deliveryMethod) {
    DeliveryMethod = deliveryMethod;
  }

  public int getAddressId() {
    return AddressId;
  }

  public void setAddressId(int addressId) {
    AddressId = addressId;
  }

  public String getDescription() {
    return Description;
  }

  public void setDescription(String deliveryMethod) {
    Description = deliveryMethod;
  }

  public String getAddressText() {
    return AddressText;
  }

  public void setAddressText(String addressText) {
    AddressText = addressText;
  }

  public String getStreet1() {
    return Street1;
  }

  public void setStreet1(String street1) {
    Street1 = street1;
  }

  public String getStreet2() {
    return Street2;
  }

  public void setStreet2(String street2) {
    Street2 = street2;
  }

  public String getSuburb() {
    return Suburb;
  }

  public void setSuburb(String suburb) {
    Suburb = suburb;
  }

  public String getArea() {
    return Area;
  }

  public void setArea(String area) {
    Area = area;
  }

  public int getSuburbID() {
    return SuburbID;
  }

  public void setSuburbID(int suburbID) {
    SuburbID = suburbID;
  }

  public int getAreaId() {
    return AreaId;
  }

  public void setAreaId(int areaId) {
    AreaId = areaId;
  }

  public int getPostCode() {
    return PostCode;
  }

  public void setPostCode(int postCode) {
    PostCode = postCode;
  }

  @JsonProperty("IsPrimary")
  public Boolean getIsPrimary() {
    return IsPrimary;
  }

  public void setIsPrimary(Boolean isPrimary) {
    IsPrimary = isPrimary;
  }

  public String getPostalAddressID() {
    return PostalAddressID;
  }

  public void setPostalAddressID(String postalAddressID) {
    PostalAddressID = postalAddressID;
  }

  public int getDistance() {
    return Distance;
  }

  public void setDistance(int distance) {
    Distance = distance;
  }

  public String getStoreNumber() {
    return StoreNumber;
  }

  public void setStoreNumber(String storeNumber) {
    StoreNumber = storeNumber;
  }

  public Object getTradingHours() {
    return TradingHours;
  }

  public void setTradingHours(Object tradingHours) {
    TradingHours = tradingHours;
  }

  public String getPhone() {
    return Phone;
  }

  public void setPhone(String phone) {
    Phone = phone;
  }

  public List<String> getFacilities() {
    return Facilities;
  }

  public void setFacilities(List<String> facilities) {
    Facilities = facilities;
  }

  public String getCustomerFriendlyAddress() {
    return CustomerFriendlyAddress;
  }

  public void setCustomerFriendlyAddress(String customerFriendlyAddress) {
    CustomerFriendlyAddress = customerFriendlyAddress;
  }

  public String getLatitude() {
    return Latitude;
  }

  public void setLatitude(String latitude) {
    Latitude = latitude;
  }

  public String getLongitude() {
    return Longitude;
  }

  public void setLongitude(String longitude) {
    Longitude = longitude;
  }

  public String getDivision() {
    return Division;
  }

  public void setDivision(String division) {
    Division = division;
  }

  public Boolean getIsDriveUp() {
    return IsDriveUp;
  }

  public void setIsDriveUp(Boolean isDriveUp) {
    IsDriveUp = isDriveUp;
  }

  public String getState() {
    return State;
  }

  public void setState(String state) {
    State = state;
  }

  public String getFulfilmentMethod() {
    return FulfilmentMethod;
  }

  public void setFulfilmentMethod(String fulfilmentMethod) {
    FulfilmentMethod = fulfilmentMethod;
  }

}