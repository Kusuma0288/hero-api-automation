package au.com.woolworths.apigee.model;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)

public class PickupDetails {
    private StoreInfo Store;
    private WindowInfo Window;

    @Override
    public String toString(){
        return "PickupDetails {" + "Store=" + Store + "Window=" + Window + '}';
    }

    public StoreInfo getStore() {
        return Store;
    }

    public void setStore(StoreInfo store) {
        Store = store;
    }

    public WindowInfo getWindow() {
        return Window;
    }

    public void setWindow(WindowInfo window) {
        Window = window;
    }
}

