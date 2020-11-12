package au.com.woolworths.model.metis.offers;

import au.com.woolworths.model.metis.message.Message;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class Filter {
  private String label;
  private Message message;
  private List<String> offerIds;
}
