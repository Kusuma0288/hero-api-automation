package au.com.woolworths.apigee.model;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApigeeSearchAddresses {
    private ApigeeAddress[] addresses;

    @Override
    public String toString() {
        return "Addresses{" +
                "addresses=" + addresses +
                +'}';
    }

    public ApigeeAddress[] getaddresses() {
        return addresses;
    }

    public void setaddresses(ApigeeAddress[] address) {
        addresses = address;
    }
}
