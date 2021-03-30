package au.com.woolworths.utils;

public final class URLResources {
  public static final String APIGEE_V2_SIGNUP = "/wow/v2/commerce/signup";
  public static final String APIGEE_V2_GUEST_LOGIN = "/wow/v2/commerce/guest";
  public static final String APIGEE_V2_SHOPPER_LOGIN = "/wow/v2/commerce/token";
  public static final String APIGEE_V2_RETRIEVE_TROLLEY = "wow/v2/trolley";
  public static final String APIGEE_V2_TROLLEY = "wow/v2/trolley/items/";
  public static final String APIGEE_V2_TROLLEY_CLEAR = "/wow/v2/trolley/clear";
  public static final String APIGEE_V3_RETRIEVE_TROLLEY = "wow/v3/trolley";
  public static final String APIGEE_V3_TROLLEY = "wow/v3/trolley/items/";
  public static final String APIGEE_V3_SEARCH = "/wow/v3/search";
  public static final String APIGEE_V2_SEARCH_ADDRESS = "/wow/v2/addresses";
  public static final String APIGEE_V2_SEARCH_ADDRESS_POSTCODE = "/wow/v2/addresses/stores";
  public static final String APIGEE_V3_FULFILMENT = "/wow/v3/fulfilment";
  public static final String APIGEE_V2_ADDRESS = "/wow/v2/addresses";
  public static final String APIGEE_V2_LIST_ADDRESSES = "/wow/v2/addresses";
  public static final String APIGEE_V2_IN_STORE = "/wow/v2/stores";
  public static final String APIGEE_V3_CATEGORIES = "/wow/v3/categories";
  public static final String APIGEE_V2_PRODUCTS = "/wow/v2/products";
  public static final String APIGEE_V2_LISTS = "/wow/v2/lists";
  public static final String APIGEE_V3_LISTS = "/wow/v3/lists";
  public static final String APIGEE_V2_UPDATE_LIST_FREETEXT = "/wow/v2/lists/{list_id}/items";
  public static final String APIGEE_V3_LIST_ITEMS = "/wow/v3/lists/{list_id}/items";
  public static final String APIGEE_V2_GET_LIST_BY_ID = "wow/v2/lists/{list_id}";
  public static final String APIGEE_V2_LIST_ITEMS = "wow/v2/lists/{list_id}/items";
  public static final String HERMES_V1_HOMEPAGE = "/hermes/iris/v1/pages/home";
  public static final String APIGEE_CHECKOUT = "/wow/checkout";
  public static final String HERMES_V1 = "/hermes/iris/v1";
  public static final String APIGEE_V2_SPECIALS = "/wow/v2/specials";
  public static final String APIGEE_CHECKOUT_PAYMENT_SUMMARY = "/wow/checkout/payment";
  public static final String APIGEE_PAYMENT_INSTRUMENTS = "/wow/v1/pay/instruments";
  public static final String APIGEE_PAYMENT_CARDS = "/wow/v1/pay/cards/initcapture";
  public static final String APIGEE_iFRAME_UAT = "https://iframe.nonprod.payments.woolworths.com.au/tokenisation/creditcard";
  public static final String APIGEE_iFRAME_TEST = "https://iframe.sit.payments.woolworths.com.au/tokenisation/creditcard";
  public static final String IFRAME_CREDITCARD = "/tokenisation/creditcard";
  public static final String IFRAME_CVVANDEXPIRY = "/container-ws/tokens/cvvandexpiry";
  public static final String APIGEE_PAYMENT_DIGITALPAY = "/wow/v2/commerce/checkout/payment/digitalpay";
  public static final String HERMES_V1_GRAPHQL = "/hermes/iris/v1/graphql";
  public static final String APIGEE_V2_ORDER_CONFIRMATION = "/wow/v2/commerce/orders/";

  public static final String METIS_REWARDS_LINK = "/zeus/metis/v1/rewards/link";
  public static final String METIS_LOGIN = "/zeus/metis/v1/rewards/login";
  public static final String METIS_REWARDS_SPECIALS = "/zeus/metis/v1/rewards/specials";
  public static final String METIS_REWARDS_GRAPHQL = "/zeus/metis/v1/rewards/graphql";
  public static final String METIS_LOGOUT = "/zeus/metis/v1/rewards/logout";
  public static final String METIS_TOKEN = "/zeus/metis/v1/rewards/token";
  public static final String METIS_CONFIG = "/zeus/metis/v1/rewards/config";
  public static final String METIS_PARTNER_DETAILS = "/zeus/metis/v1/rewards/partner";
  public static final String METIS_QRID = "/zeus/metis/v1/api/digipay/instore/customer/payment/session/qr/:qrId";
  public static final String METIS_PAYMENT_SESSION = "/zeus/metis/v1/api/digipay/instore/customer/payment/session/:paymentSessionId";
  public static final String METIS_TRANSACTIONS = "/zeus/metis/v1/api/digipay/instore/customer/transactions";
  public static final String HELIOS_LOGIN = "/helios/v1/api/login";
  public static final String DIGIPAY_IN_STORE_PAYMENT_SESSION = "wow/v1/pay/instore/merchant/payment/session";
  public static final String DIGIPAY_IN_STORE_PAYMENTS = "wow/v1/pay/instore/merchant/payments";

  //Trader URL
  public static final String TRADER_V2_GUEST = "/Authenticate/LoginAsGuest";
  public static final String TRADER_V3_GUEST = "/api/v3/shoppers/guest";
  public static final String TRADER_V2_AUTH_SHOPPER = "/apis/v2/auth/shopper";
  public static final String TRADER_V2_SEARCH_ADDRESS = "/apis/v2/search/addresses";
  public static final String TRADER_V3_ADDRESS = "/api/v3/address";
  public static final String TRADER_V3_CHECKOUT_ADDRESS = "api/v3/checkout/address/";
  public static final String TRADER_V2_SHOPPER_SIGNUP = "apis/v2/shoppers/signup";
  public static final String TRADER_V2_MYACCOUNT_ADDRESS = "apis/v2/myaccount/addresses/";
  public static final String TRADER_V2_CLEAR_TROLLEY = "apis/v2/trolley/clear";
  public static final String TRADER_V2_GET_TROLLEY = "apis/v2/trolley";
  public static final String TRADER_V2_ADD_UPDATE_TROLLEY = "apis/v2/trolley/items";
  public static final String TRADER_V3_ADDRESS_SEARCH = "api/v3/address/search";
  public static final String TRADER_V2_DELETE_ADDRESS = "apis/v2/myaccount/addresses/";
  public static final String TRADER_V2_PICKUP_STORES = "apis/v2/pickupstores";
  public static final String TRADER_V2_CHECKOUT = "/apis/v2/checkout";
  public static final String TRADER_V3_CHECKOUT = "/api/v3/checkout";
  public static final String TRADER_V3_CAN_LEAVE_UNATTENDED = "/api/v3/checkout/canleaveunattended";
  public static final String TRADER_V2_PICKUP_SUBURB = "apis/v2/pickupstores/suburbs";
  public static final String TRADER_V2_PICKUP_SEARCH = "apis/v2/pickupstores/search";
  public static final String TRADER_V2_PRODUCTS_SEARCH = "apis/v2/products/search";
  public static final String TRADER_V2_PACKAGING_PREFERENCE = "/apis/v2/Delivery/PackagingPreferences";
  public static final String TRADER_V2_CHECKOUTDELIVERYWINDOWS = "/apis/v2/deliverywindows/courier";
  public static final String TRADER_V2_CHECKOUTPICKUPWINDOWS = "/apis/v2/deliverywindows/pickup";
  public static final String TRADER_V2_CHECKOUTWINDOWS = "/apis/v2/checkout/windows";
  public static final String TRADER_V3_CHECKOUTDELIVERYWINDOWS = "/api/v3/checkout/windows";
  public static final String TRADER_V3_CHECKOUTWINDOWS = "/api/v3/checkout/window";
  public static final String TRADER_V3_PRODUCTS_WEEKLYPICKS = "api/v3/product/myweeklypicks";
  public static final String TRADER_V2_PRODUCT_DETAIL = "apis/v2/product/detail/{stockcode}";
  public static final String TRADER_V2_PAYMENT_PAYPAL = "apis/v2/checkout/payment/paypal";
  public static final String TRADER_V2_ORDER = "apis/v2/orders/{orderId}";
  public static final String TRADER_V3_ORDER = "/api/v3/myorders";
  public static final String TRADER_V2_SHOPPING_AISLES = "/apis/v2/vsaisles";
  public static final String TRADER_V2_PAST_SHOPPING_LIST = "/apis/v2/lists/pastshops";
  public static final String TRADER_V2_SPECIALS_GROUP = "api/v3/product/specials/groups";
  public static final String TRADER_V2_SPECIALS_GROUP_DETAILS = "api/v3/product/specials";
  public static final String TRADER_V3_PRODUCT_GROUP = "api/v3/product/productgroup";
  public static final String V3_SEARCH = "api/v3/product/search";

  public static final String SCANGO_LOGIN = "scan/go/v1/login/wow/online";
  public static final String SCANGO_START_SHOP = "scan/go/tapon";
  public static final String SCANGO_ADD_ITEM  = "scan/go/cart/items";
  public static final String SCANGO_CHECKOUT = "scan/go/checkout";
  public static final String SCANGO_LIST_INSTRUMENTS = "wow/v1/pay/instruments";
  public static final String SCANGO_PAYMENT = "scan/go/payment";
  public static final String SCANGO_REWARDS_TOKEN = "/wx/v1/rewardspartner/secure/code2login/token";
  public static final String SCANGO_LOAD_OR_DELETE_CART = "scan/go/cart";
  public static final String SCANGO_VERIFY_CART = "scango/store/kiosk/cart/verify";
  public static final String SCANGO_TRANSACTION_HISTORY = "scan/go/transaction/history";
  public static final String SCANGO_VEIW_RECEIPT = "scan/go/receipt/view";
  public static final String SCANGO_UPDATE_ITEM = "/scan/go/v2/cart/items/";
  public static final String SCANGO_REMOVE_ITEM = "/scan/go/cart/items/";
  public static final String SCANGO_USER_PROFILE = "/scan/go/userprofile";
  public static final String SCANGO_KIOSK_CHECKOUT = "/scango/store/kiosk/cart/checkout";
  public static final String SCANGO_KIOSK_LOAD_CART = "/scango/store/kiosk/cart";
  public static final String SCANGO_KIOSK_PAYMENT = "/scango/store/kiosk/cart/payment/confirm";
  public static final String SCANGO_KIOSK_REMOVE_INTERVENTION = "/scango/store/kiosk/item/intervention/remove/";
  public static final String SCANGO_KIOSK_CONFIG = "/scango/store/kiosk/config";
  public static final String SCANGO_KIOSK_LOGIN = "scango/store/kiosk/team/login";
  public static final String SCANGO_KIOSK_DELETE_CART = "scango/store/kiosk/cart/void";
  public static final String SCANGO_READ_FIRESTORE = "/wow/firestore/v1/document/read";
  public static final String SCANGO_REFRESH_TOKEN = "/scan/go/v2/rewards/token/";
  public static final String SCANGO_UPSERT_FIRESTORE = "/wow/firestore/v1/document/upsert";
}
