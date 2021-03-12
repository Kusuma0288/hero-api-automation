package au.com.woolworths.model.scango.menu;

import au.com.woolworths.model.scango.scanitems.Images;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class LineItem {
  private String linenumber;
  private Images images;
  private String description;
  private String producttype;
  private Integer quantity;
  private ReceiptIs is;
  private Integer discountamount;
  private List<Object> matchedpromotions = null;
  private String promotiontype;
  private Integer saleprice;
  private Integer unitprice;
  private Integer originalunitprice;
  private String uom;
  private List<Object> offers = null;
}
