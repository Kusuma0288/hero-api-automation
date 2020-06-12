package au.com.woolworths.apigee.model;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Item {
  private Object article;
  private int itemquantityintrolley;
  private String allowsubstitution;
  private String comment;

  @Override
  public String toString() {
    return "Item{" +
        "article=" + article +
        ", itemquantityintrolley=" + itemquantityintrolley +
        ", allowsubstitution='" + allowsubstitution + '\'' +
        ", comment='" + comment + '\'' +
        '}';
  }

  public Object getArticle() {
    return article;
  }

  public void setArticle(Object article) {
    this.article = article;
  }

  public int getItemquantityintrolley() {
    return itemquantityintrolley;
  }

  public void setItemquantityintrolley(int itemquantityintrolley) {
    this.itemquantityintrolley = itemquantityintrolley;
  }

  public String getAllowsubstitution() {
    return allowsubstitution;
  }

  public void setAllowsubstitution(String allowsubstitution) {
    this.allowsubstitution = allowsubstitution;
  }

  public String getComment() {
    return comment;
  }

  public void setComment(String comment) {
    this.comment = comment;
  }
}
