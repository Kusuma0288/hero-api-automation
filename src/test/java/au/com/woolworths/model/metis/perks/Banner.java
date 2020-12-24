package au.com.woolworths.model.metis.perks;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class Banner {
  private String bannerImageUrl;
  private Content content;
  private Link link;
}
