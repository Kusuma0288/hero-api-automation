package au.com.woolworths.model.metis.specials;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class PartnersData {
  private Details details;
  private Disclaimer disclaimer;
  private String itemCountText;
  private List<Product> products;
  private Sorting sorting;
  private Message message;
}
