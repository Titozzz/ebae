package ebaeapp.com.ebae;

/**
 * Created by Hung on 3/11/2017.
 */

public class LoadCategoryAction {
    public static String findCategories() {
        PreferenceSingleton prefsLoad = PreferenceSingleton.getInstance();
        return prefsLoad.stringifyPrefs();
    }
}
