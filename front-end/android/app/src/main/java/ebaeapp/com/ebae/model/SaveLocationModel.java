package ebaeapp.com.ebae.model;

import android.location.Location;

import ebaeapp.com.ebae.data.LocationSingleton;

/**
 * Created by Hung on 3/17/2017.
 */

public class SaveLocationModel {

    public static void updateCoordinates(Location location) {
        LocationSingleton locSingleton = LocationSingleton.getInstance();
        locSingleton.setCoordinates(location.getLatitude(),
                location.getLongitude());

    }
}
