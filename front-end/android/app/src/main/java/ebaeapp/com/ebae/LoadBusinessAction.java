package ebaeapp.com.ebae;

import com.yelp.clientlib.entities.Business;

import org.json.JSONObject;

/**
 * Created by Hung on 3/7/2017.
 */

public class LoadBusinessAction {
    public static JSONObject[] loadBusinesses(RestaurantActivity restAct) {
        return LoadBusinessManager.loadFromFile(restAct);
    }
    public static JSONObject[] loadBusinesses(HistoryActivity histAct) {
        return LoadBusinessManager.loadFromFile(histAct);
    }
}
