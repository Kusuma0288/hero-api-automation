package au.com.woolworths.model.scango.kiosk;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class KioskIs {
    private Boolean isbigkioskrequired;
    private Boolean bagcheckrequired;
    private Boolean hasintervention;
    private Boolean enableinapppayment;
    private Boolean isbagcheckcompleted;
    private Boolean kioskpaymentrequired;
    private Boolean finishTransaction;
}