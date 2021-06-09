package au.com.woolworths.model.apigee.store;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class ErrorDetail {
    private String ErrorCode;
    private String Message;
    private String StackTrace;
    private String Errors;
}
