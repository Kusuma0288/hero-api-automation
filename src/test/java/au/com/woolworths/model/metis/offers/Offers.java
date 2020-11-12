package au.com.woolworths.model.metis.offers;

import au.com.woolworths.model.metis.message.Message;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class Offers {
  private List<Filter> filters;
  private List<Item> items;
  private Message message;
  private String title;
}
