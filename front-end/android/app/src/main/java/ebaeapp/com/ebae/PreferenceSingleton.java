package ebaeapp.com.ebae;

/**
 * Created by Hung on 3/4/2017.
 */

public class PreferenceSingleton {
    boolean [] lifestyles;
    boolean [] dislikes;
    int [] sliders;

    /*Simple constructor for the wrapper, sets all the fields*/
    public PreferenceSingleton(boolean[] lifestyles, boolean[]dislikes, int[]sliders) {
        this.lifestyles = lifestyles;
        this.dislikes = dislikes;
        this.sliders = sliders;
    }
}
