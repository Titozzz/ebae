package ebaeapp.com.ebae.controller;

import android.support.v7.app.AppCompatActivity;

import ebaeapp.com.ebae.model.LoadPreferenceModel;

/**
 * Created by Hung on 2/17/2017.
 */

public class LoadPreferenceController {
    public static void loadPrefs(AppCompatActivity setAct){
        LoadPreferenceModel.readPrefsFromFile(setAct);
    }
}
