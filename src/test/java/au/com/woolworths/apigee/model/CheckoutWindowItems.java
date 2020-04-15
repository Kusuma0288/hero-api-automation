package au.com.woolworths.apigee.model;
import com.fasterxml.jackson.annotation.JsonInclude;

public class CheckoutWindowItems {
    @JsonInclude(JsonInclude.Include.NON_NULL)
        private String StartTimeRange;
        private CheckoutWindowSlots CheckoutWindowSlots;


        @Override
        public String toString() {
            return "CheckoutWindowItemsResponse {" +
                    "StartTimeRange=" + StartTimeRange +
                    ", Slots=" + CheckoutWindowSlots +'}';
        }

    public String getIsReserved() {return StartTimeRange; }
    public void setIsReserved(String startTimeRange) { StartTimeRange = startTimeRange; }

    public CheckoutWindowSlots getCheckoutWindowSlots() {return CheckoutWindowSlots; }
    public void setCheckoutWindowSlots(CheckoutWindowSlots checkoutWindowSlots) { CheckoutWindowSlots = checkoutWindowSlots; }
}
