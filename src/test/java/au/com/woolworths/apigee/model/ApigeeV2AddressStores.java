package au.com.woolworths.apigee.model;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApigeeV2AddressStores {
    private ApigeeStores[] stores;

    @Override
    public String toString() {
        return "ApigeeV2AddressStores{" +
                "stores=" + stores +
                '}';
    }

    public ApigeeStores[] getStores() {
        return stores;
    }
    public ApigeeStores[] setStores(ApigeeStores apigeeStores[]) {
       return this.stores = apigeeStores;
    }

}
