package au.com.woolworths.model.iris.graphql.list;
import com.fasterxml.jackson.annotation.JsonInclude;

@lombok.Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FreeTextItems {
  private int id;
  private String text;
  private double timestamp;
  private boolean checked;
  private String referenceId;
}