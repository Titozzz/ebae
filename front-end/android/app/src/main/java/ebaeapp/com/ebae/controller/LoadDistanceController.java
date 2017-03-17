package ebaeapp.com.ebae.controller;

import ebaeapp.com.ebae.data.PreferenceSingleton;

/**
 * Created by Hung on 3/11/2017.
 */

public class LoadDistanceController {
    public static int findDistance() {
        PreferenceSingleton prefsLoad = PreferenceSingleton.getInstance();
        return prefsLoad.normalizeDistance();
    }
}
