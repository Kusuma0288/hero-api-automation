package au.com.woolworths.apigee.model;

public class CheckoutPackagingPreferencesResponse {

  private int Id;
  private String Code;
  private String Name;
  private int Cost;
  private boolean IsSelected;
  private String Image;
  private String Description;
  private String FeeLabel;

  @Override
  public String toString() {
    return "CheckoutPackagingPreferencesResponse{" +
            "  Id=" + Id +
            ", Code=" + Code +
            ", Name='" + Name + '\'' +
            ", Cost=" + Cost +
            ", IsSelected=" + IsSelected +
            ", Image=" + Image +
            ", Description=" + Description +
            ", FeeLabel=" + FeeLabel +
            '}';
  }

  public int getId() {
    return Id;
  }

  public void setId(int id) {
    Id = id;
  }

  public String getCode() {
    return Code;
  }

  public void setCode(String code) {
    Code = code;
  }

  public String getName() {
    return Name;
  }

  public void setName(String name) {
    Name = name;
  }

  public int getCost() {
    return Cost;
  }

  public void setCost(int cost) {
    Cost = cost;
  }

  public boolean getIsSelected() {
    return IsSelected;
  }

  public void setIsSelected(boolean isSelected) {
    IsSelected = isSelected;
  }

  public String getImage() {
    return Image;
  }

  public void setImage(String image) {
    Image = image;
  }

  public String getDescription() {
    return Description;
  }

  public void setDescription(String description) {
    Description = description;
  }

  public String getFeeLabel() {
    return FeeLabel;
  }

  public void setFeeLabel(String feeLabel) {
    FeeLabel = feeLabel;
  }

}
