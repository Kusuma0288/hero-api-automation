package au.com.woolworths.model.iris.graphql.list;
import com.fasterxml.jackson.annotation.JsonInclude;

@lombok.Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductItems {
  private int id;
  private int productId;
  private long quantity;
  private double timestamp;
}