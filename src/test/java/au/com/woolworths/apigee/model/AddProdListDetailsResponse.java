package au.com.woolworths.apigee.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Arrays;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class AddProdListDetailsResponse {
  private long id;
  private long articleId;
  private double quantity;
  private boolean checked;
  private long timestamp;
  private String status;

  @Override
  public String toString() {
    return "ApigeeListDetailsResponse{" +
            "id='" + id + '\'' +
            ", articleId='" + articleId + '\'' +
            ", quantity=" + quantity +
            ", checked=" + checked +
            ", status=" + status +
            ", timestamp=" + timestamp +

            '}';
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public long getArticleId() {
    return articleId;
  }

  public void setArticleId(long articleId) {
    this.articleId = articleId;
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

  public long getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(long timestamp) {
    this.timestamp = timestamp;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

}
