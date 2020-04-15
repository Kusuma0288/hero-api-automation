package au.com.woolworths.apigee.model;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class TrolleyV3Response {
    private Object Order;
    private Object Trolley;
    private TrolleyResults Results;

    @Override
    public String toString() {
        return "TrolleyV3Response{" +
                "Order=" + Order +
                ", Trolley=" + Trolley +
                ", Results=" + Results +
                '}';
    }

    public Object getOrder() {
        return Order;
    }

    public void setOrder(Object order) {
        Order = order;
    }

    public Object getTrolley() {
        return Trolley;
    }

    public void setTrolley(Object trolley) {
        Trolley = trolley;
    }

    public TrolleyResults getResults() {
        return Results;
    }

    public void setResults(TrolleyResults results) {
        Results = results;
    }
}
