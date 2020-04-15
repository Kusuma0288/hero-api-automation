package au.com.woolworths.apigee.model;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class SpecialspageResponse {
    private SpecialsCategories[] categories;

    @Override
    public String toString() {
        return "getCategories{" +
                "categories='" + categories + '\'' +
                '}';
    }

    public SpecialsCategories[] getCategories() {
        return categories;
    }

    public void setCategories(SpecialsCategories[] categories) {
        this.categories = categories;
    }
}

