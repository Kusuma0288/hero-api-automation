package au.com.woolworths.apigee.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class ApigeeLoginReponse {
    @JsonProperty("access_token")
    private String accessToken;
    @JsonProperty("refresh_token")
    private String refreshToken;
    @JsonProperty("expires_in")
    private String expiresIn;
    @JsonProperty("trolleyhref")
    private String trolleyhref;
    @JsonProperty("fulfilmenthref")
    private String fulfilmenthref;
    @JsonProperty("paymenthref")
    private String paymenthref;
    @JsonProperty("shopperid")
    private String shopperid;
    @JsonProperty("title")
    private String title;
    @JsonProperty("firstname")
    private String firstname;
    @JsonProperty("lastname")
    private String lastname;
    @JsonProperty("email")
    private String email;
    @JsonProperty("dateofbirth")
    private String dateofbirth;
    @JsonProperty("primaryphone")
    private String primaryphone;
    @JsonProperty("secondaryphone")
    private String secondaryphone;
    @JsonProperty("otherphone")
    private String otherphone;
    @JsonProperty("fulfilmentstoreid")
    private String fulfilmentstoreid;
    @JsonProperty("fulfilmentmethod")
    private String fulfilmentmethod;
    @JsonProperty("fulfilmentstorename")
    private String fulfilmentstorename;
    @JsonProperty("deliverymethod")
    private String deliverymethod;
    @JsonProperty("deliverystoreaddressid")
    private String deliverystoreaddressid;
    @JsonProperty("deliverystoreaddresscity")
    private String deliverystoreaddresscity;
    @JsonProperty("deliverystoreaddresscountry")
    private String deliverystoreaddresscountry;
    @JsonProperty("deliveryaddressstreet1")
    private String deliveryaddressstreet1;
    @JsonProperty("deliveryaddressstreet2")
    private String deliveryaddressstreet2;
    @JsonProperty("deliveryaddresssuburb")
    private String deliveryaddresssuburb;
    @JsonProperty("deliveryinstructions")
    private String deliveryinstructions;
    @JsonProperty("isservicable")
    private String isservicable;
    @JsonProperty("rewardscardNumber")
    private String rewardscardNumber;
    @JsonProperty("teammember")
    private String teammember;
    @JsonProperty("trolleymergestatus")
    private String trolleymergestatus;
    private String statusCode;
    private String error;

    @JsonProperty("access_token")
    public String getAccessToken() {
        return accessToken;
    }

    @JsonProperty("access_token")
    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    @JsonProperty("refresh_token")
    public String getRefreshToken() {
        return refreshToken;
    }

    @JsonProperty("refresh_token")
    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    @JsonProperty("expires_in")
    public String getExpiresIn() {
        return expiresIn;
    }

    @JsonProperty("expires_in")
    public void setExpiresIn(String expiresIn) {
        this.expiresIn = expiresIn;
    }

    @JsonProperty("trolleyhref")
    public String getTrolleyhref() {
        return trolleyhref;
    }

    @JsonProperty("trolleyhref")
    public void setTrolleyhref(String trolleyhref) {
        this.trolleyhref = trolleyhref;
    }

    @JsonProperty("fulfilmenthref")
    public String getFulfilmenthref() {
        return fulfilmenthref;
    }

    @JsonProperty("fulfilmenthref")
    public void setFulfilmenthref(String fulfilmenthref) {
        this.fulfilmenthref = fulfilmenthref;
    }

    @JsonProperty("paymenthref")
    public String getPaymenthref() {
        return paymenthref;
    }

    @JsonProperty("paymenthref")
    public void setPaymenthref(String paymenthref) {
        this.paymenthref = paymenthref;
    }

    @JsonProperty("shopperid")
    public String getShopperid() {
        return shopperid;
    }

    @JsonProperty("shopperid")
    public void setShopperid(String shopperid) {
        this.shopperid = shopperid;
    }

    @JsonProperty("title")
    public String getTitle() {
        return title;
    }

    @JsonProperty("title")
    public void setTitle(String title) {
        this.title = title;
    }

    @JsonProperty("firstname")
    public String getFirstname() {
        return firstname;
    }

    @JsonProperty("firstname")
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    @JsonProperty("lastname")
    public String getLastname() {
        return lastname;
    }

    @JsonProperty("lastname")
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    @JsonProperty("email")
    public String getEmail() {
        return email;
    }

    @JsonProperty("email")
    public void setEmail(String email) {
        this.email = email;
    }

    @JsonProperty("dateofbirth")
    public String getDateofbirth() {
        return dateofbirth;
    }

    @JsonProperty("dateofbirth")
    public void setDateofbirth(String dateofbirth) {
        this.dateofbirth = dateofbirth;
    }

    @JsonProperty("primaryphone")
    public String getPrimaryphone() {
        return primaryphone;
    }

    @JsonProperty("primaryphone")
    public void setPrimaryphone(String primaryphone) {
        this.primaryphone = primaryphone;
    }

    @JsonProperty("secondaryphone")
    public String getSecondaryphone() {
        return secondaryphone;
    }

    @JsonProperty("secondaryphone")
    public void setSecondaryphone(String secondaryphone) {
        this.secondaryphone = secondaryphone;
    }

    @JsonProperty("otherphone")
    public String getOtherphone() {
        return otherphone;
    }

    @JsonProperty("otherphone")
    public void setOtherphone(String otherphone) {
        this.otherphone = otherphone;
    }

    @JsonProperty("fulfilmentstoreid")
    public String getFulfilmentstoreid() {
        return fulfilmentstoreid;
    }

    @JsonProperty("fulfilmentstoreid")
    public void setFulfilmentstoreid(String fulfilmentstoreid) {
        this.fulfilmentstoreid = fulfilmentstoreid;
    }

    @JsonProperty("fulfilmentmethod")
    public String getFulfilmentmethod() {
        return fulfilmentmethod;
    }

    @JsonProperty("fulfilmentmethod")
    public void setFulfilmentmethod(String fulfilmentmethod) {
        this.fulfilmentmethod = fulfilmentmethod;
    }

    @JsonProperty("fulfilmentstorename")
    public String getFulfilmentstorename() {
        return fulfilmentstorename;
    }

    @JsonProperty("fulfilmentstorename")
    public void setFulfilmentstorename(String fulfilmentstorename) {
        this.fulfilmentstorename = fulfilmentstorename;
    }

    @JsonProperty("deliverymethod")
    public String getDeliverymethod() {
        return deliverymethod;
    }

    @JsonProperty("deliverymethod")
    public void setDeliverymethod(String deliverymethod) {
        this.deliverymethod = deliverymethod;
    }

    @JsonProperty("deliverystoreaddressid")
    public String getDeliverystoreaddressid() {
        return deliverystoreaddressid;
    }

    @JsonProperty("deliverystoreaddressid")
    public void setDeliverystoreaddressid(String deliverystoreaddressid) {
        this.deliverystoreaddressid = deliverystoreaddressid;
    }

    @JsonProperty("deliverystoreaddresscity")
    public String getDeliverystoreaddresscity() {
        return deliverystoreaddresscity;
    }

    @JsonProperty("deliverystoreaddresscity")
    public void setDeliverystoreaddresscity(String deliverystoreaddresscity) {
        this.deliverystoreaddresscity = deliverystoreaddresscity;
    }

    @JsonProperty("deliverystoreaddresscountry")
    public String getDeliverystoreaddresscountry() {
        return deliverystoreaddresscountry;
    }

    @JsonProperty("deliverystoreaddresscountry")
    public void setDeliverystoreaddresscountry(String deliverystoreaddresscountry) {
        this.deliverystoreaddresscountry = deliverystoreaddresscountry;
    }

    @JsonProperty("deliveryaddressstreet1")
    public String getDeliveryaddressstreet1() {
        return deliveryaddressstreet1;
    }

    @JsonProperty("deliveryaddressstreet1")
    public void setDeliveryaddressstreet1(String deliveryaddressstreet1) {
        this.deliveryaddressstreet1 = deliveryaddressstreet1;
    }

    @JsonProperty("deliveryaddressstreet2")
    public String getDeliveryaddressstreet2() {
        return deliveryaddressstreet2;
    }

    @JsonProperty("deliveryaddressstreet2")
    public void setDeliveryaddressstreet2(String deliveryaddressstreet2) {
        this.deliveryaddressstreet2 = deliveryaddressstreet2;
    }

    @JsonProperty("deliveryaddresssuburb")
    public String getDeliveryaddresssuburb() {
        return deliveryaddresssuburb;
    }

    @JsonProperty("deliveryaddresssuburb")
    public void setDeliveryaddresssuburb(String deliveryaddresssuburb) {
        this.deliveryaddresssuburb = deliveryaddresssuburb;
    }

    @JsonProperty("deliveryinstructions")
    public String getDeliveryinstructions() {
        return deliveryinstructions;
    }

    @JsonProperty("deliveryinstructions")
    public void setDeliveryinstructions(String deliveryinstructions) {
        this.deliveryinstructions = deliveryinstructions;
    }

    @JsonProperty("isservicable")
    public String getIsservicable() {
        return isservicable;
    }

    @JsonProperty("isservicable")
    public void setIsservicable(String isservicable) {
        this.isservicable = isservicable;
    }

    @JsonProperty("rewardscardNumber")
    public String getRewardscardNumber() {
        return rewardscardNumber;
    }

    @JsonProperty("rewardscardNumber")
    public void setRewardscardNumber(String rewardscardNumber) {
        this.rewardscardNumber = rewardscardNumber;
    }

    @JsonProperty("teammember")
    public String getTeammember() {
        return teammember;
    }

    @JsonProperty("teammember")
    public void setTeammember(String teammember) {
        this.teammember = teammember;
    }

    @JsonProperty("trolleymergestatus")
    public String getTrolleymergestatus() {
        return trolleymergestatus;
    }

    @JsonProperty("trolleymergestatus")
    public void setTrolleymergestatus(String trolleymergestatus) {
        this.trolleymergestatus = trolleymergestatus;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
