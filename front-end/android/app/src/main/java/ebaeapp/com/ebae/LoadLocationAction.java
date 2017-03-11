package ebaeapp.com.ebae;

/**
 * Created by Hung on 3/11/2017.
 */

public class LoadLocationAction {

    public static double findLatitude() {
        return LocationSingleton.getInstance().getLatitude();
    }
    public static double findLongitude() {
        return LocationSingleton.getInstance().getLongitude();
    }
}
