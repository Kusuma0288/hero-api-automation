package au.com.woolworths.model.metis.activity;

import au.com.woolworths.model.metis.message.Message;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data

public class Groups {
  private String title;
  private List<Items> items;
  private Message message;
}