package au.com.woolworths.model.scango.startshop;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class Preference {
    private Boolean isemailinvoiceopted;
}
