package au.com.woolworths.apigee.model;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class FulFilmentResponse {
    private Pickup Pickup;
    private FulfilmentMethod Fulfilment;
    private FulFilmentResults Results;

    @Override
    public String toString() {
        return "FulFilmentResponse{" +
                "Pickup=" + Pickup +
                ", Fulfilment=" + Fulfilment +
                ", Results=" + Results +
                '}';
    }

    public au.com.woolworths.apigee.model.Pickup getPickup() {
        return Pickup;
    }

    public void setPickup(au.com.woolworths.apigee.model.Pickup pickup) {
        Pickup = pickup;
    }

    public FulfilmentMethod getFulfilment() {
        return Fulfilment;
    }

    public void setFulfilment(FulfilmentMethod fulfilment) {
        Fulfilment = fulfilment;
    }

    public FulFilmentResults getResults() {
        return Results;
    }

    public void setResults(FulFilmentResults results) {
        Results = results;
    }
}
