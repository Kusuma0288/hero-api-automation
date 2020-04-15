package au.com.woolworths.apigee.model;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductGroupData {
    private ProductGroupComponents[] Items;


    @Override
    public String toString() {
        return "ProductGroupData{" +
                "Items=" + Items +
                + '}';
    }

    public ProductGroupComponents[] getItems() {
        return Items;
    }

    public void setItems(ProductGroupComponents[] items) {
        Items = items;
    }
}



