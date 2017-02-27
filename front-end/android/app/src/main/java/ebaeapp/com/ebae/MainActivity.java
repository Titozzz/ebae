package ebaeapp.com.ebae;

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
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

  @BindView(R.id.history_button)
  FloatingActionButton history_button;
  @BindView(R.id.history_button)
  FloatingActionButton settings_button;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ButterKnife.bind(this);

  }

  public void onHistoryButtonClick(View view) {

  }

  public void onSettingsButtonClick(View view) {

  }

  public void onSimpleRollButtonClick(View view) {
     /* temp running of roll for testing purposes*/
    try {
      ARoll simpleRoll = new SimpleRoll();
      simpleRoll.rollRestaurant();
    }
    catch(Exception e) {
      Log.e("onSimpleRollButton", e.getMessage());
    }
  }

  public void onPreferencesRollButtonClick(View view) {
     /* temp running of roll for testing purposes*/
    try {
      ARoll preferencesRoll = new PreferencesRoll();
      preferencesRoll.rollRestaurant();
    }
    catch(Exception e) {
      Log.e("onPreferencesRollButton", e.getMessage());
    }
  }
}
