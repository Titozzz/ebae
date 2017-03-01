package ebaeapp.com.ebae;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;

import butterknife.ButterKnife;

/**
 * Created by thiba on 14/02/2017.
 */

public class SettingsActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_settings);
    ButterKnife.bind(this);
    Intent intent = getIntent();
  }

  /** Called when the user clicks the roll button */
  public void onCheckClick(View view) {
      // Is the view now checked?
      boolean checked = ((CheckBox) view).isChecked();

      // Check which checkbox was clicked
      switch(view.getId()) {
        case R.id.vegetarian_check:
          if (checked) {} //Do something
          else {} //Do something
          break;
        case R.id.vegan_check:
          if (checked) {} //Do something
          else {} //Do something
          break;
        case R.id.gluten_free_check:
          if (checked) {} //Do something
          else {} //Do something
          break;
      }
  }
}
