package au.com.woolworths.model.metis.card.view_gift_card;

import lombok.Data;

import java.util.List;

@Data
public class ViewGiftCards {
  private List<Item> items;
  private String helperText;
}
