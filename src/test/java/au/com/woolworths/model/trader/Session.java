package au.com.woolworths.model.trader;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class Session {

  private String DateOfBirth;
  private int AddressId;
  private int DeliveryAddressID;
  private String DeliveryAddressCity;
  private String DeliveryAddressCountry;
  private String DeliveryAddressStreet1;
  private String DeliveryAddressStreet2;
  private String DeliveryAddressSuburb;
  private String Suburb;
  private String StoreName;
  private String DeliveryInstructions;
  private String FulfilmentMethod;
  private String DeliveryMethod;
  private int FulfilmentStoreID;
  private String Email;
  private String FirstName;
  private String PrimaryPhone;
  private String SecondaryPhone;
  private String OtherPhone;
  private boolean IsServicable;
  private String LastName;
  private String MiddleInitial;
  private String RewardsCardNumber;
  private int ShopperID;
  private boolean TeamMember;
  private String Title;
  // Below fields for V2 Shopper Login Response
  private int ContextId;

}
