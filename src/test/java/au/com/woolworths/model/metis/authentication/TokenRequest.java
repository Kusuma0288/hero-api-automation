package au.com.woolworths.model.metis.authentication;

import lombok.Data;

@Data
public class TokenRequest {
  private String refreshToken;
}