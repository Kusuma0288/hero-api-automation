package au.com.woolworths.model.metis.payment_session;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SessionRequest {

  private SessionData data;
  private SessionMeta meta;
}
