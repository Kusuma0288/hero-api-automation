package au.com.woolworths.apigee.model;

import lombok.Data;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data public class ApigeeInStores {

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

  // Setter Methods
}
