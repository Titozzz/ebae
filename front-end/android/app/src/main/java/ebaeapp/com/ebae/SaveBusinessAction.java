package ebaeapp.com.ebae;

import com.yelp.clientlib.entities.Business;

/**
 * Created by Hung on 3/7/2017.
 */

public  class SaveBusinessAction{
    public static void saveBusiness(Business business, RestaurantActivity restAct){
        SaveBusinessManager.saveToFile(business, restAct);
    }
}
