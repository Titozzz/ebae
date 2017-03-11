package ebaeapp.com.ebae;

/**
 * Created by Hung on 3/11/2017.
 */

public class LoadPriceAction {
    public static String findPrice() {
        PreferenceSingleton prefsLoad = PreferenceSingleton.getInstance();
        return prefsLoad.stringifyPrice();
    }
}
