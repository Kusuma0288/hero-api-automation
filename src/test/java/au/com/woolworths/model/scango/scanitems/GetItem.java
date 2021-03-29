package au.com.woolworths.model.scango.scanitems;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)

@JsonIgnoreProperties(ignoreUnknown = true) //TODO fix it
@Data
public class GetItem {

  private String linenumber;
  private String status;
  private String producttype;
  private String articlenumber;
  private String eannumber;
  private String name;
  private Integer quantity;
  private Integer defaultqty;
  private Integer incrementalquantity;
  private Integer supplyLimit;
  private String description;
  private String updated;
  private Integer size;
  private String measure;
  private String tareCode;
  private Double listprice;
  private Double saleprice;
  private String intervention_reason = null;
  private Integer promotion_price;
  private Integer discountamount;
  private List<Object> matchedpromotions = null;
  private List<Object> missedpromotions = null;
  private String promotiontype;
  private String updatedby;
  private Is is;
  private Instoreprice instoreprice;
  private Object interventiondetails;
  private Images images;
  private Boolean skip_bag_check;
  private String hierarchy_value = null;
}
