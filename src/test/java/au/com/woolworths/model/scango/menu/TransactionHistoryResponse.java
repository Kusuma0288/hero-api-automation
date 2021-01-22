package au.com.woolworths.model.scango.menu;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class TransactionHistoryResponse {
    private List<HistoryList> historylist = null;
    private String statusCode;
}


