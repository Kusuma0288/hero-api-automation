package au.com.woolworths.model.metis.specials;

import au.com.woolworths.helpers.metis.deserializers.specials.MessageDeserializer;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonDeserialize(using = MessageDeserializer.class)
@Data
public class Message {

  private String title;
  private String imageUrl;
  private ActionButton actionButton;
  private String type;

  public Message(String title, String imageUrl, ActionButton actionButton, String type) {
    this.title = title;
    this.imageUrl = imageUrl;
    this.actionButton = actionButton;
    this.type = type;
  }

}
