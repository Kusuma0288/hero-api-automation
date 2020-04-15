package au.com.woolworths.apigee.context;

import au.com.woolworths.apigee.stepdefinitions.ApigeeSharedData;

public class ApigeeApplicationContext {

    private ApigeeApplicationContext() {
    }

    private enum Singleton {

        INSTANCE;

        private ApigeeSharedData sharedData;

        Singleton() {
            sharedData = new ApigeeSharedData();
        }

        public ApigeeSharedData getSharedData() {
            return sharedData;
        }
    }

    public static ApigeeSharedData getSharedData() {
        return Singleton.INSTANCE.getSharedData();
    }
}
