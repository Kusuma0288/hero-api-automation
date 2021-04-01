package au.com.woolworths.model.metis.account;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class SectionsItem {
  private String title;
  private List <OptionsItem> options;
}