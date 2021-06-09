package au.com.woolworths.model.apigee.address;

import au.com.woolworths.model.apigee.store.ErrorDetail;
import lombok.Data;
@Data
public class InvalidPostcode {
    private Integer httpStatusCode;
    private String errorCode;
    private String errorMessage;
    private ErrorDetail errorDetail;
}
