package ebaeapp.com.ebae.controller;

import com.yelp.fusion.client.models.Business;

import ebaeapp.com.ebae.RestaurantView;
import ebaeapp.com.ebae.model.SaveBusinessModel;

/**
 * Created by Hung on 3/7/2017.
 */

public  class SaveBusinessController {
    public static void saveBusiness(Business business, RestaurantView restAct){
        SaveBusinessModel.saveToFile(business, restAct);
    }
}

