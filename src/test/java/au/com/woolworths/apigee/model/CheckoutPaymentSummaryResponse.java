package au.com.woolworths.apigee.model;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CheckoutPaymentSummaryResponse {
    private Order Order;
    private Object PaymentMessages;
    private Object Results;

    @Override
    public String toString() {
        return "CheckoutPaymentSummaryResponse{" +
                "Order=" + Order +
                ", PaymentMessages=" + PaymentMessages +
                ", Results=" + Results +
                '}';
    }

    public Order getOrder() {
        return Order;
    }

    public void setOrder(au.com.woolworths.apigee.model.Order order) {
        Order = order;
    }

    public Object getPaymentMessages() {
        return PaymentMessages;
    }

    public void setPaymentMessages(Object paymentMessages) {
        PaymentMessages = paymentMessages;
    }

    public Object getResults() {
        return Results;
    }

    public void setResults(Object results) {
        Results = results;
    }

}