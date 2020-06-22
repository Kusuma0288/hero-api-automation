package au.com.woolworths.apigee.model;

import lombok.Data;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
@Data public class ApigeeLists {
  private String id;
  private String title;
  private String timestamp;
  private String url;
  private int productCount;
}
