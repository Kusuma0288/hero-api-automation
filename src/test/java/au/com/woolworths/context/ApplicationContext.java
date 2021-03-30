package au.com.woolworths.context;

import au.com.woolworths.utils.SharedData;

public class ApplicationContext {

  private ApplicationContext() {
  }

  public static SharedData getSharedData() {
    return Singleton.INSTANCE.getSharedData();
  }

  private enum Singleton {

    INSTANCE;

    private SharedData sharedData;

    Singleton() {
      sharedData = new SharedData();
    }

    public SharedData getSharedData() {
      return sharedData;
    }
  }
}
