package au.com.woolworths.apigee.model;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)

public class DeliveryDetails {
    private AddressInfo Address;
    private WindowInfo Window;

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

    public WindowInfo getWindow() {
        return Window;
    }

    public void setWindow(WindowInfo window) {
        Window = window;
    }
}
