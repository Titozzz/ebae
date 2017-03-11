package ebaeapp.com.ebae;

import android.location.Location;

/**
 * Created by Hung on 3/11/2017.
 */

public class LocationUpdateAction {

    public static void updateCoordinates(Location location) {
        LocationSingleton locSingleton = LocationSingleton.getInstance();
        locSingleton.setCoordinates(location.getLatitude(),
                                    location.getLongitude());

    }
}
