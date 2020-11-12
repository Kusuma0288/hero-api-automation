package au.com.woolworths.model.metis.fuelvoucher;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class FuelVoucherList {

  private String instructions;
  private InstructionsButton instructionsButton;
  private List<Object> items;
  private Message message;
  private String title;
}
