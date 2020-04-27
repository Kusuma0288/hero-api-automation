package au.com.woolworths.apigee.model;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApigeeProducts {
    private String name;
    private String article;
    private String description;
    private Object images;
    private float size;
    private String measure;
    private ApigeeIsProductFlag is;
    private String timeslotrestriction;
    private String instorelocation;
    private String instoreaisleid;
    private String instoreaisleside;
    private String instoreaislebay;
    private String ecfcategory1;
    private String ecfcategory2;
    private String ecfcategory3;
    private Object instoreprice;
    private Object offers;
    private Object promo;
    private float stockqty;
    private float defaultqty;
    private float incrementalquantity;
    private Object supplementaryinfo;
    private String details;
    private String badge;
    private Object productbadge;
    private ApigeeProductDisclaimer disclaimer;
    private String specialbadge;
    private String savingsamount;
    private Object stock;
    private String hasBeenBoughtBefore;
    private Object footerNew;

    @Override
    public String toString() {
        return "ApigeeProducts{" +
                "name=" + name +
                "article=" + article +
                "description=" + description +
                "images=" + images +
                "size=" + size +
                "measure=" + measure +
                "is=" + is +
                "timeslotrestriction=" + timeslotrestriction +
                "instorelocation=" + instorelocation +
                "instoreaisleid=" + instoreaisleid +
                "instoreaisleside=" + instoreaisleside +
                "instoreaislebay=" + instoreaislebay +
                "ecfcategory1=" + ecfcategory1 +
                "ecfcategory2=" + ecfcategory2 +
                "ecfcategory3=" + ecfcategory3 +
                "instoreprice=" + instoreprice +
                "offers=" + offers +
                "promotions=" + promo +
                "stockqty=" + stockqty +
                "defaultqty=" + defaultqty +
                "incrementalquantity=" + incrementalquantity +
                "supplementaryinfo=" + supplementaryinfo +
                "details=" + details +
                "badge=" + badge +
                "productbadge=" + productbadge +
                "disclaimer=" + disclaimer +
                "specialbadge=" + specialbadge +
                "savingsamount=" + savingsamount +
                "stock=" + stock +
                "hasBeenBoughtBefore=" + hasBeenBoughtBefore +
                "footerNew=" + footerNew +
                +'}';
    }

    public String getName() {
        return name;
    }

    public String getArticle() {
        return article;
    }

    public String getDescription() {
        return description;
    }

    public Object getImages() {
        return images;
    }

    public float getSize() {
        return size;
    }

    public String getMeasure() {
        return measure;
    }

    public ApigeeIsProductFlag getIs() {
        return is;
    }

    public String getTimeslotrestriction() {
        return timeslotrestriction;
    }

    public String getInstorelocation() {
        return instorelocation;
    }

    public String getInstoreaisleid() {
        return instoreaisleid;
    }

    public String getInstoreaisleside() {
        return instoreaisleside;
    }

    public String getInstoreaislebay() {
        return instoreaislebay;
    }

    public String getEcfcategory1() {
        return ecfcategory1;
    }

    public String getEcfcategory2() {
        return ecfcategory2;
    }

    public String getEcfcategory3() {
        return ecfcategory3;
    }

    public Object getInstoreprice() {
        return instoreprice;
    }

    public Object getOffers() {
        return offers;
    }

    public Object getPromo() {
        return promo;
    }

    public float getStockqty() {
        return stockqty;
    }

    public float getDefaultqty() {
        return defaultqty;
    }

    public float getIncrementalquantity() {
        return incrementalquantity;
    }

    public Object getSupplementaryinfo() {
        return supplementaryinfo;
    }

    public String getDetails() {
        return details;
    }

    public String getBadge() {
        return badge;
    }

    public ApigeeProductDisclaimer getDisclaimer() {
        return disclaimer;
    }

    public String getSpecialbadge() {
        return specialbadge;
    }

    public String getSavingsamount() {
        return savingsamount;
    }

    public Object getStock() {
        return stock;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setArticle(String article) {
        this.article = article;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImages(Object images) {
        this.images = images;
    }

    public void setSize(float size) {
        this.size = size;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
    }

    public void setIs(ApigeeIsProductFlag is) {
        this.is = is;
    }

    public void setTimeslotrestriction(String timeslotrestriction) {
        this.timeslotrestriction = timeslotrestriction;
    }

    public void setInstorelocation(String instorelocation) {
        this.instorelocation = instorelocation;
    }

    public void setInstoreaisleid(String instoreaisleid) {
        this.instoreaisleid = instoreaisleid;
    }

    public void setInstoreaisleside(String instoreaisleside) {
        this.instoreaisleside = instoreaisleside;
    }

    public void setInstoreaislebay(String instoreaislebay) {
        this.instoreaislebay = instoreaislebay;
    }

    public void setEcfcategory1(String ecfcategory1) {
        this.ecfcategory1 = ecfcategory1;
    }

    public void setEcfcategory2(String ecfcategory2) {
        this.ecfcategory2 = ecfcategory2;
    }

    public void setEcfcategory3(String ecfcategory3) {
        this.ecfcategory3 = ecfcategory3;
    }

    public void setInstoreprice(Object instoreprice) {
        this.instoreprice = instoreprice;
    }

    public void setOffers(Object offers) {
        this.offers = offers;
    }

    public void setPromotions(Object promo) {
        this.promo = promo;
    }

    public void setStockqty(float stockqty) {
        this.stockqty = stockqty;
    }

    public void setDefaultqty(float defaultqty) {
        this.defaultqty = defaultqty;
    }

    public void setIncrementalquantity(float incrementalquantity) {
        this.incrementalquantity = incrementalquantity;
    }

    public void setSupplementaryinfo(Object supplementaryinfo) {
        this.supplementaryinfo = supplementaryinfo;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public void setBadge(String badge) {
        this.badge = badge;
    }

    public void setDisclaimer(ApigeeProductDisclaimer disclaimer) {
        this.disclaimer = disclaimer;
    }

    public void setSpecialbadge(String specialbadge) {
        this.specialbadge = specialbadge;
    }

    public void setSavingsamount(String savingsamount) {
        this.savingsamount = savingsamount;
    }
    public void setStock(Object stock) {
        this.stock = stock;
    }

    public Object getProductbadge() {
        return productbadge;
    }

    public void setProductbadge(Object productbadge) {
        this.productbadge = productbadge;
    }

    public void setPromo(Object promo) {
        this.promo = promo;
    }

    public String getHasBeenBoughtBefore() {
        return hasBeenBoughtBefore;
    }

    public void setHasBeenBoughtBefore(String hasBeenBoughtBefore) {
        this.hasBeenBoughtBefore = hasBeenBoughtBefore;
    }

    public Object getFooterNew() {
        return footerNew;
    }

    public void setFooterNew(Object footerNew) {
        this.footerNew = footerNew;
    }
}
