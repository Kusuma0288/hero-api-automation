package au.com.woolworths.model.trader;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class CountryOfOriginLabel {

  private String pngImageFile;
  private String svgImageFile;
  private String altText;
  private String countryOfOrigin;
  private String ingredientPercentage;
  private Object disclaimer;

}
