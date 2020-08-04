package au.com.woolworths.model.apigee.search;

import au.com.woolworths.model.apigee.products.ProductFlag;
import au.com.woolworths.model.common.Promotions;
import au.com.woolworths.model.apigee.products.ProductDisclaimer;
import lombok.Data;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data public class SearchProductsV3 {

  private String name;
  private String article;
  private String description;
  private Object images;
  private float size;
  private String measure;
  private ProductFlag is;
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
  Promotions promotions;
  private float stockqty;
  private float defaultqty;
  private float incrementalquantity;
  private Object supplementaryinfo;
  private String details;
  private String badge;
  private Object productbadge;
  private ProductDisclaimer disclaimer;
  private Object stock;
  private Boolean hasBeenBoughtBefore;
  private String specialbadge;
  private String savingsamount;
}

