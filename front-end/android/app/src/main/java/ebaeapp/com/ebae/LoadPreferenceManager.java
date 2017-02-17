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
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();

                while ( (receiveString = bufferedReader.readLine()) != null ) {
                    stringBuilder.append(receiveString);
                }

                is.close();
                str = stringBuilder.toString();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace(); //file not found
        } catch (IOException e) {
            e.printStackTrace();
        }

        JSONObject jsonObj = null;
        try {
            jsonObj = new JSONObject(str);
        } catch (JSONException e) {
            e.printStackTrace(); //json conversion failed (file not in correct format)
        }
        return jsonObj;
    }
}
