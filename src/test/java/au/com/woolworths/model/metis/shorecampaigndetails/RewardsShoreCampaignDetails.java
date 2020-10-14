package au.com.woolworths.model.metis.shorecampaigndetails;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class RewardsShoreCampaignDetails {
  private Footer footer;
  private Header header;
  private List<Product> products;
}
