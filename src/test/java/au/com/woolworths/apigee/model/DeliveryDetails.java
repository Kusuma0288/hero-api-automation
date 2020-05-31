package au.com.woolworths.apigee.model;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)

public class DeliveryDetails {
    private AddressInfo Address;
    private au.com.woolworths.apigee.model.Window Window;

    @Override
    public String toString() {
        return "DeliveryDetails{" +
                "Address=" + Address +
                ", Window=" + Window +
                '}';
    }

    public AddressInfo getAddress() {
        return Address;
    }

    public void setAddress(AddressInfo address) {
        Address = address;
    }

    public au.com.woolworths.apigee.model.Window getWindow() {
        return Window;
    }

    public void setWindow(au.com.woolworths.apigee.model.Window window) {
        Window = window;
    }
}
