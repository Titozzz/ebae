package ebaeapp.com.ebae;

import android.content.Intent;
import android.media.audiofx.BassBoost;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

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

    /* temp running of roll for testing purposes*/
    try {
      Roll rollTest = new SimpleRoll();
      System.err.println("Constructor finished");
      rollTest.rollRestaurant();
    }
    catch(Exception e) {
      System.err.println(e);
      System.err.println("FALSEFALSEFALSE");
      //assert(false);

    }

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
    Intent intent = new Intent(this, RestaurantActivity.class);
    startActivity(intent);
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
}
