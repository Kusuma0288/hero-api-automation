package au.com.woolworths.model.apigee.authentication;

import lombok.Data;

@Data
public class SignUpRequest {
  private String firstname = "John";
  private String lastname = "Doe";
  private String emailaddress;
  private String password = "123456";
  private String dateofbirth;
  private String mobilephone = "0421000000";
  private String emailproductsandservices = "true";
  private String smsproductsservicesandpromotions = "true";
  private boolean agreetotsandcs = false;
}
