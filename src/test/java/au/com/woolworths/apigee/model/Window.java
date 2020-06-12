package au.com.woolworths.apigee.model;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)

public class Window {
  private int Id;
  private String DisplayDate;
  private String DisplayTime;
  private String DisplayDeliveryFee;
  private String StartTime;
  private String EndTime;
  private Boolean IsExpress;

  @Override
  public String toString() {
    return "Window{" +
            "Id=" + Id +
            ", DisplayDate='" + DisplayDate + '\'' +
            ", DisplayTime='" + DisplayTime + '\'' +
            ", DisplayDeliveryFee='" + DisplayDeliveryFee + '\'' +
            ", StartTime='" + StartTime + '\'' +
            ", EndTime='" + EndTime + '\'' +
            ", IsExpress=" + IsExpress +
            '}';
  }

  public int getId() {
    return Id;
  }

  public void setId(int id) {
    Id = id;
  }

  public String getDisplayDate() {
    return DisplayDate;
  }

  public void setDisplayDate(String displayDate) {
    DisplayDate = displayDate;
  }

  public String getDisplayTime() {
    return DisplayTime;
  }

  public void setDisplayTime(String displayTime) {
    DisplayTime = displayTime;
  }

  public String getDisplayDeliveryFee() {
    return DisplayDeliveryFee;
  }

  public void setDisplayDeliveryFee(String displayDeliveryFee) {
    DisplayDeliveryFee = displayDeliveryFee;
  }

  public String getStartTime() {
    return StartTime;
  }

  public void setStartTime(String startTime) {
    StartTime = startTime;
  }

  public String getEndTime() {
    return EndTime;
  }

  public void setEndTime(String endTime) {
    EndTime = endTime;
  }

  public Boolean getIsExpress() {
    return IsExpress;
  }

  public void setIsExpress(Boolean isExpress) {
    IsExpress = isExpress;
  }
}
