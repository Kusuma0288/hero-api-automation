package au.com.woolworths.model.iris.graphql.list;

import com.fasterxml.jackson.annotation.JsonInclude;

@lombok.Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CreatedLists {
  private int id;
  private String title;
  private String color;
  private String referenceId;
  private double timestamp;
}