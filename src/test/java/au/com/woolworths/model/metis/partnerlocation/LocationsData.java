package au.com.woolworths.model.metis.partnerlocation;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data

public class LocationsData {
  private String partnerId;
  private String name;
  private String storeNo;
  private String division;
  private String address;
  private String distance;
  private Double latitude;
  private Double longitude;
  private String mapClusterColour;
  private String icon;
  private String badgeUrl;
  private String iconUrl;
}
