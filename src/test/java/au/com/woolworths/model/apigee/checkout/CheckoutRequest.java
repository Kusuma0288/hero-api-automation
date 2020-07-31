package au.com.woolworths.model.apigee.checkout;

import lombok.Data;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)

@Data public class CheckoutRequest {
  private int window;
  private String date;
  private int packaging;
}