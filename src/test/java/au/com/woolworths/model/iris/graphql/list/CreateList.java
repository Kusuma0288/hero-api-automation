package au.com.woolworths.model.iris.graphql.list;
import com.fasterxml.jackson.annotation.JsonInclude;

@lombok.Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CreateList {
  private int id;
  private String title;
  private String color;
  private double timestamp;
  private int count;
  private FreeTextItems[] freeTextItems;
  private ProductItems[] productItems;
}

