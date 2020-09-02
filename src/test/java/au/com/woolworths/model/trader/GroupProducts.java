package au.com.woolworths.model.trader;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.ArrayList;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class GroupProducts {

  ArrayList<Object> ChildProducts = new ArrayList<Object>();
  ArrayList<Object> DetailsImagePaths = new ArrayList<Object>();
  private String TileID;
  private String Stockcode;
  private String Barcode;
  private float GtinFormat;
  private float CupPrice;
  private float InstoreCupPrice;
  private String CupMeasure;
  private String CupString;
  private String InstoreCupString;
  private boolean HasCupPrice;
  private boolean InstoreHasCupPrice;
  private float Price;
  private float InstorePrice;
  private String Name;
  private String UrlFriendlyName;
  private String Description;
  private String SmallImageFile;
  private String MediumImageFile;
  private String LargeImageFile;
  private boolean IsNew;
  private boolean IsOnSpecial;
  private boolean InstoreIsOnSpecial;
  private boolean IsEdrSpecial;
  private float SavingsAmount;
  private float InstoreSavingsAmount;
  private float WasPrice;
  private float InstoreWasPrice;
  private float QuantityInTrolley;
  private String Unit;
  private float MinimumQuantity;
  private boolean HasBeenBoughtBefore;
  private boolean IsInTrolley;
  private String Source;
  private float SupplyLimit;
  private boolean IsRanged;
  private boolean IsInStock;
  private String PackageSize;
  private boolean IsPmDelivery;
  private boolean IsForCollection;
  private boolean IsForDelivery;
  private boolean IsForExpress;
  private String ProductRestrictionMessage = null;
  private String ProductWarningMessage = null;
  private Object CentreTag;
  private boolean IsCentreTag;
  private Object ImageTag;
  private Object HeaderTag;
  private boolean HasHeaderTag;
  private float UnitWeightInGrams;
  private String SupplyLimitMessage;
  private String SmallFormatDescription;
  private String FullDescription;
  private boolean IsAvailable;
  private boolean InstoreIsAvailable;
  private boolean IsPurchasable;
  private boolean InstoreIsPurchasable;
  private boolean AgeRestricted;
  private float DisplayQuantity;
  private String RichDescription;
  private boolean IsDeliveryPass;
  private boolean HideWasSavedPrice;
  private String SapCategories = null;
  private String Brand;
  private Object FooterTag;
  private boolean IsFooterEnabled;
  private String Diagnostics;
  private boolean IsBundle;
  private boolean IsInFamily;
  private String UrlOverride = null;
  private Object AdditionalAttributes;
  private String Variety = null;
  private Object Rating;
  private boolean IsSponsoredAd;
  private String AdID = null;
  private String AdIndex = null;

}
