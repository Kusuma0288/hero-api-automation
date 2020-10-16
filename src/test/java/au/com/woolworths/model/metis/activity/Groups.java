package au.com.woolworths.model.metis.activity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data

public class Groups {
  private String title;
  private List<Items> items;
}