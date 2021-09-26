package supervillain.herokuapp.com.model.user;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)

@Data
public class CreateUserResponse {

  private String status;
  private String message;
}
