package au.com.woolworths.model.scango.menu;

import au.com.woolworths.model.scango.payment.PaymentInfo;
import au.com.woolworths.model.scango.scanitems.Discounts;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class ViewReceiptResponse {
    private String cartid;
    private String barcode;
    private String receiptno;
    private Transaction transaction;
    private Integer totalquantity;
    private Integer merchanttotal;
    private Double totalprice;
    private Double balancedue;
    private Double totalgst;
    private String status;
    private String rewardcardnumber;
    private Integer rewardsaccrued;
    private Boolean islmsonline;
    private Boolean generatePdfEnabled;
    private Double totalsavings;
    private Discounts discounts;
    private ReceiptStoreInfo storeInfo;
    private Vouchers vouchers;
    private List<LineItem> lineItems = null;
    private List<Object> totaldiscounts = null;
    private List<PaymentInfo> paymentInfo = null;
    private List<Object> voucherInfo = null;
    private List<Offer> offers = null;
    private String receiptMessage;
    private String statusCode;
}
