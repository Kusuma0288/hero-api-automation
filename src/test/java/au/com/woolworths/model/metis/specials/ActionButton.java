package au.com.woolworths.model.metis.specials;

import au.com.woolworths.helpers.metis.deserializers.specials.ActionButtonDeserializer;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@JsonDeserialize(using = ActionButtonDeserializer.class)
public class ActionButton {

  private String title;
  private String url;
  private String type;

  public ActionButton(String title, String url, String type) {
    this.title = title;
    this.url = url;
    this.type = type;
  }
}
