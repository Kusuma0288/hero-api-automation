package au.com.woolworths.model.scango.menu;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class ReceiptIs {
    private Boolean promotionalitem;
    private Boolean linkedpromo;
    private Boolean taxeditem;
}
