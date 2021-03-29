package au.com.woolworths.model.apigee.trolley;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class TrolleyItems {
  private List<Item> items;
  private boolean replaceTrolley;
}
