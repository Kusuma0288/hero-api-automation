package au.com.woolworths.model.trader;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
@Data
public class Product {

  private List<String> detailsImagePaths = null;
  private List<NutritionalInformation> nutritionalInformation = null;
  private AdditionalAttributes additionalAttributes;
  private TgaAttributes tgaAttributes;
  private CountryOfOriginLabel countryOfOriginLabel;
  private String richDescription;
  private Integer savingsAmount;
  private Double Price;
  private Double basePrice;
  private Double WasPrice;
  private Boolean isEdrSpecial;
  private Double salePrice;
  private Boolean ageRestricted;
  private String packageSize;
  private Boolean isOnSpecial;
  private Boolean hasBeenBoughtBefore;
  private ImageTag imageTag;
  private CentreTag centreTag;
  private FooterTag footerTag;
  private Boolean isCentreTagEnabled;
  private Boolean isFooterTagEnabled;
  private int UnitWeightInGrams;
  private String stockcode;
  private String name;
  private String IsNew;
  private String description;
  private Integer quantity;
  private Integer minimumQuantity;
  private String unit;
  private Integer supplyLimit;
  private Double cupPrice;
  private String cupMeasure;
  private Boolean isAvailable;
  private Boolean isPmRestriction;
  private Boolean isForCollection;
  private Boolean isForDelivery;
  private Boolean isMeatStockLoss;
  private Boolean IsPurchasableWithRewardsCredits;
  private Double RewardsCreditPrice;
  private String Disclaimer;
  private Object Restrictions;
  private String SuitableFor;
  private String StorageInstructions;
  //New fields introduced for CMO (Change my order) changes.
  private String SmallImageFile;
  private String MediumImageFile;
  private String LargeImageFile;
  private String DisplayName;
  private String HeaderTag;
}