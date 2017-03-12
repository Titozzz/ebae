package ebaeapp.com.ebae;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.util.Log;
import android.view.View;


import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity{

  int PERMISSION_LOCATION_REQUEST_CODE = 0;
  //For location tracking
  LocationListener locationListener;
  LocationManager locationManager;

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


    locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
    // Define a listener that responds to location updates

    locationListener = new LocationListener() {
      public void onLocationChanged(Location location) {
        // Called when a new location is found by the network location provider.
        Log.e("Updating Location:", "AHHHHHHHHHHHHHHHHHHHHHH");
        LocationUpdateAction.updateCoordinates(location);
      }

      public void onStatusChanged(String provider, int status, Bundle extras) {
      }

      public void onProviderEnabled(String provider) {
      }

      public void onProviderDisabled(String provider) {
      }
    };

// Register the listener with the Location Manager to receive location updates
    if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
      Log.e("Attempting Permissions:", "DENIED");

        // TODO: Consider calling
        //    ActivityCompat#requestPermissions
        // here to request the missing permissions, and then overriding
        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
        //                                          int[] grantResults)
        // to handle the case where the user grants the permission. See the documentation
        // for ActivityCompat#requestPermissions for more details.
      ActivityCompat.requestPermissions(
              this,
              new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION},
              PERMISSION_LOCATION_REQUEST_CODE);

      locationListener = new LocationListener() {
        public void onLocationChanged(Location location) {
          // Called when a new location is found by the network location provider.
          Log.e("Updating Location:", "AHHHHHHHHHHHHHHHHHHHHHH");
          LocationUpdateAction.updateCoordinates(location);
        }

        public void onStatusChanged(String provider, int status, Bundle extras) {
        }

        public void onProviderEnabled(String provider) {
        }

        public void onProviderDisabled(String provider) {
        }
      };
      locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);

      return;
    }
    locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);


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


  //below is the code to find the locaation

}
