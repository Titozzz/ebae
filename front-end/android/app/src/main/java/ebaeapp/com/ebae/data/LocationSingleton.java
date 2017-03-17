package ebaeapp.com.ebae.data;

/**
 * Created by Hung on 3/11/2017.
 */

public class LocationSingleton {
    private static final LocationSingleton locationSingle = new LocationSingleton();
    private double latitude;
    private double longitude;

    private LocationSingleton() {
    }

    public static LocationSingleton getInstance() {
        return locationSingle;
    }

    public void setCoordinates(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public double getLatitude() {
        return this.latitude;
    }

    public double getLongitude() {
        return this.longitude;
    }
}
