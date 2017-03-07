package ebaeapp.com.ebae;

import android.util.Log;

import com.yelp.clientlib.entities.Business;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Hung on 3/7/2017.
 */

public class LoadBusinessManager {

    public static Business[] loadFromFile(RestaurantActivity restAct) {
        String str = "";
        Business[] busArr;
        File file = new File(restAct.getFilesDir(),
                "history.json");//write to file in internal storage
        Log.i("LoadPrefManagerActivity", "file is in: " + file.getAbsolutePath());
        InputStream is;
        try {
            is = new FileInputStream(file);
            if ( is != null ) {
                FileReader fr = new FileReader(restAct.getFilesDir() + "/history.json");
                BufferedReader bufferedReader = new BufferedReader(fr);
                String receiveString = ""; //load in strings per line
                StringBuilder stringBuilder = new StringBuilder();

                while ( (receiveString = bufferedReader.readLine()) != null ) {
                    stringBuilder.append(receiveString); //keep adding them to the full string
                }

                is.close();
                str = stringBuilder.toString(); // convert it into a useable string output
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace(); //file not found
        } catch (IOException e) {
            e.printStackTrace();
        }

        Log.i("LoadPreferenceManager", "The String is: " + str);
        JSONArray jsonArr = null;
        try {
            jsonArr = new JSONArray(str); //make the JSONObject
        } catch (JSONException e) {
            e.printStackTrace(); //json conversion failed (file not in correct format)
        }
        if(jsonArr != null) {
            busArr = new Business[jsonArr.length()];
            for(int i = 0; i < jsonArr.length(); i++) {
                try {
                    busArr[i] = (Business)jsonArr.get(i);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
        else {
            Log.i("LoadBusinessManager", "Load failed");
            return null;
        }

        return busArr;


    }
}
