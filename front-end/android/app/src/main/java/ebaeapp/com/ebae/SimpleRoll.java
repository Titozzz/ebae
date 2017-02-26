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
import retrofit2.Callback;
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
    public void rollRestaurant() {
        YelpAPIFactory apiFactory = new YelpAPIFactory(CONSUMER_KEY,CONSUMER_SECRET,TOKEN,TOKEN_SECRET);
        YelpAPI yelpAPI = apiFactory.createAPI(); //prepare yelp api
        Map<String, String> params = new HashMap<>(); // parameters for the search
        // add all of the permanent preferences no matter what
        if(usingTemps) {
            //add all of the temporary preferences
        }
        //leaving it in San Diego for now, use Location later
        Call<SearchResponse> call = yelpAPI.search("San Diego", params);
        Callback<SearchResponse> callback = new Callback<SearchResponse>() {
            @Override
            public void onResponse(Call<SearchResponse> call, Response<SearchResponse> response) {
                SearchResponse searchResponse = response.body();
                Business business = chooseRestaurant(searchResponse);
                System.err.println("Made it past choosing restaurant!");
                System.err.println(business.toString());
                // Update UI text with the searchResponse.
            }
            @Override
            public void onFailure(Call<SearchResponse> call, Throwable t) {
                // HTTP error happened, do something to handle it.
            }
        };

        call.enqueue(callback);
    }


    @Override
    public Business chooseRestaurant(SearchResponse searchResponse) {
        int restChoice = 0;
        int numBusinesses = 0;

        //retrieve number of elements in the list
        numBusinesses = searchResponse.businesses().size();
        restChoice = (int)(Math.random() * numBusinesses);
        return searchResponse.businesses().get(restChoice);
    }
}
