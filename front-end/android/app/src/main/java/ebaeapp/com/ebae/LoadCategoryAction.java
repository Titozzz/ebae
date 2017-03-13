package ebaeapp.com.ebae;

import java.util.ArrayList;

/**
 * Created by Hung on 3/11/2017.
 */

public class LoadCategoryAction {
    public static String findCategories() {
        PreferenceSingleton prefsLoad = PreferenceSingleton.getInstance();
        return prefsLoad.stringifyPrefs();
    }

    public static ArrayList<String> arrayNegativePrefs() {
        PreferenceSingleton prefsLoad = PreferenceSingleton.getInstance();
        return prefsLoad.arrayNegativePrefs();
    }
}
