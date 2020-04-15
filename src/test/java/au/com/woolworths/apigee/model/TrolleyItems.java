package au.com.woolworths.apigee.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Arrays;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class TrolleyItems {
    private List<Item> items;
    private boolean replaceTrolley;

    @Override
    public String toString() {
        return "TrolleyItems{" +
                "items=" + items +
                ", replaceTrolley=" + replaceTrolley +
                '}';
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public boolean isReplaceTrolley() {
        return replaceTrolley;
    }

    public void setReplaceTrolley(boolean replaceTrolley) {
        this.replaceTrolley = replaceTrolley;
    }
}
