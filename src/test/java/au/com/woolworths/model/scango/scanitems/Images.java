package au.com.woolworths.model.scango.scanitems;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true) //TODO fix it
@Data
public class Images {
    private List<String> _360 = null;
    private String thumbnail;
    private List<String> details = null;
}
