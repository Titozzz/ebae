package ebaeapp.com.ebae;

import android.app.Application;

/**
 * Created by thiba on 05/03/2017.
 */

public class CustomApplication extends Application {
  public CustomApplication() {
    YelpSingleton.setInstance();
  }

  private ARoll mRoll;

  public ARoll getRoll() {
    return mRoll;
  }

  public void setRoll(ARoll roll) {
    mRoll = roll;
  }
}
