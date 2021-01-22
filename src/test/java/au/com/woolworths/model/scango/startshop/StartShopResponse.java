package au.com.woolworths.model.scango.startshop;

import lombok.Data;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data

public class StartShopResponse {
    private String cartid;
    private String lastupdated;
    private String status;
    private String reason;
    private String storeid;
    private String rewardcardnumber;
    private String name;
    private String division;
    private String no;
    private String fraudresponse;
    private Boolean isrewardslinked;
    private StoreInfo storeInfo;
    private Transaction transaction;
    private String statusCode;
}
