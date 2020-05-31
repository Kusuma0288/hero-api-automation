package au.com.woolworths.apigee.model;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Order {
    private Object PrimaryAddress;
    private DeliveryDetails Delivery;
    private PickupDetails Pickup;
    private Object Fulfilment;
    private String Instructions;
    private double Savings;
    private int TeamDiscount;
    private int OrderDiscount;
    private int Subtotal;
    private int PackagingFee;
    private String PackagingFeeLabel;
    private int DeliveryFee;
    private int DeliveryFeeDiscount;
    private int DeliveryFeeBeforeDiscount;
    private int BalanceToPay;
    private int TotalIncludingGst;
    private int WowRewardsPaymentAmount;
    private int RedeemableWowRewardsDollars;
    private int DeferredWowRewardsDollars;
    private int StoreCreditTotal;
    private String EdrNumber;
    private Object Loyalty;
    private Object Discounts;
    private Object GiftCardPayments;
    private Object AvailableOrderItems;
    private Object UnavailableOrderItems;
    private Object RestrictedOrderItems;
    private Object ExceededSupplyLimitProducts;
    private Object RestrictedProductsByDeliveryMethod;
    private Object RestrictedProductsByDeliPlatter;
    private Object Errors;
    private boolean CanProceedToPayment;
    private Object OrderTotalsTableData;
    private Object LeaveUnattended;

    @Override
    public String toString() {
        return "Order {" +
                "PrimaryAddress=" + PrimaryAddress + ", Delivery=" + Delivery + "Pickup=" + Pickup +
                "Fulfilment=" + Fulfilment +
                "Instructions=" + Instructions +
                "Savings=" + Savings +
                "TeamDiscount=" + TeamDiscount + "OrderDiscount=" + OrderDiscount +
                "SubTotal=" + Subtotal +
                "PackagingFee=" + PackagingFee +
                "PackagingFeeLabel=" + PackagingFeeLabel +
                "DeliveryFee=" + DeliveryFee + "DeliveryFeeDiscount=" + DeliveryFeeDiscount +
                "DeliveryFeeBeforeDiscount=" + DeliveryFeeBeforeDiscount +
                "BalanceToPay=" + BalanceToPay +
                "TotalIncludingGst=" + TotalIncludingGst +
                "WowRewardsPaymentAmount=" + WowRewardsPaymentAmount + "RedeemableWowRewardsDollars=" + RedeemableWowRewardsDollars +
                "DeferredWowRewardsDollars=" + DeferredWowRewardsDollars +
                "StoreCreditTotal=" + StoreCreditTotal +
                "EdrNumber=" + EdrNumber +
                "Loyalty=" + Loyalty + "Discounts=" + Discounts + "GiftCardPayments=" + GiftCardPayments + "AvailableOrderItems=" + AvailableOrderItems
                + "UnavailableOrderItems=" + UnavailableOrderItems + "RestrictedOrderItems=" + RestrictedOrderItems + "ExceededSupplyLimitProducts=" +
                ExceededSupplyLimitProducts + "RestrictedProductsByDeliveryMethod=" + RestrictedProductsByDeliveryMethod + "RestrictedProductsByDeliPlatter="
                + RestrictedProductsByDeliPlatter + "Errors=" + Errors + "CanProceedToPayment=" + CanProceedToPayment + "OrderTotalsTableData=" + OrderTotalsTableData
                + "LeaveUnattended=" + LeaveUnattended + '}';

    }

    public Object getPrimaryAddress() {
        return PrimaryAddress;
    }

    public void setPrimaryAddress(Object primaryAddress) {
        PrimaryAddress = primaryAddress;
    }

    public DeliveryDetails getDelivery() {
        return Delivery;
    }

    public void setDelivery(DeliveryDetails delivery) {
        Delivery = delivery;
    }

    public PickupDetails getPickup() {
        return Pickup;
    }

    public void setPickup(PickupDetails pickup) {
        Pickup = pickup;
    }

    public Object getFulfilment() {
        return Fulfilment;
    }

    public void setFulfilment(Object fulfilment) {
        Fulfilment = fulfilment;
    }

    public String getInstructions() {
        return Instructions;
    }

    public void setInstructions(String instructions) {
        Instructions = instructions;
    }

    public double getSavings() {
        return Savings;
    }

    public void setSavings(double savings) {
        Savings = savings;
    }

    public int getTeamDiscount() {
        return TeamDiscount;
    }

    public void setTeamDiscount(int teamDiscount) {
        TeamDiscount = teamDiscount;
    }

    public int getOrderDiscount() {
        return OrderDiscount;
    }

    public void setOrderDiscount(int orderDiscount) {
        OrderDiscount = orderDiscount;
    }

    public int getSubtotal() {
        return Subtotal;
    }

    public void setSubtotal(int subtotal) {
        Subtotal = subtotal;
    }

    public int getPackagingFee() {
        return PackagingFee;
    }

    public void setPackagingFee(int packagingFee) {
        PackagingFee = packagingFee;
    }

    public String getPackagingFeeLabel() {
        return PackagingFeeLabel;
    }

    public void setPackagingFeeLabel(String packagingFeeLabel) {
        PackagingFeeLabel = packagingFeeLabel;
    }

    public int getDeliveryFee() {
        return DeliveryFee;
    }

    public void setDeliveryFee(int deliveryFee) {
        DeliveryFee = deliveryFee;
    }

    public int getDeliveryFeeDiscount() {
        return DeliveryFeeDiscount;
    }

    public void setDeliveryFeeDiscount(int deliveryFeeDiscount) {
        DeliveryFeeDiscount = deliveryFeeDiscount;
    }

    public int getDeliveryFeeBeforeDiscount() {
        return DeliveryFeeBeforeDiscount;
    }

    public void setDeliveryFeeBeforeDiscount(int deliveryFeeBeforeDiscount) {
        DeliveryFeeBeforeDiscount = deliveryFeeBeforeDiscount;
    }

    public int getBalanceToPay() {
        return BalanceToPay;
    }

    public void setBalanceToPay(int balanceToPay) {
        BalanceToPay = balanceToPay;
    }

    public int getTotalIncludingGst() {
        return TotalIncludingGst;
    }

    public void setTotalIncludingGst(int totalIncludingGst) {
        TotalIncludingGst = totalIncludingGst;
    }

    public int getWowRewardsPaymentAmount() {
        return WowRewardsPaymentAmount;
    }

    public void setWowRewardsPaymentAmount(int wowRewardsPaymentAmount) {
        WowRewardsPaymentAmount = wowRewardsPaymentAmount;
    }

    public int getRedeemableWowRewardsDollars() {
        return RedeemableWowRewardsDollars;
    }

    public void setRedeemableWowRewardsDollars(int redeemableWowRewardsDollars) {
        RedeemableWowRewardsDollars = redeemableWowRewardsDollars;
    }

    public int getDeferredWowRewardsDollars() {
        return DeferredWowRewardsDollars;
    }

    public void setDeferredWowRewardsDollars(int deferredWowRewardsDollars) {
        DeferredWowRewardsDollars = deferredWowRewardsDollars;
    }

    public int getStoreCreditTotal() {
        return StoreCreditTotal;
    }

    public void setStoreCreditTotal(int storeCreditTotal) {
        StoreCreditTotal = storeCreditTotal;
    }

    public String getEdrNumber() {
        return EdrNumber;
    }

    public void setEdrNumber(String edrNumber) {
        EdrNumber = edrNumber;
    }

    public Object getLoyalty() {
        return Loyalty;
    }

    public void setLoyalty(Object loyalty) {
        Loyalty = loyalty;
    }

    public Object getDiscounts() {
        return Discounts;
    }

    public void setDiscounts(Object discounts) {
        Discounts = discounts;
    }

    public Object getGiftCardPayments() {
        return GiftCardPayments;
    }

    public void setGiftCardPayments(Object giftCardPayments) {
        GiftCardPayments = giftCardPayments;
    }

    public Object getAvailableOrderItems() {
        return AvailableOrderItems;
    }

    public void setAvailableOrderItems(Object availableOrderItems) {
        AvailableOrderItems = availableOrderItems;
    }

    public Object getUnavailableOrderItems() {
        return UnavailableOrderItems;
    }

    public void setUnavailableOrderItems(Object unavailableOrderItems) {
        UnavailableOrderItems = unavailableOrderItems;
    }

    public Object getRestrictedOrderItems() {
        return RestrictedOrderItems;
    }

    public void setRestrictedOrderItems(Object restrictedOrderItems) {
        RestrictedOrderItems = restrictedOrderItems;
    }

    public Object getExceededSupplyLimitProducts() {
        return ExceededSupplyLimitProducts;
    }

    public void setExceededSupplyLimitProducts(Object exceededSupplyLimitProducts) {
        ExceededSupplyLimitProducts = exceededSupplyLimitProducts;
    }

    public Object getRestrictedProductsByDeliveryMethod() {
        return RestrictedProductsByDeliveryMethod;
    }

    public void setRestrictedProductsByDeliveryMethod(Object restrictedProductsByDeliveryMethod) {
        RestrictedProductsByDeliveryMethod = restrictedProductsByDeliveryMethod;
    }

    public Object getRestrictedProductsByDeliPlatter() {
        return RestrictedProductsByDeliPlatter;
    }

    public void setRestrictedProductsByDeliPlatter(Object restrictedProductsByDeliPlatter) {
        RestrictedProductsByDeliPlatter = restrictedProductsByDeliPlatter;
    }

    public Object getErrors() {
        return Errors;
    }

    public void setErrors(Object errors) {
        Errors = errors;
    }

    public boolean isCanProceedToPayment() {
        return CanProceedToPayment;
    }

    public void setCanProceedToPayment(boolean canProceedToPayment) {
        CanProceedToPayment = canProceedToPayment;
    }

    public Object getOrderTotalsTableData() {
        return OrderTotalsTableData;
    }

    public void setOrderTotalsTableData(Object orderTotalsTableData) {
        OrderTotalsTableData = orderTotalsTableData;
    }

    public Object getLeaveUnattended() {
        return LeaveUnattended;
    }

    public void setLeaveUnattended(Object leaveUnattended) {
        LeaveUnattended = leaveUnattended;
    }
}
