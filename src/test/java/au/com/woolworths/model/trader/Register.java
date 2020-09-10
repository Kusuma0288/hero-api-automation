package au.com.woolworths.model.trader;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class Register {
  private String FirstName;
  private String LastName;
  private String EmailAddress;
  private String Password;
  private String MobilePhone;
  private String DateOfBirth;
  private boolean IsBusinessShopper;
  private boolean EmailProductsAndServices;
  private boolean SmsProductsServicesAndPromotions;
  private String CampaignName;
  private String GuestTrolleyToken;
  private DeliveryAddress DeliveryAddress;
  private boolean agreetotsandcs;

}
