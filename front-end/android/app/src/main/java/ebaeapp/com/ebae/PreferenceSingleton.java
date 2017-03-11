package ebaeapp.com.ebae;

/**
 * Created by Hung on 3/4/2017.
 */

public class PreferenceSingleton {
    private static final PreferenceSingleton prefs = new PreferenceSingleton();
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

        return prefs;
    }

    public String stringifyPrefs() {
        String categoryList = ",";
        int counter = 0;
        //construct string for lifestyles categories
        if(lifestyles[Constants.VEGETARIAN_INDEX] == true && counter < 10) {
            categoryList = categoryList + "vegetarian,";
            counter++;
        }
        if(lifestyles[Constants.VEGAN_INDEX]== true && counter < 10) {
            categoryList = categoryList + "vegan,";
            counter++;
        }
        if(lifestyles[Constants.GLUTEN_FREE_INDEX] == true && counter < 10) {
            categoryList = categoryList + "gluten_free,";
            counter++;
        }

        //construct string for dislikes (Use everything not selected)
        if(dislikes[Constants.AMERICAN_INDEX] == false&& counter < 9) {
            categoryList = categoryList + "newamerican,tradamerican,";
            counter+=2;
        }
        if(dislikes[Constants.ASIAN_INDEX] == false&& counter < 5) {
            categoryList = categoryList + "chinese,japanese,";
            categoryList = categoryList + "korean,";
            categoryList = categoryList + "thai,vietnamese,";
            counter+=5;
        }
        if(dislikes[Constants.BAKERIES_INDEX]==false && counter < 10) {
            categoryList = categoryList + "bakeries,";
            counter++;
        }
        if(dislikes[Constants.BARS_INDEX]==false && counter < 10) {
            categoryList = categoryList + "bars,";
            counter++;
        }
        if(dislikes[Constants.BBQ_INDEX]==false && counter < 10) {
            categoryList = categoryList + "bbq,";
            counter++;
        }
        if(dislikes[Constants.BRUNCH_INDEX] == false&& counter < 10) {
            categoryList = categoryList + "breakfast_brunch,";
            counter++;
        }
        if(dislikes[Constants.FAST_FOOD_INDEX] == false&& counter < 10) {
            categoryList = categoryList + "hotdogs,";
            counter++;
        }
        if(dislikes[Constants.INDIAN_INDEX] == false&& counter < 10) {
            categoryList = categoryList + "indpak,";
            counter++;
        }
        if(dislikes[Constants.MEDITERRANEAN_INDEX]== false&& counter < 10) {
            categoryList = categoryList + "mediterranean,";
            counter++;
        }
        if(dislikes[Constants.MEXICAN_INDEX] == false&& counter < 10) {
            categoryList = categoryList + "mexican,";
            counter++;
        }
        if(dislikes[Constants.SEAFOOD_INDEX]==false&& counter < 10) {
            categoryList = categoryList + "seafood,";
            counter++;
        }
        //make sure to remove the first and last commas
        return categoryList.substring(1, categoryList.length()-1);
    }

    public String stringifyPrice() {
        String priceString = "";
        if(sliders[Constants.PRICE_INDEX] < 2) {
            priceString = "1"; //$
        }
        else if(sliders[Constants.PRICE_INDEX] < 3) {
            priceString = "2, 1"; //$$
        }
        else if(sliders[Constants.PRICE_INDEX] < 4) {
            priceString = "3, 2, 1"; //$$$
        }
        else {
            priceString = "4, 3, 2, 1"; //$$$$
        }
        return priceString;
    }

    //Normalize the distance. Yelp takes it in meters, so
    //each  slider will be a step towards the max of 40000
    public int normalizeDistance() {
        return sliders[Constants.DISTANCE_INDEX] * 10000;
    }

    //normalize the ratings passed in from the sliders.
    //Should start at 1.
    public int normalizeRating() {
        return sliders[Constants.RATING_INDEX] +1;
    }
}
