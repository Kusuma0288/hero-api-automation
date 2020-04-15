package au.com.woolworths.apigee.model;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class SpecialsCategories {
    private String products_href;
    private String title;
    private int product_count;


    @Override
    public String toString() {
        return "categories{" +
                "products_href='" + products_href + '\'' +
                "title='" + title + '\'' +
                "product_count='" + product_count + '\'' +
                '}';
    }

    public String getProducts_href() {
        return products_href;
    }

    public void setProducts_href(String products_href) {
        this.products_href = products_href;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getProduct_count() {
        return product_count;
    }

    public void setProduct_count(int product_count) {
        this.product_count = product_count;
    }
}