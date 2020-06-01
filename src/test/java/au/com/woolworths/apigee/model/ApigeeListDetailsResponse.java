package au.com.woolworths.apigee.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Arrays;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class ApigeeListDetailsResponse {
  private String id;
  private String text;
  private double quantity;
  private boolean checked;
  private String status;
  private String title;
  private long timestamp;
  private long lastUpdated;
  private int count;
  private int page;
  private Object nextPage;
  private ApigeeFreeTextItem[] freeTextItems;
  private ApigeeProductsInList[] products;

  @Override
  public String toString() {
    return "ApigeeListDetailsResponse{" +
            "id='" + id + '\'' +
            ", text='" + text + '\'' +
            ", quantity=" + quantity +
            ", checked=" + checked +
            ", status=" + status +
            ", title='" + title + '\'' +
            ", timestamp=" + timestamp +
            ", lastUpdated=" + lastUpdated +
            ", count=" + count +
            ", page=" + page +
            ", nextPage=" + nextPage +
            ", freeTextItems=" + Arrays.toString(freeTextItems) +
            ", products=" + Arrays.toString(products) +
            '}';
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public double getQuantity() {
    return quantity;
  }

  public void setQuantity(double quantity) {
    this.quantity = quantity;
  }

  public boolean isChecked() {
    return checked;
  }

  public void setChecked(boolean checked) {
    this.checked = checked;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public long getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(long timestamp) {
    this.timestamp = timestamp;
  }

  public ApigeeFreeTextItem[] getFreeTextItems() {
    return freeTextItems;
  }

  public void setFreeTextItems(ApigeeFreeTextItem[] freeTextItems) {
    this.freeTextItems = freeTextItems;
  }

  public ApigeeProductsInList[] getProducts() {
    return products;
  }

  public void setProducts(ApigeeProductsInList[] products) {
    this.products = products;
  }

  public long getLastUpdated() {
    return lastUpdated;
  }

  public void setLastUpdated(long lastUpdated) {
    this.lastUpdated = lastUpdated;
  }

  public int getCount() {
    return count;
  }

  public void setCount(int count) {
    this.count = count;
  }

  public int getPage() {
    return page;
  }

  public void setPage(int page) {
    this.page = page;
  }

  public Object getNextPage() {
    return nextPage;
  }

  public void setNextPage(Object nextPage) {
    this.nextPage = nextPage;
  }
}
