package supervillain.herokuapp.com.model.user;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)

@Data
public class UserTokenVerificationResponse {
  private String app;
  private int app_id;
}



