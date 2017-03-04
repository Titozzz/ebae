package ebaeapp.com.ebae;

/**
 * Created by Hung on 3/4/2017.
 */

public class PreferenceSingleton {
    static PreferenceSingleton prefs;
    boolean [] lifestyles;
    boolean [] dislikes;
    int [] sliders;

    /*Simple constructor for the wrapper, sets all the fields*/
    private PreferenceSingleton() {
        this.lifestyles = new boolean[Constants.LIFESTYLES_SIZE];
        this.dislikes = new boolean[Constants.DISLIKES_SIZE];
        this.sliders = new int[Constants.SLIDERS_SIZE];
    }

    public static PreferenceSingleton getInstance() {
        if(prefs == null) {
            prefs = new PreferenceSingleton();
        }
        return prefs;
    }
}
