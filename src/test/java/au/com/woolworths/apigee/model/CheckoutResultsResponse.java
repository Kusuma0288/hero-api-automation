package au.com.woolworths.apigee.model;

public class CheckoutResultsResponse {
    private CheckoutResultsErrorResponse SetDeliveryWindow;
    private CheckoutResultsErrorResponse Order;
    private CheckoutResultsErrorResponse DeliveryPackagingPreferences;
    private CheckoutResultsErrorResponse FulfilmentWindows;
    private CheckoutResultsErrorResponse PickupStores;
    private CheckoutResultsErrorResponse SetPackagingOption;


    @Override
    public String toString() {
        return "CheckoutResultsResponse {" +
                "SetDeliveryWindow=" + SetDeliveryWindow +
                ", Order=" + Order +
                ", DeliveryPackagingPreferences=" + DeliveryPackagingPreferences +
                ", FulfilmentWindows=" + FulfilmentWindows +
                ", PickupStores =" + PickupStores +
                ", SetPackagingOption =" + SetPackagingOption +
                '}';
    }

    public CheckoutResultsErrorResponse getSetDeliveryWindow() {return SetDeliveryWindow; }
    public void setSetDeliveryWindow(CheckoutResultsErrorResponse setDeliveryWindow) { SetDeliveryWindow = setDeliveryWindow; }

    public CheckoutResultsErrorResponse getOrder() {return Order; }
    public void setOrder(CheckoutResultsErrorResponse order) { Order = order; }

    public CheckoutResultsErrorResponse getDeliveryPackagingPreferences() {return DeliveryPackagingPreferences; }
    public void setDeliveryPackagingPreferences(CheckoutResultsErrorResponse deliveryPackagingPreferences) { DeliveryPackagingPreferences = deliveryPackagingPreferences; }

    public CheckoutResultsErrorResponse getFulfilmentWindows() {return FulfilmentWindows; }
    public void setFulfilmentWindows(CheckoutResultsErrorResponse fulfilmentWindows) { FulfilmentWindows = fulfilmentWindows; }

    public CheckoutResultsErrorResponse getPickupStoress() {return PickupStores; }
    public void setPickupStores(CheckoutResultsErrorResponse pickupStores) { PickupStores = pickupStores; }

    public CheckoutResultsErrorResponse getSetPackagingOption() {return SetPackagingOption; }
    public void setSetPackagingOption(CheckoutResultsErrorResponse setPackagingOption) { SetPackagingOption = setPackagingOption; }
}
