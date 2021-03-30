package au.com.woolworths.model.scango.scanitems;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true) //TODO fix it
@Data
public class Images {
  private List<String> _360 = null;
  private String thumbnail;
  private List<String> details = null;
}
