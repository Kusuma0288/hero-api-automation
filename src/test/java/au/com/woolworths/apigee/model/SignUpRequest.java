package au.com.woolworths.apigee.model;

import lombok.Data;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
@Data public class SignUpRequest {
  private String firstname;
  private String lastname;
  private String emailaddress;
  private String password;
  private String dateofbirth;
  private String mobilephone;
  private String emailproductsandservices;
  private String smsproductsservicesandpromotions;
}
