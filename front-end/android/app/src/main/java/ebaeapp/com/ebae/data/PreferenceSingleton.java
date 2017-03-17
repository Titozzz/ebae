package ebaeapp.com.ebae.data;

import java.util.ArrayList;

/**
 * Created by Hung on 3/4/2017.
 */

public class PreferenceSingleton {
    private static final PreferenceSingleton prefs = new PreferenceSingleton();
    public boolean [] lifestyles;
    public boolean [] dislikes;
    public int [] sliders;

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
        //construct string for lifestyles categories
        if(lifestyles[Constants.VEGETARIAN_INDEX]) {
            categoryList = categoryList + "vegetarian,";
        }
        if(lifestyles[Constants.VEGAN_INDEX]) {
            categoryList = categoryList + "vegan,";
        }
        if(lifestyles[Constants.GLUTEN_FREE_INDEX]) {
            categoryList = categoryList + "gluten_free,";
        }
        //make sure to remove the first and last commas
        return categoryList.substring(1, Math.max(1,categoryList.length()-1));
    }

    public ArrayList<String> arrayNegativePrefs() {
        ArrayList<String> returnValues = new ArrayList<>(0);

        //construct string for dislikes (Use everything not selected)
        if(dislikes[Constants.AMERICAN_INDEX]) {
            returnValues.add("newamerican");
            returnValues.add("tradamerican");
        }
        if(dislikes[Constants.ASIAN_INDEX]) {
            returnValues.add("chinese");
            returnValues.add("japanese");
            returnValues.add("korean");
            returnValues.add("thai");
            returnValues.add("vietnamese");
        }
        if(dislikes[Constants.BAKERIES_INDEX]) {
            returnValues.add("bakeries");
        }
        if(dislikes[Constants.BARS_INDEX]) {
            returnValues.add("bars");
        }
        if(dislikes[Constants.BBQ_INDEX]) {
            returnValues.add("bbq");
        }
        if(dislikes[Constants.BRUNCH_INDEX]) {
            returnValues.add("breakfast_brunch");
        }
        if(dislikes[Constants.FAST_FOOD_INDEX]) {
            returnValues.add("hotdogs");
        }
        if(dislikes[Constants.INDIAN_INDEX]) {
            returnValues.add("indpak");
        }
        if(dislikes[Constants.MEDITERRANEAN_INDEX]) {
            returnValues.add("mediterranean");
        }
        if(dislikes[Constants.MEXICAN_INDEX]) {
            returnValues.add("mexican");
        }
        if(dislikes[Constants.SEAFOOD_INDEX]) {
            returnValues.add("seafood");
        }
        return returnValues;
    }

    public String stringifyPrice() {
        String priceString = "";
        if(sliders[Constants.PRICE_INDEX] < 2) {
            priceString = "1"; //$
        }
        else if(sliders[Constants.PRICE_INDEX] < 3) {
            priceString = "1,2"; //$$
        }
        else if(sliders[Constants.PRICE_INDEX] < 4) {
            priceString = "1,2,3"; //$$$
        }
        else {
            priceString = "1,2,3,4"; //$$$$
        }
        return priceString;
    }

    //Normalize the distance. Yelp takes it in meters, so
    //each  slider will be a step towards the max of 40000
    public int normalizeDistance() {
        return (sliders[Constants.DISTANCE_INDEX]) * 5000;
    }

    //normalize the ratings passed in from the sliders.
    //Should start at 1.
    public int normalizeRating() {
        return sliders[Constants.RATING_INDEX];
    }
}
