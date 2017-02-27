package ebaeapp.com.ebae;

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

  public void onHistoryButtonClick(View view) {

  }

  public void onSettingsButtonClick(View view) {

  }

  public void onSimpleRollButtonClick(View view) {
  }

  public void onPreferencesRollButtonClick(View view) {
  }
}
