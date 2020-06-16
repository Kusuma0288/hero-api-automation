package au.com.woolworths.apigee.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApigeeProductCategories {
  private String title;
  private Object images;
  private int category_order;
  private Object products_href;
  private int product_count;

  @Override
  public String toString() {
    return "ProductCategories{" +
        "title=" + title +
        "images=" + images +
        "category_order=" + category_order +
        "products_href=" + products_href +
        "product_count=" + product_count +
        +'}';
  }

  public String getTitle() {
    return title;
  }

  public Object getImages() {
    return images;
  }

  @JsonProperty("category_order")
  public int getCategoryOrder() {
    return category_order;
  }

  @JsonProperty("products_href")
  public Object getProductsHref() {
    return products_href;
  }

  @JsonProperty("product_count")
  public int getProductCount() {
    return product_count;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public void setImages(Object images) {
    this.images = images;
  }

  @JsonProperty("category_order")
  public void setCategoryOrder(int category_order) {
    this.category_order = category_order;
  }

  @JsonProperty("products_href")
  public void setProductsHref(Object products_href) {
    this.products_href = products_href;
  }

  @JsonProperty("product_count")
  public void setProductCount(int product_count) {
    this.product_count = product_count;
  }
}
