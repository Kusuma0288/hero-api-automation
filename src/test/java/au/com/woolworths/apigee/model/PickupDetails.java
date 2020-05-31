package au.com.woolworths.apigee.model;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)

public class PickupDetails {
    private Store Store;
    private Window Window;

    @Override
    public String toString(){
        return "PickupDetails {" + "Store=" + Store + "Window=" + Window + '}';
    }

    public Store getStore() {
        return Store;
    }

    public void setStore(Store store) {
        Store = store;
    }

    public Window getWindow() {
        return Window;
    }

    public void setWindow(Window window) {
        Window = window;
    }
}

