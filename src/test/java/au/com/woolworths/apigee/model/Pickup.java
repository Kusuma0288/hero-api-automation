package au.com.woolworths.apigee.model;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Pickup {
    private Store Store;

    @Override
    public String toString() {
        return "Pickup{" +
                "Store=" + Store +
                '}';
    }

    public Store getStore() {
        return Store;
    }

    public void setStore(Store store) {
        Store = store;
    }
}
