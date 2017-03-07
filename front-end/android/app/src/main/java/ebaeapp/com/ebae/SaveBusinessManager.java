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
    public static void saveToFile(Business business, RestaurantActivity restAct) {

        Business[] tempBusArr = null;
        File file = new File(restAct.getFilesDir(),
                "history.json");//write to file in internal storage
        if(file.exists()) {
            //if we have a file with businesses in it already, we need to catch all of them
            //and rewrite them.
            Business[] busArr = LoadBusinessAction.loadBusinesses(restAct);
            file.delete();
            tempBusArr = new Business[busArr.length+1];
            for(int i = 0; i < busArr.length; i++) {
                tempBusArr[i] = busArr[i];
            }
        }
        if(tempBusArr == null) {
            tempBusArr = new Business[1];
        }
        tempBusArr[tempBusArr.length-1] = business;
        try {
            JSONArray jsonArr= new JSONArray(tempBusArr);
            FileOutputStream os = new FileOutputStream(file, true);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(os);
            Log.i("SaveBusinessActivity", "file is in: " + file.getAbsolutePath());

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
