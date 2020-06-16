package au.com.woolworths.apigee.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class SpecialsCategories {
  private String products_href;
  private String title;
  private int product_count;

  @Override
  public String toString() {
    return "categories{" +
        "products_href='" + products_href + '\'' +
        "title='" + title + '\'' +
        "product_count='" + product_count + '\'' +
        '}';
  }

  @JsonProperty("products_href")
  public String getProductsHref() {
    return products_href;
  }

  @JsonProperty("products_href")
  public void setProductsHref(String products_href) {
    this.products_href = products_href;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }
  @JsonProperty("product_count")
  public int getProductCount() {
    return product_count;
  }

  @JsonProperty("product_count")
  public void setProductCount(int product_count) {
    this.product_count = product_count;
  }
}