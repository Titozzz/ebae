package ebaeapp.com.ebae;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.util.Log;
import android.view.View;

import com.yelp.clientlib.entities.Business;
import com.yelp.clientlib.entities.SearchResponse;

import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

  @BindView(R.id.history_button)
  FloatingActionButton history_button;
  @BindView(R.id.settings_button)
  FloatingActionButton settings_button;

  public PreferenceSingleton prefs = null;
  boolean loadedPrefs = false;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    if(!loadedPrefs) {
      LoadPreferenceAction.loadPrefs(this); //changes the singleton object to the file's data
      prefs = PreferenceSingleton.getInstance(); //updates singleton.
    }

    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ButterKnife.bind(this);

  }

  /** Called when the user clicks the history button */
  public void onHistoryButtonClick(View view) {
    Intent intent = new Intent(this, HistoryActivity.class);
    startActivity(intent);
  }

  /** Called when the user clicks the settings button */
  public void onSettingsButtonClick(View view) {
    Intent intent = new Intent(this, SettingsActivity.class);
    startActivity(intent);
  }

  /** Called when the user clicks the roll button */
  public void onRollButtonClick(View view) {
    try {
      ARoll simpleRoll = new SimpleRoll();
      ((CustomApplication)getApplicationContext()).setRoll(simpleRoll);

      Intent intent = new Intent(this, RestaurantActivity.class);
      startActivity(intent);
    }
    catch(Exception e) {
      Log.e("onSimpleRollButton", e.getMessage());
    }

  }

  /** Called when the user clicks the help button */
  public void onHelpButtonClick(View view) {
    Intent intent = new Intent(this, HelpActivity.class);
    startActivity(intent);
  }

  /** Called when the user clicks the roll button */
  public void onTempPrefButtonClick(View view) {
    Intent intent = new Intent(this, TempPrefActivity.class);
    startActivity(intent);
  }
   //commented out for being confusing
  /*public void onSimpleRollButtonClick(View view) {
     /* temp running of roll for testing purposes*//*
    try {
      ARoll simpleRoll = new SimpleRoll();
      simpleRoll.rollRestaurant();
    }
    catch(Exception e) {
      Log.e("onSimpleRollButton", e.getMessage());
    }
  }*/

  public void onPreferencesRollButtonClick(View view) {
    try {
      ARoll simpleRoll = new PreferencesRoll();
      ((CustomApplication)getApplicationContext()).setRoll(simpleRoll);

      Intent intent = new Intent(this, RestaurantActivity.class);
      startActivity(intent);
    }
    catch(Exception e) {
      Log.e("onSimpleRollButton", e.getMessage());
    }

  }
}
