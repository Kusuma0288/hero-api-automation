package au.com.woolworths.stepdefinitions.trader;

import java.util.HashMap;
import java.util.List;

public class Container {
  String shopperDeliveryAddress;
  int trolleyQuantity;
  int fulfilmentStoreId;
  String deviceId;
  int guestAddressId;
  int deliveryAddressIdContainer;
  String signedUpEmail;
  String fulfilmentMethod;
  String trolleyItemNote;
  String stockCode;
  List<String> stockCodes;
  HashMap<Integer, String> deliveryAddresses = new HashMap<Integer, String>();
  String orderId;

  public int getFulfilmentStoreId() {
    return fulfilmentStoreId;
  }

  public void setFulfilmentStoreId(int fulfilmentStoreId) {
    this.fulfilmentStoreId = fulfilmentStoreId;
  }

  public int getTrolleyQuantity() {
    return trolleyQuantity;
  }

  public void setTrolleyQuantity(int trolleyQuantity) {
    this.trolleyQuantity = trolleyQuantity;
  }

  public String getShopperDeliveryAddress() {
    return shopperDeliveryAddress;
  }

  public void setShopperDeliveryAddress(String shopperDeliveryAddress) {
    this.shopperDeliveryAddress = shopperDeliveryAddress;
  }

  public String getDeviceId() {
    return deviceId;
  }

  public void setDeviceId(String deviceId) {
    this.deviceId = deviceId;
  }

  public int getGuestAddressId() {
    return guestAddressId;
  }

  public void setGuestAddressId(int guestAddressId) {
    this.guestAddressId = guestAddressId;
  }

  public String getTrolleyItemNote() {
    return trolleyItemNote;
  }

  public void setTrolleyItemNote(String trolleyItemNote) {
    this.trolleyItemNote = trolleyItemNote;
  }

  public String getStockCode() {
    return stockCode;
  }

  public void setStockCode(String stockCode) {
    this.stockCode = stockCode;
  }

  public HashMap<Integer, String> getDeliveryAddresses() {
    return deliveryAddresses;
  }

  public int getPrimaryaddress() {
    return guestAddressId;
  }

  public void setDeliveryAddresses(int addressID, String addressText) {
    deliveryAddresses.put(addressID, addressText);
  }

  public List<String> getStockCodes() {
    return stockCodes;
  }

  public void setStockCodes(List<String> stockCodes) {
    this.stockCodes = stockCodes;
  }

  public void removeAllItemsInDeliveryAddresses() {
    deliveryAddresses.clear();
  }

  public String getOrderId() {
    return orderId;
  }

  public void setOrderID(String orderID) {
    orderId = orderID;
  }

  public int getDeliveryAddressIdContainer() {
    return deliveryAddressIdContainer;
  }

  public void setdeliveryAddressIdContainer(int deliveryAddressId) {
    this.deliveryAddressIdContainer = deliveryAddressId;
  }

}
