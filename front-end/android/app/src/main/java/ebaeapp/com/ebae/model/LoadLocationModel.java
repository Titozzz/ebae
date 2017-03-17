package ebaeapp.com.ebae.model;

import ebaeapp.com.ebae.data.LocationSingleton;

/**
 * Created by Hung on 3/17/2017.
 */

public class LoadLocationModel {

    public static double findLatitude() {
        return LocationSingleton.getInstance().getLatitude();
    }
    public static double findLongitude() {

        return LocationSingleton.getInstance().getLongitude();
    }
}
