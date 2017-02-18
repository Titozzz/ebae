package ebaeapp.com.ebae;

import android.util.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import android.content.Context;

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
  public static void savePrefsToFile(boolean[] lifestyles, boolean[] dislikes,
      int[] sliders, SettingsActivity setAct) {

    File file = new File(setAct.getFilesDir(),
        "prefs.json");//write to file in internal storage
      JSONObject jsonObject = new JSONObject();
    try {
      FileOutputStream os = new FileOutputStream(file, true);
      OutputStreamWriter outputStreamWriter = new OutputStreamWriter(os);
      Log.i("SavePrefManagerActivity", "file is in: " + file.getAbsolutePath());
      for (int i = 0; i < lifestyles.length; i++) { //store the lifestyles choices (booleans)
          jsonObject.put("lifestyles", lifestyles[i]);
      }
      for (int i = 0; i < dislikes.length; i++) { //store dislikes (booleans)
          jsonObject.put("dislikes", dislikes[i]);
      }
      for (int i = 0; i < sliders.length; i++) { //store the lifestyles choices(ints 0-5)
          jsonObject.put("sliders", sliders[i]);
      }
        outputStreamWriter.write(jsonObject.toString());//write the constructed json Object to a string


    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } catch (JSONException e) {
        e.printStackTrace();
    }
  }
}
