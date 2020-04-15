package au.com.woolworths.apigee.model;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApigeeAisles {
    private String title;
    private ApigeeProductCategories[] categories;
    private float number;
    private Object images;
    private Object color;

    @Override
    public String toString() {
        return "Aisles{" +
                "title=" + title +
                "categories=" + categories +
                "number=" + number +
                "images=" + images +
                "color=" + color +
                +'}';
    }

    public String getTitle() {
        return title;
    }

    public ApigeeProductCategories[] getCategories() {
        return categories;
    }

    public float getNumber() {
        return number;
    }

    public Object getImages() {
        return images;
    }

    public Object getColor() {
        return color;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCategories(ApigeeProductCategories[] categories) {
        this.categories = categories;
    }

    public void setNumber(float number) {
        this.number = number;
    }

    public void setImages(Object images) {
        this.images = images;
    }

    public void setColor(Object color) {
        this.color = color;
    }
}
