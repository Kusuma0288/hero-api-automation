package au.com.woolworths.apigee.model;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)

public class Delivery {

    private Address Address;

    @Override
    public String toString() {
        return "Delivery{" +
                "Address=" + Address +
                '}';
    }
    public Address getAddress() {
        return Address;
    }

    public void setAddress(Address address) {
        Address = address;
    }



}
