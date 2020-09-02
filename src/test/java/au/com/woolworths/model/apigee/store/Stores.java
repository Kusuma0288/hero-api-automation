package au.com.woolworths.model.apigee.store;

import au.com.woolworths.model.apigee.pickup.PickUpType;
import lombok.Data;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class Stores {
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
}
