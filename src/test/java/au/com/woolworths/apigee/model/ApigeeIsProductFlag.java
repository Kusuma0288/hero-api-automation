package au.com.woolworths.apigee.model;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApigeeIsProductFlag {
  private boolean liquor;
  private boolean ranged;
  private boolean weighed;
  private boolean weightonline;
  private boolean tobacco;
  private boolean everydaypricecut;
  private boolean pmrestriction;
  private boolean forcollection;
  private boolean fordelivery;
  private boolean meatstockloss;

  @Override
  public String toString() {
    return "ApigeeIsProductFlag{" +
            "liquor=" + liquor +
            "ranged=" + ranged +
            "weighed=" + weighed +
            "weightonline=" + weightonline +
            "tobacco=" + tobacco +
            "everydaypricecut=" + everydaypricecut +
            "pmrestriction=" + pmrestriction +
            "forcollection=" + forcollection +
            "fordelivery=" + fordelivery +
            "meatstockloss=" + meatstockloss
            + '}';
  }

  public boolean isLiquor() {
    return liquor;
  }

  public void setLiquor(boolean liquor) {
    this.liquor = liquor;
  }

  public boolean isRanged() {
    return ranged;
  }

  public void setRanged(boolean ranged) {
    this.ranged = ranged;
  }

  public boolean isWeighed() {
    return weighed;
  }

  public void setWeighed(boolean weighed) {
    this.weighed = weighed;
  }

  public boolean isWeightonline() {
    return weightonline;
  }

  public void setWeightonline(boolean weightonline) {
    this.weightonline = weightonline;
  }

  public boolean isTobacco() {
    return tobacco;
  }

  public void setTobacco(boolean tobacco) {
    this.tobacco = tobacco;
  }

  public boolean isEverydaypricecut() {
    return everydaypricecut;
  }

  public void setEverydaypricecut(boolean everydaypricecut) {
    this.everydaypricecut = everydaypricecut;
  }

  public boolean isPmrestriction() {
    return pmrestriction;
  }

  public void setPmrestriction(boolean pmrestriction) {
    this.pmrestriction = pmrestriction;
  }

  public boolean isForcollection() {
    return forcollection;
  }

  public void setForcollection(boolean forcollection) {
    this.forcollection = forcollection;
  }

  public boolean isFordelivery() {
    return fordelivery;
  }

  public void setFordelivery(boolean fordelivery) {
    this.fordelivery = fordelivery;
  }

  public boolean isMeatstockloss() {
    return meatstockloss;
  }

  public void setMeatstockloss(boolean meatstockloss) {
    this.meatstockloss = meatstockloss;
  }

}
