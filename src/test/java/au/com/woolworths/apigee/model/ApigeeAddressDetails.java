package au.com.woolworths.apigee.model;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApigeeAddressDetails {
    private String id;
    private String text;
    private boolean isprimary;
    private String postalcode;
    private String street1;
    private String street2;
    private String suburbid;
    private String suburbname;
    private boolean ispartner;
    private String partnerbranchid;

    @Override
    public String toString() {
        return "AddressDetail{" +
                "id=" + id +
                ", text=" + text +
                ", isprimary=" + isprimary +
                ", postalcode=" + postalcode +
                ", street1=" + street1 +
                ", street2=" + street2 +
                ", suburbid=" + suburbid +
                ", suburbname=" + suburbname +
                ", ispartner=" + ispartner +
                ", partnerbranchid=" + partnerbranchid +
                '}';
    }
    // Getter Methods

    public String getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public boolean getIsprimary() {
        return isprimary;
    }

    public String getPostalcode() {
        return postalcode;
    }

    public String getStreet1() {
        return street1;
    }

    public String getStreet2() {
        return street2;
    }

    public String getSuburbid() {
        return suburbid;
    }

    public String getSuburbname() {
        return suburbname;
    }

    public boolean getIspartner() {
        return ispartner;
    }

    public String getPartnerbranchid() {
        return partnerbranchid;
    }

    // Setter Methods

    public void setId(String id) {
        this.id = id;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setIsprimary(boolean isprimary) {
        this.isprimary = isprimary;
    }

    public void setPostalcode(String postalcode) {
        this.postalcode = postalcode;
    }

    public void setStreet1(String street1) {
        this.street1 = street1;
    }

    public void setStreet2(String street2) {
        this.street2 = street2;
    }

    public void setSuburbid(String suburbid) {
        this.suburbid = suburbid;
    }

    public void setSuburbname(String suburbname) {
        this.suburbname = suburbname;
    }

    public void setIspartner(boolean ispartner) {
        this.ispartner = ispartner;
    }

    public void setPartnerbranchid(String partnerbranchid) {
        this.partnerbranchid = partnerbranchid;
    }
}
