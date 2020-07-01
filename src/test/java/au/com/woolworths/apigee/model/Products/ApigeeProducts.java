package au.com.woolworths.apigee.model.Products;

import au.com.woolworths.apigee.model.ApigeeIsProductFlag;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import com.fasterxml.jackson.annotation.JsonInclude;
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data public class ApigeeProducts {
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
  private InStorePrice instoreprice;
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
}
