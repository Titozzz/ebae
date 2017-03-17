package ebaeapp.com.ebae.controller;

import ebaeapp.com.ebae.data.PreferenceSingleton;

/**
 * Created by Hung on 3/11/2017.
 */

public class LoadRatingController {
    public static int findRating() {
        PreferenceSingleton prefsLoad = PreferenceSingleton.getInstance();
        return prefsLoad.normalizeRating();
    }
}
