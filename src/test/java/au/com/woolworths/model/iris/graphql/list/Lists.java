package au.com.woolworths.model.iris.graphql.list;

import com.fasterxml.jackson.annotation.JsonInclude;

@lombok.Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Lists {
  private int id;
  private String title;
  private String color;
  private String referenceId;
  private double timestamp;
  private int count;
  private int listId;
  private ProductItems[] productItems;
  private FreeTextItems[] freeTextItems;
  private FreeTextItems[] createdFreeTextItems;
  private ProductItems[] createdProductItems;
  private FreeTextItems[] editedFreeTextItems;
  private ProductItems[] editedProductItems;
  private ProductItems[] deletedItems;
}