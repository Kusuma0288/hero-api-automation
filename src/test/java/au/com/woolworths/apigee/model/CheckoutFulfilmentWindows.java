package au.com.woolworths.apigee.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sun.org.apache.xpath.internal.operations.Bool;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CheckoutFulfilmentWindows {

  private boolean IsReserved;
  private String StartsFrom;
  private String Date;
  private Boolean IsAvailable;
  private String DateText;
  private boolean IsEligibleForDeliverySaver;
  private CheckoutWindowItems Morning;
  private CheckoutWindowItems Afternoon;
  private CheckoutWindowItems Evening;
  private DeliveryNowWindows DeliveryNow;

  @Override
  public String toString() {
    return "CheckoutFulfilmentWindows{" +
            "IsReserved=" + IsReserved +
            ", StartsFrom='" + StartsFrom + '\'' +
            ", Date='" + Date + '\'' +
            ", IsAvailable=" + IsAvailable +
            ", DateText='" + DateText + '\'' +
            ", IsEligibleForDeliverySaver=" + IsEligibleForDeliverySaver +
            ", Morning=" + Morning +
            ", Afternoon=" + Afternoon +
            ", Evening=" + Evening +
            ", DeliveryNow=" + DeliveryNow +
            '}';
  }

  public boolean getIsReserved() {
    return IsReserved;
  }

  public void setIsReserved(boolean isReserved) {
    IsReserved = isReserved;
  }

  public String getStartsFrom() {
    return StartsFrom;
  }

  public void setStartsFrom(String startsFrom) {
    StartsFrom = startsFrom;
  }

  public String getDate() {
    return Date;
  }

  public void setDate(String date) {
    Date = date;
  }

  public Boolean getIsAvailable() {
    return IsAvailable;
  }

  public void setIsAvailable(Boolean isAvailable) {
    IsAvailable = isAvailable;
  }

  public String getDateText() {
    return DateText;
  }

  public void setDateText(String dateText) {
    DateText = dateText;
  }

  public boolean getIsEligibleForDeliverySaver() {
    return IsEligibleForDeliverySaver;
  }

  public void setIsEligibleForDeliverySaver(boolean isEligibleForDeliverySaver) {
    IsEligibleForDeliverySaver = isEligibleForDeliverySaver;
  }

  public CheckoutWindowItems getMorning() {
    return Morning;
  }

  public void setMorning(CheckoutWindowItems morning) {
    Morning = morning;
  }

  public CheckoutWindowItems getAfternoon() {
    return Afternoon;
  }

  public void setAfternoon(CheckoutWindowItems afternoon) {
    Afternoon = afternoon;
  }

  public CheckoutWindowItems getEvening() {
    return Evening;
  }

  public void setEvening(CheckoutWindowItems evening) {
    Evening = evening;
  }

  public DeliveryNowWindows getDeliveryNow() {
    return DeliveryNow;
  }

  public void setDeliveryNow(DeliveryNowWindows deliveryNow) {
    DeliveryNow = deliveryNow;
  }
}
