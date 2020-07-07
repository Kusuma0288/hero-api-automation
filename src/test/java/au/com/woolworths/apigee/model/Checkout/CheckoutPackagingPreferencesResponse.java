package au.com.woolworths.apigee.model.Checkout;

import lombok.Data;

@Data public class CheckoutPackagingPreferencesResponse {

  private int Id;
  private String Code;
  private String Name;
  private int Cost;
  private boolean IsSelected;
  private String Image;
  private String Description;
  private String FeeLabel;

}
