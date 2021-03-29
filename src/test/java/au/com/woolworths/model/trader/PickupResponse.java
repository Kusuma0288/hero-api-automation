package au.com.woolworths.model.trader;

import com.fasterxml.jackson.annotation.JsonInclude;
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

}