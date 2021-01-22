package au.com.woolworths.model.scango.menu;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class HistoryList {
    private String cartid;
    private Integer storeid;
    private String storeName;
    private String txntime;
    private String txndatetime;
    private String txndatestring;
    private Double balancedue;
    private String txnid;
    private Integer totalquantity;
    private Double totalprice;
    private Double totalgst;
}
