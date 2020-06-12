package au.com.woolworths.apigee.model;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApigeeProductsSpecial {
  private int page;
  private int count;
  ApigeeProducts[] products;
  ApigeeSortOptions[] sortoptions;
  private String nextpage;

  @Override
  public String toString() {
    return "ApigeeProducts{" +
        "page=" + page +
        "nextpage=" + nextpage +
        "count=" + count +
        "products=" + products +
        "sortoptions=" + sortoptions +
        +'}';
  }

  public int getPage() {
    return page;
  }

  public int getCount() {
    return count;
  }

  public ApigeeProducts[] getProducts() {
    return products;
  }

  public ApigeeSortOptions[] getSortoptions() {
    return sortoptions;
  }

  public void setPage(int page) {
    this.page = page;
  }

  public void setCount(int count) {
    this.count = count;
  }

  public void setProducts(ApigeeProducts[] products) {
    this.products = products;
  }

  public void setSortoptions(ApigeeSortOptions[] sortoptions) {
    this.sortoptions = sortoptions;
  }

  public String getNextpage() {
    return nextpage;
  }

  public void setNextpage(String nextpage) {
    this.nextpage = nextpage;
  }
}
