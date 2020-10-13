
package au.com.woolworths.model.metis.fuelvoucher;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class FuelVoucherList {

  private String instructions;
  private InstructionsButton instructionsButton;
  private List<Object> items;
  private Message message;
  private String title;
}
