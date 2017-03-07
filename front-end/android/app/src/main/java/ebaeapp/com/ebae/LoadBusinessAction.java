package ebaeapp.com.ebae;

import com.yelp.clientlib.entities.Business;

/**
 * Created by Hung on 3/7/2017.
 */

public class LoadBusinessAction {
    public static Business[] loadBusinesses(RestaurantActivity restAct) {
        return LoadBusinessManager.loadFromFile(restAct);
    }
}
