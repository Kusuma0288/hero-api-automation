package au.com.woolworths.model.metis.payment_session;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SessionData {

  private String primaryInstrumentId;
  private String[] secondaryInstruments;
  private String clientReference;
}
