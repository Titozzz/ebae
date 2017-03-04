package ebaeapp.com.ebae;

import android.util.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Hung on 2/15/2017. Using the information from SavePreferenceAction, save the
 * preferences sent from SavePreferenceForm
 *
 * The Model view of the SavePreference Activity, in the Manager Layer of our architecture.
 */

public class SavePreferenceManager {


  /*
   * Prints all of the preferences passed in to the "prefs" file in local storage.
   * Printing is in the following format:
   *
   * In JSON format:
   * lifestyles - either a true or false value
   * dislikes - either a true or false value
   * sliders - integers from 0-5, which will correspond to different values, depending on the slider
   *
   *
   *
   */
  public static void savePrefsToFile(PreferenceSingleton prefs, SettingsActivity setAct) {

    File file = new File(setAct.getFilesDir(),
        "prefs.json");//write to file in internal storage
    if(file.exists()) {
      file.delete();
    }
      JSONObject jsonObject = new JSONObject();
    try {
      FileOutputStream os = new FileOutputStream(file, true);
      OutputStreamWriter outputStreamWriter = new OutputStreamWriter(os);
      Log.i("SavePrefManagerActivity", "file is in: " + file.getAbsolutePath());
      JSONArray lifeArr = new JSONArray();
      for (int i = 0; i < prefs.lifestyles.length; i++) { //store the lifestyles choices (booleans)

        lifeArr.put(prefs.lifestyles[i]);
      }
      jsonObject.put("lifestyles", lifeArr);
      JSONArray dislikesArr = new JSONArray();
      for (int i = 0; i < prefs.dislikes.length; i++) { //store the lifestyles choices (booleans)

        dislikesArr.put(prefs.dislikes[i]);
      }
      jsonObject.put("dislikes", dislikesArr);
      JSONArray sliderArr = new JSONArray();
      for (int i = 0; i < prefs.sliders.length; i++) { //store the lifestyles choices (booleans)

        sliderArr.put(prefs.sliders[i]);
      }
      jsonObject.put("sliders", sliderArr);
        outputStreamWriter.write(jsonObject.toString());//write the constructed json Object to a string
        outputStreamWriter.flush();
        outputStreamWriter.close();
        Log.i("SavePreferenceManager", "Saved" + jsonObject.toString());


    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } catch (JSONException e) {
        e.printStackTrace();
    }
  }
}
