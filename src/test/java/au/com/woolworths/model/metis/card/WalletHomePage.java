package au.com.woolworths.model.metis.card;

import lombok.Data;

@Data
public class WalletHomePage {

  private String __typename;
  private String title;
  private String content;
  private String action;
  private boolean showBadge;
}