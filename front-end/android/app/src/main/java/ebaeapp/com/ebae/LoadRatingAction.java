package ebaeapp.com.ebae;

/**
 * Created by Hung on 3/11/2017.
 */

public class LoadRatingAction {
    public static int findRating() {
        PreferenceSingleton prefsLoad = PreferenceSingleton.getInstance();
        return prefsLoad.normalizeRating();
    }
}
