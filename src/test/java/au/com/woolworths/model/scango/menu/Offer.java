package au.com.woolworths.model.scango.menu;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class Offer {
  private List<Object> triggeredlinenumbers = null;
  private String status;
  private Integer discountamount;
  private Integer quantity;
  private String promotiontype;
  private String promotiondescriptions;
  private String promotionrewardtype;
  private String datecreated;
  private String lastupdated;
  private Boolean ispromotionappliedinline;

}
