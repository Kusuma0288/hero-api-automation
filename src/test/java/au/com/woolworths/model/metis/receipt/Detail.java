package au.com.woolworths.model.metis.receipt;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class Detail {
  private String content;
  private String division;
  private String iconUrl;
  private String storeNo;
  private String title;
  private Header header;
  private String footer;
  private String headerImageUrl;
  private String total;
  private String transactionDetails;
  private String abnAndStore;
  private String savings;
  private Gst gst;
  private ReceiptTotal receiptTotal;
  private List<Sections> sections;
  private Barcode barcode;
  private List<Discounts> discounts;
  private List<Payments> payments;
  private List<SummaryItems> summaryItems;
  private List<Items> items;
  private Object info;
}
