package au.com.woolworths.apigee.model;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class InStorePrice {

    private String cupuom;
    private int cupsize;
    private String uom;
    private double pricegst;
    private double cupprice;
    private String previousprice;

    @Override
    public String toString() {
        return "InStorePrice{" +
                "CupUom=" + cupuom +
                "CupSize=" + cupsize +
                "CupPrice=" + uom +
                "Uom=" + pricegst +
                "PriceGst=" + cupprice +
                "PriceGst=" + previousprice +
                +'}';
    }

    public String getCUom() {
        return cupuom;
    }

    public void setCupUom(String cupuom) {
        this.cupuom = cupuom;
    }

    public int getCupSize() {
        return cupsize;
    }

    public void setCupSize(int cupsize) {
        this.cupsize = cupsize;
    }

    public String getUom() {
        return uom;
    }

    public void setUom(String uom) {
        this.uom = uom;
    }

    public double getPriceGst() {
        return pricegst;
    }

    public void setPriceGst(double pricegst) {
        this.pricegst = pricegst;
    }

    public double getCupPrice() {
        return cupprice;
    }

    public void setCupPrice(double cupprice) {
        this.cupprice = cupprice;
    }

    public String getPreviousPrice() {
        return previousprice;
    }

    public void setPreviousPrice(String previousprice) {
        this.previousprice = previousprice;
    }
}
