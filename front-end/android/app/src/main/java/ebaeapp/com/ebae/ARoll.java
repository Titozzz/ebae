package ebaeapp.com.ebae;

import android.util.Log;
import com.yelp.clientlib.entities.Business;
import com.yelp.clientlib.entities.SearchResponse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Hung on 2/24/2017.
 */

public abstract class ARoll {

  protected boolean useTemporaryPreferences = true;
  private ArrayList<Business> businesses;
  private int currentBusinessIndex;

  public void rollRestaurant() {
    Map<String, String> params = new HashMap<>(); // parameters for the search
    // add all of the permanent preferences no matter what
    if(useTemporaryPreferences) {
      //add all of the temporary preferences
    }

    //leaving it in San Diego for now, use Location later
    Call<SearchResponse> call = YelpSingleton.getInstance().search("San Diego", params);
    Callback<SearchResponse> callback = new Callback<SearchResponse>() {
      @Override
      public void onResponse(Call<SearchResponse> call, Response<SearchResponse> response) {
        businesses = response.body().businesses();
        Business business = chooseRestaurant();
        Log.i("Business Found", business.name());
        // Update UI text with the searchResponse.
      }
      @Override
      public void onFailure(Call<SearchResponse> call, Throwable t) {
        // HTTP error happened, do something to handle it.
      }
    };

    call.enqueue(callback);
  }

  private Business chooseRestaurant() {
    //Set current index between 0 and businessesSize
    currentBusinessIndex = (int)(Math.random() * businesses.size());

    return businesses.get(currentBusinessIndex);
  }

  public Business rerollRestaurant() {
    if (businesses.size() <= 1) {
      throw new RuntimeException("rerollRestaurant() called but no other options");
    }

    businesses.remove(currentBusinessIndex);
    return chooseRestaurant();
  }

  // Helper method to know if you should display the reroll button
  // Return businesses array size.
  public int getBusinessesSize() {
    return businesses.size();
  }
}
