package ebaeapp.com.ebae.roll;

import android.util.Log;
import android.widget.Toast;

import com.yelp.fusion.client.models.Business;
import com.yelp.fusion.client.models.Category;
import com.yelp.fusion.client.models.SearchResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import ebaeapp.com.ebae.controller.LoadCategoryController;
import ebaeapp.com.ebae.controller.LoadDistanceController;
import ebaeapp.com.ebae.controller.LoadLocationController;
import ebaeapp.com.ebae.controller.LoadPriceController;
import ebaeapp.com.ebae.controller.LoadRatingController;
import ebaeapp.com.ebae.data.YelpSingleton;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Hung on 2/24/2017.
 */

public abstract class ARoll{
  private static final int MAX_TRIES = 5;

  protected boolean useTemporaryPreferences = true;
  private ArrayList<Business> businesses = new ArrayList<>(0);
  private int currentBusinessIndex;
  private int tries = MAX_TRIES;

  private void filterBusinesses(ArrayList<Business> yelpBusinesses) {
    ArrayList<String> categoriesToRemove = LoadCategoryController.arrayNegativePrefs();
    ArrayList<Business> filteredBusinesses = new ArrayList<>(0);
    float minRating = LoadRatingController.findRating();
    for (Business business : yelpBusinesses) {
      boolean hasBadCategory = false;
      for (Category category : business.getCategories()) {
        if (categoriesToRemove.contains(category.getAlias())) {
          hasBadCategory = true;
          break;
        }
      }
      if (business.getRating() >= minRating && !hasBadCategory) {
        filteredBusinesses.add(business);
      }
    }
    businesses = filteredBusinesses;
  }

  private void roll20Restaurants(BusinessRunnable onSuccess, Runnable onFailure) {
    Map<String, String> params = new HashMap<>(); // parameters for the search
    // add all of the permanent preferences no matter what
    if(useTemporaryPreferences) {
      //add all of the temporary preferences
    }

    Log.e("Rolling Latitude:", Double.toString(LoadLocationController.findLatitude()));
    Log.e("Rolling Longitude:", Double.toString(LoadLocationController.findLongitude()));

    params.put("latitude", Double.toString(LoadLocationController.findLatitude()));
    params.put("longitude",Double.toString(LoadLocationController.findLongitude()));
    //params.put("location", "San Diego");
    params.put("limit", "1");
    params.put("term", "restaurants");
    params.put("radius", Integer.toString(LoadDistanceController.findDistance()) );
    Log.e("Categories to search:", LoadCategoryController.findCategories());
    Log.e("Prices to search:", LoadPriceController.findPrice());
    params.put("categories", LoadCategoryController.findCategories());
    params.put("price", LoadPriceController.findPrice());

    //leaving it in San Diego for now, use Location later
    if (YelpSingleton.getInstance() == null) {
      YelpSingleton.setInstance();
      onFailure.run();
      return;
    }
    Call<SearchResponse> call = YelpSingleton.getInstance().getBusinessSearch(params);
    Callback<SearchResponse> callback = new Callback<SearchResponse>() {
      @Override
      public void onResponse(Call<SearchResponse> call, Response<SearchResponse> response) {
        int numberOfBusinesses = response.body().getTotal();
        Log.i("Businesses Found", "" + numberOfBusinesses);
        if (numberOfBusinesses == 0) {
          onFailure.run();
          return;
        }
        params.put("limit", "20");
        numberOfBusinesses = numberOfBusinesses > 1000 ? 1000 : numberOfBusinesses; // YELP MAX RETURN
        int offset = (int)(Math.random() * (numberOfBusinesses - 20 > 0 ? numberOfBusinesses - 20 : 0));
        params.put("offset", "" + offset);
        Log.i("OFFSET", params.get("offset"));
        Call<SearchResponse> real_call = YelpSingleton.getInstance().getBusinessSearch(params);
        Callback<SearchResponse> real_callback = new Callback<SearchResponse>() {
          @Override
          public void onResponse(Call<SearchResponse> call, Response<SearchResponse> response) {
            int numberOfBusinesses = response.body().getTotal();
            Log.i("Businesses Found", "" + numberOfBusinesses);
            filterBusinesses(response.body().getBusinesses());

            if (businesses.size() == 0) {
              if (tries > 0) {
                tries = tries - 1;
                roll20Restaurants(onSuccess, onFailure);
              } else {
                Log.i("RANDOM ISSUE", "FAILED TO RANDOM 5 TIMES");
                onFailure.run();
              }
            } else {
              tries = MAX_TRIES;
              chooseRestaurant(onSuccess);
            }
          }

          @Override
          public void onFailure(Call<SearchResponse> call, Throwable t) {
            // HTTP error happened, do something to handle it.
            onFailure.run();
          }
        };
        real_call.enqueue(real_callback);

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
