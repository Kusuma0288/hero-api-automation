package au.com.woolworths.model.trader;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class Products {
  private int ListPrice;
  private int SalePrice;
  private boolean IsRanged;
  private String PackageSize;
  private String Updated;
  private Object[] MatchedPromotions;
  private Object[] MissedPromotions;
  private boolean IsDeliveryPass;
  private boolean IsRestricted;
  private String Note;
  private boolean AllowSubstitution;
  private String Stockcode;
  private String Name;
  private String Description;
  private int Quantity;
  private int MinimumQuantity;
  private String Unit;
  private int SupplyLimit;
  private int CupPrice;
  private String CupMeasure;
  private boolean IsAvailable;
  private boolean IsPmRestriction;
  private boolean IsForCollection;
  private boolean IsForDelivery;
  private boolean IsMeatStockLoss;
  //New Fields for Search Products Query
  private String RichDescription;
  private Object SavingsAmount;
  private int BasePrice;
  private boolean IsEdrSpecial;
  private boolean AgeRestricted;
  private boolean IsOnSpecial;
  private boolean HasBeenBoughtBefore;
  private Tag ImageTag;
  private Tag CentreTag;
  private Tag FooterTag;
  private boolean IsCentreTagEnabled;
  private boolean IsFooterTagEnabled;
  private int UnitWeightInGrams;
  private Object Restrictions;
  private boolean IsRestrictedByDeliPlatters;

}
