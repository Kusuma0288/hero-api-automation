package au.com.woolworths.model.scango.scanitems;
import lombok.Data;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class Instoreprice {
    private String cupuom;
    private Integer cupsize;
    private String uom;
    private Double pricegst;
    private Double cupprice;
}
