package au.com.woolworths.model.trader;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class AdditionalAttributes {

  private String sapdepartmentname;
  private String sapdepartmentno;
  private String sapcategoryname;
  private String sapcategoryno;
  private String sapsubcategoryname;
  private String sapsubcategoryno;
  private String sapsegmentname;
  private String sapsegmentno;
  private Object specialsgroupid;
  private Object piesProductDepartmentNodeId;
  private String piesProductDepartmentsjson;
  private String piesdepartmentnamesjson;
  private String piescategorynamesjson;
  private String piessubcategorynamesjson;
  @JsonProperty("servingsize-total-nip")
  private Object servingsizeTotalNip;
  @JsonProperty("servingsperpack-total-nip")
  private Object servingsperpackTotalNip;
  private String microwaveable;
  private String ovencook;
  private String vegetarian;
  private String freezable;
  private String containsnuts;
  private String containsgluten;
  private String description;
  private String ingredients;
  private String nutritionalinformation;
  private Object storageinstructions;
  private Object usageinstructions;
  private String brand;
  private Object manufacturer;
  private Object countryoforigin;
  private Object productheightmm;
  private Object productwidthmm;
  private Object productdepthmm;
  private Object wool_productpackaging;
//  private Object woolProductpackaging;
  private Object recyclableinformation;
  private Object importantinformation;
  private String allergencontains;
  private Object allergenmaybepresent;
  private Object allergystatement;
  private Object lifestyleanddietarystatement;
  private Object lifestyleclaim;
  private String wool_dietaryclaim;
  //private String woolDietaryclaim;
  private Object boxedcontents;
  private Object suitablefor;
  private Object fragrance;
  private Object colour;
  private String antioxidant;
  private String addedvitaminsandminerals;
  private String bpafree;
  private String sulfatefree;
  private String parabenfree;
  private Object claims;
  private Object alcoholfree;
  @JsonProperty("anti-dandruff")
  private Object antiDandruff;
  private Object dermatologicallyapproved;
  private Object dermatologistrecommended;
  private Object dermatologisttested;
  @JsonProperty("fragrance-free")
  private Object fragranceFree;
  private Object haircolour;
  private Object hairtype;
  @JsonProperty("hypo-allergenic")
  private Object hypoAllergenic;
  @JsonProperty("non-comedogenic")
  private Object nonComedogenic;
  private Object oilfree;
  private Object ophthalmologistapproved;
  private Object ophthalmologisttested;
  @JsonProperty("paba-free")
  private Object pabaFree;
  private Object skincondition;
  private Object skintype;
  @JsonProperty("soap-free")
  private Object soapFree;
  private Object spf;
  private Object sweatresistant;
  private Object timer;
  private Object waterresistant;
  private Object phbalanced;
  private String antiseptic;
  private String antibacterial;
  private Object activeconstituents;
  private Object contains;
  private String microwavesafe;
  private String productimagecount;
  private Object friendlydisclaimer;
  private String productimages;
  private String healthstarrating;
  private Object tgawarnings;
  private Object tgawarning;
  private String vendorarticleid;
  private String vendorcostprice;

}
