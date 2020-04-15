package au.com.woolworths.apigee.model;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductGroupComponents {
    private ProductGroupComponentsData data;
    private String type;



    @Override
    public String toString() {
        return "ProductGroupComponents{" +
                "data=" + data +
                ", type=" + type +
                +'}';
    }

    public ProductGroupComponentsData getData() {
        return data;
    }

    public void setData(ProductGroupComponentsData data) {
        this.data = data;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}



