package au.com.woolworths.apigee.model;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CheckoutResponse {
    private Object Order;
    private Object DeliveryPackagingPreferences;
    private CheckoutFulfilmentWindows[] FulfilmentWindows;
    private Object Results;


    @Override
    public String toString() {
        return "CheckoutResponse {" +
                "order=" + Order +
                ", deliveryPackagingPreferences=" + DeliveryPackagingPreferences +
                ", fulfilmentWindows=" + FulfilmentWindows +
                ", results=" + Results +'}';
    }

    public Object getOrder() {return Order; }
    public void setOrder(Object order) { Order = order; }

    public Object getDeliveryPackagingPreferences() {return DeliveryPackagingPreferences; }
    public void setDeliveryPackagingPreferences(Object deliveryPackagingPreferences) { DeliveryPackagingPreferences = deliveryPackagingPreferences; }

    public CheckoutFulfilmentWindows[] getFulfilmentWindows() {return FulfilmentWindows; }
    public void setFulfilmentWindows(CheckoutFulfilmentWindows[] fulfilmentWindows) { FulfilmentWindows = fulfilmentWindows; }

    public Object getResults() {return Results; }
    public void setResults(Object results) { Results = results; }
}
