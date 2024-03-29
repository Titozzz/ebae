package ebaeapp.com.ebae.model;

import android.util.Log;


import com.yelp.fusion.client.models.Business;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import ebaeapp.com.ebae.RestaurantView;
import ebaeapp.com.ebae.data.Constants;
import ebaeapp.com.ebae.controller.LoadBusinessController;

/**
 * Created by Hung on 3/7/2017.
 */

public class SaveBusinessModel {

    /**
     * Given a business to be saved, save to a file, creating a new file if it exists
     * The file is saved in the following format:
     *      Up to 10 businesses in a JSONArray string.
     *      Sorted Newest to Oldest
     * @param business
     * @param restAct
     */
    public static void saveToFile(Business business, RestaurantView restAct) {

        JSONObject[] newBusArray = null; //for holding the different businesses, either
                                      //empty or containing up to 9 old businesses
        File file = new File(restAct.getFilesDir(),
                "history.json");//write to file in internal storage
        if(file.exists()) {
            //if we have a file with businesses in it already, we need to catch all of them
            //and rewrite them.
            JSONObject[] busArr = LoadBusinessController.loadBusinesses(restAct);
            file.delete();
            if (busArr.length == Constants.MAX_HISTORY_SIZE) {
                newBusArray = new JSONObject[busArr.length - 1];
                for (int i = 0; i < newBusArray.length; i++) {
                    newBusArray[i] = busArr[i]; //put everything except last element
                    //into new array
                }
            } else {
                newBusArray = new JSONObject[busArr.length];
                for (int i = 0; i < busArr.length; i++) {
                    newBusArray[i] = busArr[i]; //put everything from old array
                    //into new array
                }
            }

        }
        try {
            JSONArray jsonArr= new JSONArray();
            JSONObject jsonObj = new JSONObject();
            //populate jsonObj fields
            jsonObj.put("id", business.getId());
            jsonObj.put("name", business.getName());
            jsonObj.put("image_url", business.getImageUrl());
            jsonObj.put("url", business.getUrl());
            jsonObj.put("rating", (float)business.getRating());
            jsonObj.put("location", business.getLocation().getCity());
            jsonObj.put("price", business.getPrice());

            jsonArr.put(jsonObj);
            if(newBusArray != null) { //add all the old object
                for(int i = 0; i < newBusArray.length; i++) {
                    jsonArr.put(newBusArray[i]);
                }
            }
            FileOutputStream os = new FileOutputStream(file, true);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(os);
            Log.i("SaveBusinessActivity", "file is in: " + file.getAbsolutePath());

            Log.i("Business File:", "Current History is: " + jsonArr.toString());
            outputStreamWriter.write(jsonArr.toString());//write the constructed json Object to a string
            outputStreamWriter.flush();
            outputStreamWriter.close();
            Log.i("SaveBusinessModel", "Saved" + business.toString());


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
