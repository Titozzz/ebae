package ebaeapp.com.ebae;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by thiba on 14/02/2017.
 */

public class HistoryActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_history);
    LinearLayout linearLayout = (LinearLayout) findViewById(R.id.linearLayout);
    linearLayout.setOrientation(LinearLayout.VERTICAL);
    JSONObject[] businesses = LoadBusinessAction.loadBusinesses(this);
    for(int buttonIndex = 0; buttonIndex < businesses.length; buttonIndex++) {
      Button bt = new Button(this);
      try {
        bt.setText(businesses[buttonIndex].getString("name"));
      } catch (JSONException e) {
        e.printStackTrace();
        continue;
      }
      bt.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
              LinearLayout.LayoutParams.WRAP_CONTENT));
      JSONObject currButtonJson = businesses[buttonIndex];
      bt.setOnClickListener(new View.OnClickListener() {
          public void onClick(View v) {
            startHistRestScreen(currButtonJson);
          }
      });


      linearLayout.addView(bt);
    }
    //setContentView(linearLayout);
    ButterKnife.bind(this);
    Intent intent = getIntent();
  }

  private void startHistRestScreen(JSONObject passBusiness) {

    Intent intent = new Intent(HistoryActivity.this, HistoryRestaurantActivity.class);
    intent.putExtra("business", passBusiness.toString());
    startActivity(intent);
    finish();

  }


}
