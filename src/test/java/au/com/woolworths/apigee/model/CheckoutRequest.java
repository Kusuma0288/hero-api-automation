package au.com.woolworths.apigee.model;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)

public class CheckoutRequest {
    private String date;
    private int window;

    @Override
    public String toString() {
        return "CheckoutRequest{" +
                "date='" + date + '\'' +
                "window='" + window + '\'' +
                '}';
    }

    public String getDates()
    {
        return date;
    }
    public void setDate(String date)
    {
        this.date = date;
    }

    public int getWindow()
    {
        return window;
    }
    public void setWindow(int window)
    {
        this.window = window;
    }

}
