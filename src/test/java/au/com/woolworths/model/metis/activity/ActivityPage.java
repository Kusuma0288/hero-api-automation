package au.com.woolworths.model.metis.activity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data

public class ActivityPage {
  private ActivityData data;
  private String message;
}