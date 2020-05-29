package au.com.woolworths.apigee.model;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class ApigeeLists {
  private String id;
  private String title;
  private String timestamp;
  private String url;
  private int productCount;

  @Override
  public String toString() {
    return "ApigeeLists{" +
            "id='" + id + '\'' +
            "title='" + title + '\'' +
            "timestamp='" + timestamp + '\'' +
            "url='" + url + '\'' +
            "productCount='" + productCount + '\'' +
            '}';
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

  public String getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(String timestamp) {
    this.timestamp = timestamp;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public int getProductCount() {
    return productCount;
  }

  public void setProductCount(int productCount) {
    this.productCount = productCount;
  }
}
