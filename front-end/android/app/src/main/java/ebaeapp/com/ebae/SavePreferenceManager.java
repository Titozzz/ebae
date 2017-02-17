package ebaeapp.com.ebae;

import android.util.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import android.content.Context;

/**
 * Created by Hung on 2/15/2017. Using the information from SavePreferenceAction, save the
 * preferences sent from SavePreferenceForm
 */

public class SavePreferenceManager {


  /*
   * Prints all of the preferences passed in to the "prefs" file in local storage.
   * Printing is in the following format:
   *
   * lifestyles - either a 1 or a 0, 1 depicting true, 0 depicting false
   * new line character separating sections
   * dislikes - either a 1 or a 0, 1 depicting true, 0 depicting false
   * new line character separating sections
   * sliders - integers from 0-5, which will correspond to different values, depending on the slider
   *                  Each slider is separated by a tab for easier reading
   *
   *
   */
  public static void savePrefsToFile(boolean[] lifestyles, boolean[] dislikes,
      int[] sliders, SettingsActivity setAct) {

    File file = new File(setAct.getFilesDir(),
        "prefs");//read in previous information(mostly for username/password)

    try {
      FileOutputStream os = new FileOutputStream(file, true);
      OutputStreamWriter outputStreamWriter = new OutputStreamWriter(os);
      Log.i("SavePrefManagerActivity", "file is in: " + file.getAbsolutePath());
      for (int i = 0; i < lifestyles.length; i++) { //print the lifestyles choices(1s and 0s)
        if (lifestyles[i] == false) {
          outputStreamWriter.write(0);
        } else {
          outputStreamWriter.write(1);
        }
      }
      outputStreamWriter.write("\n");
      for (int i = 0; i < dislikes.length; i++) { //print the lifestyles choices(1s and 0s)
        if (dislikes[i] == false) {
          outputStreamWriter.write(0);
        } else {
          outputStreamWriter.write(1);
        }
      }
      outputStreamWriter.write("\n");
      for (int i = 0; i < sliders.length; i++) { //print the lifestyles choices(1s and 0s)
        outputStreamWriter.write(sliders[i]);
        outputStreamWriter.write("\t");
      }
      outputStreamWriter.write("\n");


    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
