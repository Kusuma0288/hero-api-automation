package au.com.woolworths.apigee.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductGroupTrolleyData {
    private String minimum;
    private String maximum;
    private String increment;
    @JsonProperty("default")
    private String defaultInTrolley;
    private String inTrolley;
    private String unit;
    private String buttonState;
    private String buttonLabel;
    private String buttonQuantity;

    @Override
    public String toString() {
        return "ProductGroupTrolleyResponse{" +
                "minimum=" + minimum +
                ", maximum=" + maximum +
                ", increment=" + increment +
                ", inTrolley=" + inTrolley +
                ", unit=" + unit +
                ", buttonState=" + buttonState +
                ", buttonLabel=" + buttonLabel +
                ", buttonQuantity=" + buttonQuantity +
                + '}';
    }

    public String getMinimum() {
        return minimum;
    }

    public void setMinimum(String minimum) {
        this.minimum = minimum;
    }

    public String getMaximum() {
        return maximum;
    }

    public void setMaximum(String maximum) {
        this.maximum = maximum;
    }

    public String getIncrement() {
        return increment;
    }

    public void setIncrement(String increment) {
        this.increment = increment;
    }



    public String getInTrolley() {
        return inTrolley;
    }

    public void setInTrolley(String inTrolley) {
        this.inTrolley = inTrolley;
    }

    public String getButtonState() {
        return buttonState;
    }

    public void setButtonState(String buttonState) {
        this.buttonState = buttonState;
    }

    public String getButtonLabel() {
        return buttonLabel;
    }

    public void setButtonLabel(String buttonLabel) {
        this.buttonLabel = buttonLabel;
    }

    public String getButtonQuantity() {
        return buttonQuantity;
    }

    public void setButtonQuantity(String buttonQuantity) {
        this.buttonQuantity = buttonQuantity;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getDefaultInTrolley() {
        return defaultInTrolley;
    }

    public void setDefaultInTrolley(String defaultInTrolley) {
        this.defaultInTrolley = defaultInTrolley;
    }
}



