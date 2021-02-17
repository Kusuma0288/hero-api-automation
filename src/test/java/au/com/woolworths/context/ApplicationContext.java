package au.com.woolworths.context;

import au.com.woolworths.utils.SharedData;

public class ApplicationContext {

  private ApplicationContext() {
  }

  private enum Singleton {

    INSTANCE;

    private final SharedData sharedData;

    Singleton() {
      sharedData = new SharedData();
    }

    public SharedData getSharedData() {
      return sharedData;
    }
  }

  public static SharedData getSharedData() {
    return Singleton.INSTANCE.getSharedData();
  }
}
