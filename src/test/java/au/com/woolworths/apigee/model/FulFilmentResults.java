package au.com.woolworths.apigee.model;

public class FulFilmentResults {
    private ResultStatus SetPickupAddress;
    private ResultStatus PickupStores;

    @Override
    public String toString() {
        return "FulFilmentResults{" +
                "SetPickupAddress=" + SetPickupAddress +
                ", PickupStores=" + PickupStores +
                '}';
    }

    public ResultStatus getSetPickupAddress() {
        return SetPickupAddress;
    }

    public void setSetPickupAddress(ResultStatus setPickupAddress) {
        SetPickupAddress = setPickupAddress;
    }

    public ResultStatus getPickupStores() {
        return PickupStores;
    }

    public void setPickupStores(ResultStatus pickupStores) {
        PickupStores = pickupStores;
    }
}
