package ebaeapp.com.ebae.controller;

import ebaeapp.com.ebae.data.PreferenceSingleton;
import ebaeapp.com.ebae.model.SavePreferenceModel;
import ebaeapp.com.ebae.SettingsView;

/**
 * Created by Hung on 2/15/2017. parse the information in settings Activity to make sure there are
 * no errors in the settings. This mostly consists of entirely empty settings which would result in
 * search failures
 */

public class SavePreferenceController {

    public static void savePrefs(PreferenceSingleton prefs, SettingsView setAct) {
        SavePreferenceModel.savePrefsToFile(prefs, setAct);
    }

}
