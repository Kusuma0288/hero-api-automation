package au.com.woolworths.apigee.model;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)

public class DeliveryDetails {
    private Address Address;
    private Window Window;

    @Override
    public String toString() {
        return "DeliveryDetails{" +
                "Address=" + Address +
                ", Window=" + Window +
                '}';
    }

    public Address getAddress() {
        return Address;
    }

    public void setAddress(Address address) {
        Address = address;
    }

    public Window getWindow() {
        return Window;
    }

    public void setWindow(Window window) {
        Window = window;
    }
}
