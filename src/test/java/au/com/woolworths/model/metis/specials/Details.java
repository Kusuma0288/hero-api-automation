package au.com.woolworths.model.metis.specials;

import au.com.woolworths.helpers.metis.deserializers.specials.DetailsDeserializers;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonDeserialize(using = DetailsDeserializers.class)
@Data
public class Details {

  private String name;
  private String partnerId;
  private String iconUrl;
  private String subtitle;
  private String type;

  public Details(String name, String partnerId, String iconUrl, String subtitle, String type) {
    this.name = name;
    this.partnerId = partnerId;
    this.iconUrl = iconUrl;
    this.subtitle = subtitle;
    this.type = type;
  }
}
