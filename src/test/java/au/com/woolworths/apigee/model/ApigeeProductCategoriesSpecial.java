package au.com.woolworths.apigee.model;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApigeeProductCategoriesSpecial {
    private ApigeeAisles[] aisles;

    @Override
    public String toString() {
        return "Categories{" +
                "aisles=" + aisles +
                +'}';
    }

    public ApigeeAisles[] getAisles() {
        return aisles;
    }

    public void setAisles(ApigeeAisles[] aisles) {
        this.aisles = aisles;
    }
}
