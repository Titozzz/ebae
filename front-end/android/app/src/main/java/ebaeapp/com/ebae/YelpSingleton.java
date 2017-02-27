package ebaeapp.com.ebae;

import com.yelp.clientlib.connection.YelpAPI;
import com.yelp.clientlib.connection.YelpAPIFactory;

/**
 * Created by thiba on 26/02/2017.
 */

public class YelpSingleton {
  private static final String CONSUMER_KEY = "H97cBWqesMAn69Fsp_Gdrw";
  private static final String CONSUMER_SECRET = "IiXpuOUVutSV9SV1J_AnZcq-XN4";
  private static final String TOKEN = "dX-rO2VZALrd7TkPZiOren28BBMkSlpy";
  private static final String TOKEN_SECRET = "c49iiKz5GD68lsz6ZnjqHjpQxdE";

  private static YelpAPI instance = null;

  public static YelpAPI getInstance() {
    if (instance == null) {
      YelpAPIFactory apiFactory = new YelpAPIFactory(CONSUMER_KEY,CONSUMER_SECRET,TOKEN,TOKEN_SECRET);
      instance = apiFactory.createAPI();
    }
    return instance;
  }
}
