package au.com.woolworths.model.trader.product.productGroup;

import au.com.woolworths.model.trader.GroupProducts;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductsByProductGroup {
  private List<GroupProducts> products = new ArrayList<>();
  private Integer totalProductCount;
}