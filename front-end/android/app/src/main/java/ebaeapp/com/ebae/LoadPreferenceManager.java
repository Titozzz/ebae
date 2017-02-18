package ebaeapp.com.ebae;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by Hung on 2/17/2017.
 * The Model view of the LoadPreference activity, in the Manager Layer of our architecture
 * Return value:
 *     JSONObject containing the file's preferences, to be returned to SettingsActivity.java
 *     null if there was an error parsing the file
 */

public class LoadPreferenceManager {

    public JSONObject readPrefsFromFile(SettingsActivity setAct) {
        String str = "";
        File file = new File(setAct.getFilesDir(),
                "prefs.json");//write to file in internal storage
        InputStream is;
        try {
            is = new FileInputStream(file);
            if ( is != null ) {
                InputStreamReader inputStreamReader = new InputStreamReader(is);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
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

        JSONObject jsonObj = null;
        try {
            jsonObj = new JSONObject(str); //make the JSONObject
        } catch (JSONException e) {
            e.printStackTrace(); //json conversion failed (file not in correct format)
        }
        return jsonObj;
    }
}
