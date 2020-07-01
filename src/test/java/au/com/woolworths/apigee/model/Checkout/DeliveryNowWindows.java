package au.com.woolworths.apigee.model.Checkout;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class DeliveryNowWindows {
  private String Title;
  private int Id;
  private String StartTime;
  private String SubTitle;
  private String DisplayPrice;
  private boolean IsReserved;
  private Object Info;

  @Override
  public String toString() {
    return "DeliveryNowWindows{" +
        "Title='" + Title + '\'' +
        ", Id=" + Id +
        ", StartTime='" + StartTime + '\'' +
        ", SubTitle='" + SubTitle + '\'' +
        ", DisplayPrice='" + DisplayPrice + '\'' +
        ", IsReserved=" + IsReserved +
        ", Info=" + Info +
        '}';
  }

  public String getTitle() {
    return Title;
  }

  public void setTitle(String title) {
    Title = title;
  }

  public int getId() {
    return Id;
  }

  public void setId(int id) {
    Id = id;
  }

  public String getStartTime() {
    return StartTime;
  }

  public void setStartTime(String startTime) {
    StartTime = startTime;
  }

  public String getSubTitle() {
    return SubTitle;
  }

  public void setSubTitle(String subTitle) {
    SubTitle = subTitle;
  }

  public String getDisplayPrice() {
    return DisplayPrice;
  }

  public void setDisplayPrice(String displayPrice) {
    DisplayPrice = displayPrice;
  }

  public Boolean getIsReserved() {
    return IsReserved;
  }

  public void setIsReserved(Boolean reserved) {
    IsReserved = reserved;
  }

  public Object getInfo() {
    return Info;
  }

  public void setInfo(Object info) {
    Info = info;
  }
}
