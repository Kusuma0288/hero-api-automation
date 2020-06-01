package au.com.woolworths.apigee.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Arrays;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApigeeStores {
  private String deliveryMethod;
  private String description;
  private String addressText;
  private String street1;
  private String street2;
  private String suburb;
  private String area;
  private Integer suburbID;
  private Integer areaId;
  private String postCode;
  private Boolean isPrimary;
  private Integer postalAddressID;
  private Double distance;
  private String storeNumber;
  private Object tradingHours;
  private String phone;
  private Object facilities;
  private String customerFriendlyAddress;
  private String latitude;
  private String longitude;
  private String division;
  private String state;
  private String fulfilmentMethod;
  private PickUpType[] pickUpType;

  @Override
  public String toString() {
    return "ApigeeStores{" +
            "deliveryMethod='" + deliveryMethod + '\'' +
            ", description='" + description + '\'' +
            ", addressText='" + addressText + '\'' +
            ", street1='" + street1 + '\'' +
            ", street2='" + street2 + '\'' +
            ", suburb='" + suburb + '\'' +
            ", area='" + area + '\'' +
            ", suburbID=" + suburbID +
            ", areaId=" + areaId +
            ", postCode='" + postCode + '\'' +
            ", isPrimary=" + isPrimary +
            ", postalAddressID=" + postalAddressID +
            ", distance=" + distance +
            ", storeNumber='" + storeNumber + '\'' +
            ", tradingHours=" + tradingHours +
            ", phone='" + phone + '\'' +
            ", facilities=" + facilities +
            ", customerFriendlyAddress='" + customerFriendlyAddress + '\'' +
            ", latitude='" + latitude + '\'' +
            ", longitude='" + longitude + '\'' +
            ", division='" + division + '\'' +
            ", state='" + state + '\'' +
            ", fulfilmentMethod='" + fulfilmentMethod + '\'' +
            ", pickUpType=" + Arrays.toString(pickUpType) +
            '}';
  }

  public String getDeliveryMethod() {
    return deliveryMethod;
  }

  public void setDeliveryMethod(String DeliveryMethod) {
    this.deliveryMethod = deliveryMethod;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getAddressText() {
    return addressText;
  }

  public void setAddressText(String addressText) {
    this.addressText = addressText;
  }

  public String getStreet1() {
    return street1;
  }

  public void setStreet1(String street1) {
    this.street1 = street1;
  }

  public String getStreet2() {
    return street2;
  }

  public void setStreet2(String street2) {
    this.street2 = street2;
  }

  public String getSuburb() {
    return suburb;
  }

  public void setSuburb(String suburb) {
    this.suburb = suburb;
  }

  public String getArea() {
    return area;
  }

  public void setArea(String suburb) {
    this.area = area;
  }

  public Integer getSuburbID() {
    return suburbID;
  }

  public void setSuburbID(Integer suburbID) {
    this.suburbID = suburbID;
  }

  public Integer getAreaId() {
    return areaId;
  }

  public void setAreaId(Integer areaId) {
    this.areaId = areaId;
  }

  public String getPostCode() {
    return postCode;
  }

  public void setPostCode(String postCode) {
    this.postCode = postCode;
  }

  public Boolean getIsPrimary() {
    return isPrimary;
  }

  public void setIsPrimary(Boolean isPrimary) {
    this.isPrimary = isPrimary;
  }

  public Integer getPostalAddressID() {
    return postalAddressID;
  }

  public void setPostalAddressID(Integer postalAddressID) {
    this.postalAddressID = postalAddressID;
  }

  public Double getDistance() {
    return distance;
  }

  public void setDistance(Double distance) {
    this.distance = distance;
  }

  public String getStoreNumber() {
    return storeNumber;
  }

  public void setStoreNumber(String distance) {
    this.storeNumber = storeNumber;
  }

  public Object getTradingHours() {
    return tradingHours;
  }

  public void setTradingHours(Object tradingHours) {
    this.tradingHours = tradingHours;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public Object getFacilities() {
    return facilities;
  }

  public void setFacilities(Object facilities) {
    this.facilities = facilities;
  }

  public String getCustomerFriendlyAddress() {
    return customerFriendlyAddress;
  }

  public void setCustomerFriendlyAddress(String customerFriendlyAddress) {
    this.customerFriendlyAddress = customerFriendlyAddress;
  }

  public String getLatitude() {
    return latitude;
  }

  public void setLatitude(String latitude) {
    this.latitude = latitude;
  }

  public String getLongitude() {
    return longitude;
  }

  public void setLongitude(String longitude) {
    this.longitude = longitude;
  }

  public String getDivision() {
    return division;
  }

  public void setDivision(String division) {
    this.division = division;
  }

  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }

  public String getFulfilmentMethod() {
    return fulfilmentMethod;
  }

  public void setFulfilmentMethod(String fulfilmentMethod) {
    this.fulfilmentMethod = fulfilmentMethod;
  }

  public PickUpType[] getPickUpType() {
    return pickUpType;
  }

  public void setPickUpType(PickUpType[] pickUpType) {
    this.pickUpType = pickUpType;
  }
}
