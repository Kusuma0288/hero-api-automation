package au.com.woolworths.model.apigee.payment;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data public class iFrameRequest {
  private Authentication authentication;
  private boolean save;
  private boolean primary;
  private boolean verify;
  private boolean isGuest;
  private Item item;

  @Data public static class Authentication{
    private Credentials[] credentials;

    @Data public static class Credentials{
       private String type;
       private String sessionID;
    }
  }
   @Data public static class Item{
     private String type;
     private int priority;
     private ItemFields[] itemFields;
     @Data public static class ItemFields {
       private String name;
       private String data;
     }
  }
}
