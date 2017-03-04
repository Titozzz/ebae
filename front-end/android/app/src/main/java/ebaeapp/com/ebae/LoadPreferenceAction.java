package ebaeapp.com.ebae;

/**
 * Created by Hung on 2/17/2017.
 */

public class LoadPreferenceAction {
    public static void loadPrefs(SettingsActivity setAct){
        LoadPreferenceManager.readPrefsFromFile(setAct);
    }
}
