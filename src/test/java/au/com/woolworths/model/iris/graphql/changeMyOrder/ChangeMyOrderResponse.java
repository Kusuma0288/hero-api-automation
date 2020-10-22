package au.com.woolworths.model.iris.graphql.changeMyOrder;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
//import lombok.Data;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@lombok.Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ChangeMyOrderResponse {
  private Data data;
}
