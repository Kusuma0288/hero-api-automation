package supervillain.herokuapp.com.context;

import supervillain.herokuapp.com.utils.SharedData;

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
