package ebaeapp.com.ebae;

import android.util.Log;

import com.yelp.fusion.client.models.Business;
import com.yelp.fusion.client.models.SearchResponse;
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
  private ArrayList<Business> businesses = new ArrayList<>(0);
  private int currentBusinessIndex;

  private void roll20Restaurants(BusinessRunnable onSuccess, Runnable onFailure) {
    Map<String, String> params = new HashMap<>(); // parameters for the search
    // add all of the permanent preferences no matter what
    if(useTemporaryPreferences) {
      //add all of the temporary preferences
    }

    params.put("location", "San Diego");
    //leaving it in San Diego for now, use Location later
    Call<SearchResponse> call = YelpSingleton.getInstance().getBusinessSearch(params);
    Callback<SearchResponse> callback = new Callback<SearchResponse>() {
      @Override
      public void onResponse(Call<SearchResponse> call, Response<SearchResponse> response) {
        businesses = response.body().getBusinesses();
        int numberOfBusinesses = response.body().getTotal();
        Log.i("Businesses Found", "" + numberOfBusinesses);

        if (numberOfBusinesses == 0) {
          onFailure.run();
        } else {
          chooseRestaurant(onSuccess);
        }
      }
      @Override
      public void onFailure(Call<SearchResponse> call, Throwable t) {
        // HTTP error happened, do something to handle it.
        onFailure.run();
      }
    };

    call.enqueue(callback);
  }

  private void chooseRestaurant(BusinessRunnable onSuccess) {
    //Set current index between 0 and businessesSize
    currentBusinessIndex = (int)(Math.random() * businesses.size());
    onSuccess.run(businesses.get(currentBusinessIndex));
  }

  private void rerollRestaurant(BusinessRunnable onSuccess) {
    businesses.remove(currentBusinessIndex);
    chooseRestaurant(onSuccess);
  }

  public void getNextRestaurant(BusinessRunnable onSuccess, Runnable onFailure) {
    if (businesses.size() <= 1) {
      roll20Restaurants(onSuccess, onFailure);
    } else {
      rerollRestaurant(onSuccess);
    }
  }

  // Helper method to know if you should display the reroll button
  // Return businesses array size.
  public int getBusinessesSize() {
    return businesses.size();
  }
}
