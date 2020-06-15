package au.com.woolworths.apigee.model;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class SignUpRequest {
  private String firstname;
  private String lastname;
  private String emailaddress;
  private String password;
  private String dateofbirth;
  private String mobilephone;
  private String emailproductsandservices;
  private String smsproductsservicesandpromotions;

  @Override
  public String toString() {
    return "SignUpRequest{" +
        "firstname='" + firstname + '\'' +
        ", lastname='" + lastname + '\'' +
        ", emailaddress='" + emailaddress + '\'' +
        ", password='" + password + '\'' +
        ", dateofbirth='" + dateofbirth + '\'' +
        ", mobilephone='" + mobilephone + '\'' +
        ", emailproductsandservices='" + emailproductsandservices + '\'' +
        ", smsproductsservicesandpromotions='" + smsproductsservicesandpromotions + '\'' +
        '}';
  }

  public String getFirstname() {
    return firstname;
  }

  public void setFirstname(String firstname) {
    this.firstname = firstname;
  }

  public String getLastname() {
    return lastname;
  }

  public void setLastname(String lastname) {
    this.lastname = lastname;
  }

  public String getEmailaddress() {
    return emailaddress;
  }

  public void setEmailaddress(String emailaddress) {
    this.emailaddress = emailaddress;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getDateofbirth() {
    return dateofbirth;
  }

  public void setDateofbirth(String dateofbirth) {
    this.dateofbirth = dateofbirth;
  }

  public String getMobilephone() {
    return mobilephone;
  }

  public void setMobilephone(String mobilephone) {
    this.mobilephone = mobilephone;
  }

  public String getEmailproductsandservices() {
    return emailproductsandservices;
  }

  public void setEmailproductsandservices(String emailproductsandservices) {
    this.emailproductsandservices = emailproductsandservices;
  }

  public String getSmsproductsservicesandpromotions() {
    return smsproductsservicesandpromotions;
  }

  public void setSmsproductsservicesandpromotions(String smsproductsservicesandpromotions) {
    this.smsproductsservicesandpromotions = smsproductsservicesandpromotions;
  }
}
