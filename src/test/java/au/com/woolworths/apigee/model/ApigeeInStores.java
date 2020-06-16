package au.com.woolworths.apigee.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.ArrayList;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApigeeInStores {

  private String name;
  private String addressLine1;
  private String addressLine2;
  private String country;
  private String division;
  private String email;
  private String extra1;
  private String extra2;
  private String extra3;
  private String extra4;
  private String extra5;
  private String extra6;
  private String extra7;
  private String extra8;
  private String extra9;
  private String extra10;
  private String extra11;
  private String extra12;
  private String extra13;
  private String extra14;
  private String extra15;
  ArrayList<Object> facilities = new ArrayList<Object>();
  private String geoLevel;
  private String GMTZone;
  private Object images;
  private String manager;
  private String no;
  private String phone;
  private String postcode;
  private String state;
  private String suburb;
  private Object trading_hours;
  private float distance;
  private String latitude;
  private String longtitude;

  @Override
  public String toString() {
    return "InStore{" +
        "name=" + name +
        ", addressLine1=" + addressLine1 +
        ", addressLine2=" + addressLine2 +
        ", country=" + country +
        ", division=" + division +
        ", email=" + email +
        ", extra1=" + extra1 +
        ", extra2=" + extra2 +
        ", extra3=" + extra3 +
        ", extra4=" + extra4 +
        ", extra5=" + extra5 +
        ", extra6=" + extra6 +
        ", extra7=" + extra7 +
        ", extra8=" + extra8 +
        ", extra9=" + extra9 +
        ", extra10=" + extra10 +
        ", extra11=" + extra11 +
        ", extra12=" + extra12 +
        ", extra13=" + extra13 +
        ", extra14=" + extra14 +
        ", extra15=" + extra15 +
        ", facilities=" + facilities +
        ", geoLevel=" + geoLevel +
        ", GMTZone=" + GMTZone +
        ", images=" + images +
        ", manager=" + manager +
        ", no=" + no +
        ", phone=" + phone +
        ", postcode=" + postcode +
        ", state=" + state +
        ", suburb=" + suburb +
        ", trading_hours=" + trading_hours +
        ", distance=" + distance +
        ", latitude=" + latitude +
        ", longtitude=" + longtitude +
        '}';
  }

  public String getName() {
    return name;
  }

  public String getAddressLine1() {
    return addressLine1;
  }

  public String getAddressLine2() {
    return addressLine2;
  }

  public String getCountry() {
    return country;
  }

  public String getDivision() {
    return division;
  }

  public String getEmail() {
    return email;
  }

  public String getExtra1() {
    return extra1;
  }

  public String getExtra2() {
    return extra2;
  }

  public String getExtra3() {
    return extra3;
  }

  public String getExtra4() {
    return extra4;
  }

  public String getExtra5() {
    return extra5;
  }

  public String getExtra6() {
    return extra6;
  }

  public String getExtra7() {
    return extra7;
  }

  public String getExtra8() {
    return extra8;
  }

  public String getExtra9() {
    return extra9;
  }

  public String getExtra10() {
    return extra10;
  }

  public String getExtra11() {
    return extra11;
  }

  public String getExtra12() {
    return extra12;
  }

  public String getExtra13() {
    return extra13;
  }

  public String getExtra14() {
    return extra14;
  }

  public String getExtra15() {
    return extra15;
  }

  public String getGeoLevel() {
    return geoLevel;
  }

  public String getGMTZone() {
    return GMTZone;
  }

  public Object getImages() {
    return images;
  }

  public String getManager() {
    return manager;
  }

  public String getNo() {
    return no;
  }

  public String getPhone() {
    return phone;
  }

  public String getPostcode() {
    return postcode;
  }

  public String getState() {
    return state;
  }

  public String getSuburb() {
    return suburb;
  }

  public Object getTradingHours() {
    return trading_hours;
  }

  public float getDistance() {
    return distance;
  }

  public String getLatitude() {
    return latitude;
  }

  public String getLongtitude() {
    return longtitude;
  }

  public ArrayList<Object> getFacilities() {
    return facilities;
  }

  // Setter Methods

  public void setName(String name) {
    this.name = name;
  }

  public void setAddressLine1(String addressLine1) {
    this.addressLine1 = addressLine1;
  }

  public void setAddressLine2(String addressLine2) {
    this.addressLine2 = addressLine2;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public void setDivision(String division) {
    this.division = division;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public void setExtra1(String extra1) {
    this.extra1 = extra1;
  }

  public void setExtra2(String extra2) {
    this.extra2 = extra2;
  }

  public void setExtra3(String extra3) {
    this.extra3 = extra3;
  }

  public void setExtra4(String extra4) {
    this.extra4 = extra4;
  }

  public void setExtra5(String extra5) {
    this.extra5 = extra5;
  }

  public void setExtra6(String extra6) {
    this.extra6 = extra6;
  }

  public void setExtra7(String extra7) {
    this.extra7 = extra7;
  }

  public void setExtra8(String extra8) {
    this.extra8 = extra8;
  }

  public void setExtra9(String extra9) {
    this.extra9 = extra9;
  }

  public void setExtra10(String extra10) {
    this.extra10 = extra10;
  }

  public void setExtra11(String extra11) {
    this.extra11 = extra11;
  }

  public void setExtra12(String extra12) {
    this.extra12 = extra12;
  }

  public void setExtra13(String extra13) {
    this.extra13 = extra13;
  }

  public void setExtra14(String extra14) {
    this.extra14 = extra14;
  }

  public void setExtra15(String extra15) {
    this.extra15 = extra15;
  }

  public void setGeoLevel(String geoLevel) {
    this.geoLevel = geoLevel;
  }

  public void setGMTZone(String GMTZone) {
    this.GMTZone = GMTZone;
  }

  public void setImages(Object imagesObject) {
    this.images = imagesObject;
  }

  public void setManager(String manager) {
    this.manager = manager;
  }

  public void setNo(String no) {
    this.no = no;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public void setPostcode(String postcode) {
    this.postcode = postcode;
  }

  public void setState(String state) {
    this.state = state;
  }

  public void setSuburb(String suburb) {
    this.suburb = suburb;
  }

  public void setTradingHours(Object trading_hoursObject) {
    this.trading_hours = trading_hoursObject;
  }

  public void setDistance(float distance) {
    this.distance = distance;
  }

  public void setLatitude(String latitude) {
    this.latitude = latitude;
  }

  public void setLongtitude(String longtitude) {
    this.longtitude = longtitude;
  }

  public void setFacilities(ArrayList<Object> facilities) {
    this.facilities = facilities;
  }
}
