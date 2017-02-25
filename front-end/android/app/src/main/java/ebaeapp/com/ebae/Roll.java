package ebaeapp.com.ebae;

import com.yelp.clientlib.entities.Business;
import com.yelp.clientlib.entities.SearchResponse;

import org.json.JSONObject;

import retrofit2.Response;

/**
 * Created by Hung on 2/24/2017.
 */

public interface Roll {
    String CONSUMER_KEY = "H97cBWqesMAn69Fsp_Gdrw";
    String CONSUMER_SECRET = "IiXpuOUVutSV9SV1J_AnZcq-XN4";
    String TOKEN = "dX-rO2VZALrd7TkPZiOren28BBMkSlpy";
    String TOKEN_SECRET = "c49iiKz5GD68lsz6ZnjqHjpQxdE";
    Response<SearchResponse> rollRestaurant();
    JSONObject parseRestaurantList(Response<SearchResponse> responses);
    Business chooseRestaurant(JSONObject restList);
}
