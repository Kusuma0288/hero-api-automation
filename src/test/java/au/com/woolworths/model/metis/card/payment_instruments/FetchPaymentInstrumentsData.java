package au.com.woolworths.model.metis.card.payment_instruments;

import lombok.Data;

@Data
public class FetchPaymentInstrumentsData {

  private PaymentInstruments[] paymentInstruments;
}