package au.com.woolworths.model.helios.login;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class AuthCodeRequest {
  private String clientOS;
  private String emailOrCardNumber;
}
