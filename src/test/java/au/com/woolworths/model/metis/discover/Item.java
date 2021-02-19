package au.com.woolworths.model.metis.discover;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class Item {
  private List<Location> locations;
  private String title;
}