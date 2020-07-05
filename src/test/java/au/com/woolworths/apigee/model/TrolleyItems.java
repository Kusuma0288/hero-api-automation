package au.com.woolworths.apigee.model;

import lombok.Data;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Arrays;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data public class TrolleyItems {
  private List<Item> items;
  private boolean replaceTrolley;
}
