package au.com.woolworths.apigee.model;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)

public class Address {
  private Integer Id;
  private String Text;

  @Override
  public String toString() {
    return "Address{" +
            "Id='" + Id + '\'' +
            ", Text=" + Text +
            '}';
  }

  public Integer getId() {
    return Id;
  }

  public void setId(Integer id) {
    Id = id;
  }

  public String getText() {
    return Text;
  }

  public void setText(String text) {
    Text = text;
  }
}

