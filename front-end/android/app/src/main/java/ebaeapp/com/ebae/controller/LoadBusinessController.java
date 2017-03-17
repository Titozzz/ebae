package ebaeapp.com.ebae.controller;


import org.json.JSONObject;

import ebaeapp.com.ebae.RestaurantView;
import ebaeapp.com.ebae.model.LoadBusinessModel;
import ebaeapp.com.ebae.HistoryView;

/**
 * Created by Hung on 3/7/2017.
 */

public class LoadBusinessController {
    //used from restaurant activity as a helper in saving new businesses
    public static JSONObject[] loadBusinesses(RestaurantView restAct) {
        return LoadBusinessModel.loadFromFile(restAct);
    }
    //used from history activity to actually load stored businesses
    public static JSONObject[] loadBusinesses(HistoryView histAct) {
        return LoadBusinessModel.loadFromFile(histAct);
    }
}
