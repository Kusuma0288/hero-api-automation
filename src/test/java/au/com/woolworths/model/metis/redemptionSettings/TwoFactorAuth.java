package au.com.woolworths.model.metis.redemptionSettings;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class TwoFactorAuth {
    private String stepUpUrl;
}
