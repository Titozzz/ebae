package ebaeapp.com.ebae;

/**
 * Created by Hung on 3/11/2017.
 */

public class LoadDistanceAction {
    public static int findDistance() {
        PreferenceSingleton prefsLoad = PreferenceSingleton.getInstance();
        return prefsLoad.normalizeDistance();
    }
}
