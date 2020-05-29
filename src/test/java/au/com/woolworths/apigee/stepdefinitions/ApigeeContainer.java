package au.com.woolworths.apigee.stepdefinitions;

import au.com.woolworths.apigee.model.ApigeeListResponse;
import au.com.woolworths.apigee.model.CheckoutPackagingPreferencesResponse;
import au.com.woolworths.apigee.model.CheckoutPackagingPreferencesResponse;
import au.com.woolworths.apigee.model.CheckoutPaymentSummaryResponse;

import java.util.HashMap;
import java.util.List;

public class ApigeeContainer {
    public String currentListId;
    public long listTimeStamp;
    public String defaultListId;
    public String currentFreeTextId;
    public ApigeeListResponse updatedListResponse;
    public int windowId;
    public String windowStartTime;
    public CheckoutPackagingPreferencesResponse[] packagingPreference;
    public String orderPickupDetails;
    public String windowDate;
    public Object windowTime;
}
