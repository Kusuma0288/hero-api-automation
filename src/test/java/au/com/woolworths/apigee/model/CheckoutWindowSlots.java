package au.com.woolworths.apigee.model;
import com.fasterxml.jackson.annotation.JsonInclude;

public class CheckoutWindowSlots {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private int Id;
    private String StartTime;
    private String EndTime;
    private String Status;
    private String StatusText;
    private int BasePrice;
    private int Discount;
    private int SalePrice;
    private String DisplayPrice;
    private boolean IsAvailable;
    private boolean IsReserved;
    private String Duration;


    @Override
    public String toString() {
        return "CheckoutWindowItemsResponse {" +
                "  Id=" + Id +
                ", StartTime=" + StartTime +
                ", EndTime=" + EndTime +
                ", Status=" + Status +
                ", StatusText=" + StatusText +
                ", BasePrice=" + BasePrice +
                ", Discount=" + Discount +
                ", SalePrice=" + SalePrice +
                ", DisplayPrice= " + DisplayPrice +
                ", IsAvailable=" + IsAvailable +
                ", IsReserved=" + IsReserved +
                ", Duration=" + Duration +
                '}';
    }
    public int getId() {return Id; }
    public void setId(int id) { Id = id; }

    public String getStartTime() {return StartTime; }
    public void setStartTime(String startTime) { StartTime = startTime; }

    public String getEndTime() {return EndTime; }
    public void setEndTime(String endTime) { EndTime = endTime; }

    public String getStatus() {return Status; }
    public void setStatus(String status) { Status = status; }

    public String getStatusText() {return StatusText; }
    public void setStatusText(String statusText) { StatusText = statusText; }

    public int getBasePrice() {return BasePrice; }
    public void setBasePrice(int basePrice) { BasePrice = basePrice; }

    public int getDiscount() {return Discount; }
    public void setDiscount(int discount) { Discount = discount; }

    public int getSalePrice() {return SalePrice; }
    public void setSalePrice(int salePrice) { SalePrice = salePrice; }

    public String getDisplayPrice() {return DisplayPrice; }
    public void setDisplayPrice(String displayPrice) { DisplayPrice = displayPrice; }

    public boolean getIsAvailable() {return IsAvailable; }
    public void setIsAvailable(boolean isAvailable) { IsAvailable = isAvailable; }

    public boolean getIsReserved() {return IsReserved; }
    public void setIsReserved(boolean isReserved) { IsReserved = isReserved; }

    public String getDuration() {return Duration; }
    public void setDuration(String duration) { Duration = duration; }
}
