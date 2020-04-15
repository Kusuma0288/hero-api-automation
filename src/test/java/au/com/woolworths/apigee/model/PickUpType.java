package au.com.woolworths.apigee.model;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class PickUpType {
    private long AddressId;
    private String Type;
    private String Label;
    private String Text;

    @Override
    public String toString() {
        return "PickUpType{" +
                "AddressId=" + AddressId +
                ", Type='" + Type + '\'' +
                ", Label='" + Label + '\'' +
                ", Text='" + Text + '\'' +
                '}';
    }

    public long getAddressId() {
        return AddressId;
    }

    public void setAddressId(long addressId) {
        AddressId = addressId;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getLabel() {
        return Label;
    }

    public void setLabel(String label) {
        Label = label;
    }

    public String getText() {
        return Text;
    }

    public void setText(String text) {
        Text = text;
    }
}
