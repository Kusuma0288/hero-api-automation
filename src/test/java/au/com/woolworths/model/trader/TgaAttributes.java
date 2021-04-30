package au.com.woolworths.model.trader;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
@Data
public class TgaAttributes {
  private String Directions;
  private String ProductWarnings;
  private String SuitableFor;
  private String StorageInstructions;
}