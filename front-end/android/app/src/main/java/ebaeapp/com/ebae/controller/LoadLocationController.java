package ebaeapp.com.ebae.controller;

import ebaeapp.com.ebae.model.LoadLocationModel;

/**
 * Created by Hung on 3/11/2017.
 */

public class LoadLocationController {

    public static double findLatitude() {
        return LoadLocationModel.findLatitude();
    }
    public static double findLongitude() {

        return LoadLocationModel.findLongitude();
    }
}
