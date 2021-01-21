package au.com.woolworths.model.metis.discover;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class Location {
  private String address;
  private String distance;
  private String division;
  private String icon;
  private Double latitude;
  private Double longitude;
  private String name;
  private String storeNo;
}
