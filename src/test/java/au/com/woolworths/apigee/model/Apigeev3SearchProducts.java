package au.com.woolworths.apigee.model;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Apigeev3SearchProducts {

  private String name;
  private String article;
  private String description;
  private Object images;
  private float size;
  private String measure;
  private ApigeeIsProductFlag is;
  private String timeslotrestriction;
  private String instorelocation;
  private int instoreaisleid;
  private String instoreaisleside;
  private String instoreaislebay;
  private InStorePrice instoreprice;
  private Object offers;
  private Object promo;
  private Object footer;
  private Object footerNew;
  private Promotions promotions;
  private float stockqty;
  private float defaultqty;
  private float incrementalquantity;
  private Object supplementaryinfo;
  private String details;
  private String badge;
  private Object productbadge;
  private ApigeeProductDisclaimer disclaimer;
  private Object stock;
  private Boolean hasBeenBoughtBefore;
  private String specialbadge;
  private String savingsamount;

  @Override
  public String toString() {
    return "Apigeev3SearchProducts{" +
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
            "instoreprice=" + instoreprice +
            "offers=" + offers +
            "promo=" + promo +
            "footer" + footer +
            "footerNew=" + footerNew +
            "promotions=" + promotions +
            "stockqty=" + stockqty +
            "defaultqty=" + defaultqty +
            "incrementalquantity=" + incrementalquantity +
            "supplementaryinfo=" + supplementaryinfo +
            "details=" + details +
            "badge=" + badge +
            "productbadge=" + productbadge +
            "disclaimer=" + disclaimer +
            "stock=" + stock +
            "hasBeenBoughtBefore=" + hasBeenBoughtBefore +
            "specialbadge=" + specialbadge +
            "savingsamount=" + savingsamount +
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

  public int getInstoreaisleid() {
    return instoreaisleid;
  }

  public String getInstoreaisleside() {
    return instoreaisleside;
  }

  public String getInstoreaislebay() {
    return instoreaislebay;
  }

  public InStorePrice getInstoreprice() {
    return instoreprice;
  }

  public Object getOffers() {
    return offers;
  }

  public Promotions getPromotions() {
    return promotions;
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

  public void setInstoreaisleid(int instoreaisleid) {
    this.instoreaisleid = instoreaisleid;
  }

  public void setInstoreaisleside(String instoreaisleside) {
    this.instoreaisleside = instoreaisleside;
  }

  public void setInstoreaislebay(String instoreaislebay) {
    this.instoreaislebay = instoreaislebay;
  }

  public void setInstoreprice(InStorePrice instoreprice) {
    this.instoreprice = instoreprice;
  }

  public void setOffers(Object offers) {
    this.offers = offers;
  }

  public void setPromo(Object promo) {
    this.promo = promo;
  }

  public void setFooter(Object footer) {
    this.footer = footer;
  }

  public void setFooterNew(Object footerNew) {
    this.footerNew = footerNew;
  }

  public void setPromotions(Promotions promotions) {
    this.promotions = promotions;
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

  public void setStock(Object stock) {
    this.stock = stock;
  }

  public void setHasBeenBoughtBefore(Boolean hasBeenBoughtBefore) {
    this.hasBeenBoughtBefore = hasBeenBoughtBefore;
  }

  public void setSpecialbadge(String specialbadge) {
    this.specialbadge = specialbadge;
  }

  public void setSavingsamount(String savingsamount) {
    this.savingsamount = savingsamount;
  }

  public Object getProductbadge() {
    return productbadge;
  }

  public void setProductbadge(Object productbadge) {
    this.productbadge = productbadge;
  }
}

