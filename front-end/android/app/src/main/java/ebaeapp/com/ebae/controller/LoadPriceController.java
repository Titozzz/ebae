package ebaeapp.com.ebae.controller;

import ebaeapp.com.ebae.data.PreferenceSingleton;

/**
 * Created by Hung on 3/11/2017.
 */

public class LoadPriceController {
    public static String findPrice() {
        PreferenceSingleton prefsLoad = PreferenceSingleton.getInstance();
        return prefsLoad.stringifyPrice();
    }
}
