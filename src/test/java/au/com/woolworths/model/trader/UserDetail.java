package au.com.woolworths.model.trader;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class UserDetail {
  private final String firstName;
  private final String lastName;
  private final String emailAddress;
  private final String password;
  private final String mobilePhone;
  private final String dateOfBirth;
  private final boolean isBusinessShopper;
  private final boolean emailProductsAndServices;
  private final boolean smsProductsServicesAndPromotions;
  private boolean agreeToTsAndCs;
  private final String campaignName;

}
