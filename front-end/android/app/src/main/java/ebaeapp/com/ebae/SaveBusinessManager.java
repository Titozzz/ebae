package ebaeapp.com.ebae;

import android.util.Log;

import com.yelp.clientlib.entities.Business;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

/**
 * Created by Hung on 3/7/2017.
 */

public class SaveBusinessManager {

    /**
     * Given a business to be saved, save to a file, creating a new file if it exists
     * The file is saved in the following format:
     *      Up to 10 businesses in a JSONArray string.
     *      Sorted Newest to Oldest
     * @param business
     * @param restAct
     */
    public static void saveToFile(Business business, RestaurantActivity restAct) {

        JSONObject[] newBusArray = null; //for holding the different businesses, either
                                      //empty or containing up to 9 old businesses
        File file = new File(restAct.getFilesDir(),
                "history.json");//write to file in internal storage
        if(file.exists()) {
            //if we have a file with businesses in it already, we need to catch all of them
            //and rewrite them.
            JSONObject[] busArr = LoadBusinessAction.loadBusinesses(restAct);
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
            jsonObj.put("id", business.id());
            jsonObj.put("name", business.name());
            jsonObj.put("image_url", business.imageUrl());
            jsonObj.put("url", business.url());
            jsonObj.put("rating", business.rating());
            jsonObj.put("location", business.location());
            jsonObj.put("price", PreferenceSingleton.getInstance().
                    sliders[Constants.PRICE_SLIDER_INDEX]);//price is used in the search,
                                                            // take it from the singleton we searched with
            /*jsonStr = jsonStr.substring(jsonStr.indexOf('{'), //convert to JSONObject format
                                        jsonStr.lastIndexOf('}')+1);
            jsonStr = jsonStr.replaceAll("=", "\":\""); //replace symbols in Business to JSONObject format
            jsonStr = jsonStr.replaceAll(", ", "\", \"" );
            //fix outer brackets
            jsonStr = jsonStr.replaceAll("\\{", "\\{\"");
            jsonStr = jsonStr.replaceAll("\\}", "\"\\}");
            //fix all array brackets
            jsonStr = jsonStr.replaceAll("\"\\{", "\\{");
            jsonStr = jsonStr.replaceAll("\\}\"", "\\}");
            jsonStr = jsonStr.replaceAll("\"\\[", "\\[");
            jsonStr = jsonStr.replaceAll("\\]\"", "\\]");
            jsonStr = jsonStr.replaceAll("\"Category", "");
            jsonStr = jsonStr.replaceAll("Category", "");*/
            //fix urls

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
            Log.i("SaveBusinessManager", "Saved" + business.toString());


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
