package au.com.woolworths.model.apigee.address;

import lombok.Data;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class AddressRequest {

  private String AmasId;

}

