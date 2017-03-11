package ebaeapp.com.ebae;

import android.support.v7.app.AppCompatActivity;

/**
 * Created by Hung on 2/17/2017.
 */

public class LoadPreferenceAction {
    public static void loadPrefs(AppCompatActivity setAct){
        LoadPreferenceManager.readPrefsFromFile(setAct);
    }
}
