package au.com.woolworths.model.scango.firstore;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class FirestoreReadDocTeamMemberBarcodeResponse {
  private String result;
  private Entity[] entities;
}
