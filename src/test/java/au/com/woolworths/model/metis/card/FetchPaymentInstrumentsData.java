package au.com.woolworths.model.metis.card;

import lombok.Data;

@Data
public class FetchPaymentInstrumentsData {

  private PaymentInstruments[] paymentInstruments;
}