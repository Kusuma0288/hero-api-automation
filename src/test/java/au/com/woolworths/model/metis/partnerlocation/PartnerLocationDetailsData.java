package au.com.woolworths.model.metis.partnerlocation;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class PartnerLocationDetailsData {
  private List<Locations> locations = null;
}
