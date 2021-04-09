package au.com.woolworths.model.trader;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class Address {

  private int AddressId;
  private String AddressText;
  private boolean IsPrimary;
  private String PostalCode;
  private String Street1;
  private String Street2;
  private int SuburbId;
  private String SuburbName;
  private boolean IsPartner;
  private int PartnerBranchId;
  private int AreaId;
  private String AreaName;
  private String Country;
  private String Phone;
  private int StatusId;
  private int ShopperId;
  private int AreaDeliveryMethodId;
  private String DeliveryMethod;
  private boolean ShoppedWith;
  private String PostalAddressId;
  private String State;
  private String Latitude;
  private String Longitude;
  private String GuestToken;
  private String AddressProvider;
  private int FulfillmentStoreId;

}
