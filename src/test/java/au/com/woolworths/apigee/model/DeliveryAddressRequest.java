package au.com.woolworths.apigee.model;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)

public class DeliveryAddressRequest {
    private String address;

    @Override
    public String toString() {
        return "DeliveryAddressRequest{" +
                "address='" + address + '\'' +
                '}';
    }

    public String getAddress()
    {
        return address;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

}




