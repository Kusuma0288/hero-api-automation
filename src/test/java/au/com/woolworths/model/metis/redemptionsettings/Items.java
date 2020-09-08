package au.com.woolworths.model.metis.redemptionsettings;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class Items {
    private Boolean isSelected;
    private String icon;
    private String title;
    private String body;
    private ConfirmationMessages[] confirmationMessages;
}
