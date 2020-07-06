package au.com.woolworths.apigee.model;

import lombok.Data;

@Data public class SignUpRequest {
  private String firstname = "Naeem";
  private String lastname = "Raza";
  private String emailaddress;
  private String password = "123456";
  private String dateofbirth;
  private String mobilephone = "0421000000";
  private String emailproductsandservices = "true";
  private String smsproductsservicesandpromotions = "true";
  private boolean agreetotsandcs = true;
}
