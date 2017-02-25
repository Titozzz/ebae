package ebaeapp.com.ebae;

import com.yelp.clientlib.connection.YelpAPI;
import com.yelp.clientlib.connection.YelpAPIFactory;
import com.yelp.clientlib.entities.Business;
import com.yelp.clientlib.entities.SearchResponse;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Response;


/**
 * Created by Hung on 2/24/2017.
 */

public class SimpleRoll implements Roll {
    boolean usingTemps;
    public SimpleRoll() {
        usingTemps = false;
    }

    @Override
    public Response<SearchResponse> rollRestaurant() {
        YelpAPIFactory apiFactory = new YelpAPIFactory(CONSUMER_KEY,CONSUMER_SECRET,TOKEN,TOKEN_SECRET);
        YelpAPI yelpAPI = apiFactory.createAPI(); //prepare yelp api
        Map<String, String> params = new HashMap<>(); // parameters for the search
        // add all of the permanent preferences no matter what
        if(usingTemps) {
            //add all of the temporary preferences
        }
        //leaving it in San Diego for now, use Location later
        Call<SearchResponse> call = yelpAPI.search("San Diego", params);
        Response<SearchResponse> response = null;
        try {
            response = call.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

    @Override
    public JSONObject parseRestaurantList(Response<SearchResponse> responses) {
        String responseText = responses.toString();
        JSONObject restList = null;
        try {
            restList = new JSONObject(responseText);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return restList;
    }

    @Override
    public Business chooseRestaurant(JSONObject restList) {
        int restChoice = 0;
        int numBusinesses = 0;
        try {
            //retrieve number of elements in the list
            numBusinesses = restList.getInt("total");
            restChoice = (int)(Math.random() * numBusinesses);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        List<Business> businesses = null; //list of businesses
        try {
            businesses = (List<Business>)restList.get("businesses");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        if(numBusinesses == 0) {
            return null;
        }
        else {
            return businesses.get(restChoice);
        }

    }
}
