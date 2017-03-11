package ebaeapp.com.ebae;


import com.yelp.fusion.client.connection.YelpFusionApi;
import com.yelp.fusion.client.connection.YelpFusionApiFactory;


/**
 * Created by thiba on 26/02/2017.
 */

public class YelpSingleton {
  private static final String APP_KEY = "TaA11NOtntDvGNZGbcipWw";
  private static final String APP_SECRET = "a9OF1ZY7PDR7i3qniv3te5BwXDsqBUSOMJjEpYUExLBZc3M7oWeUTMeIy5JVHdyi";

  private static YelpFusionApi instance = null;

  public static YelpFusionApi getInstance() {
    return instance;
  }

  public static void setInstance() {
    new Thread(() -> {
      try  {
        YelpFusionApiFactory apiFactory = new YelpFusionApiFactory();
        instance = apiFactory.createAPI(APP_KEY, APP_SECRET);
      } catch (Exception e) {
        e.printStackTrace();
      }
    }).start();
  }
}
