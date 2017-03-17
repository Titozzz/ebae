package ebaeapp.com.ebae.roll;

import android.app.Application;

import ebaeapp.com.ebae.data.YelpSingleton;
import ebaeapp.com.ebae.roll.ARoll;

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
