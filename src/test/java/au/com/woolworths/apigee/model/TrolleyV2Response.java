package au.com.woolworths.apigee.model;

import lombok.Data;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Arrays;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data public class TrolleyV2Response {
  private double totaltrolleyprice;
  private int savingsontrolley;
  private int totalproducts;
  private int deliveryfee;
  private List<TrolleyItemsListResponse> items;
  private Object wowrewardssummary;
  private Object loyalty;
  private Object[] errors;
}
