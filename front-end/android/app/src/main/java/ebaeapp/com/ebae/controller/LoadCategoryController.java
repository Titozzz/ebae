package ebaeapp.com.ebae.controller;

import java.util.ArrayList;

import ebaeapp.com.ebae.data.PreferenceSingleton;

/**
 * Created by Hung on 3/11/2017.
 */

public class LoadCategoryController {
    public static String findCategories() {
        PreferenceSingleton prefsLoad = PreferenceSingleton.getInstance();
        return prefsLoad.stringifyPrefs();
    }

    public static ArrayList<String> arrayNegativePrefs() {
        PreferenceSingleton prefsLoad = PreferenceSingleton.getInstance();
        return prefsLoad.arrayNegativePrefs();
    }
}
