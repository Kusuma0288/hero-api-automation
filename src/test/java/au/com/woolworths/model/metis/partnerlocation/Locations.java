package au.com.woolworths.model.metis.partnerlocation;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class Locations {
  private LocationsData data;
  private String type;
}
