package ebaeapp.com.ebae;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import butterknife.ButterKnife;

/**
 * Created by thiba on 14/02/2017.
 */

public class SettingsActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

  //Stuff for dropdown menu. The string array can be moved elsewhere.
  private Spinner spinner;
  private static final String[]paths = {"Please select an item:", "item 1", "item 2"};

  private LinearLayout myLayout;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_settings);
    ButterKnife.bind(this);
    Intent intent = getIntent();

    spinner = (Spinner)findViewById(R.id.spinner);
    ArrayAdapter<String> adapter = new ArrayAdapter<String>(SettingsActivity.this,
            android.R.layout.simple_spinner_item,paths);

    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    spinner.setAdapter(adapter);
    spinner.setOnItemSelectedListener(this);

    myLayout = (LinearLayout) findViewById(R.id.linearLayout);
    TextView textView = new TextView(this);
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

  //For dropdown menu
  public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {

    switch (position) {
      case 0:
        // Zeroth item selected, default
        break;
      case 1:
        // Second item selected
        myLayout.addView(createNewTextView(parent.getItemAtPosition(position).toString()));
        break;
      case 2:
        // Third item selected
        myLayout.addView(createNewTextView(parent.getItemAtPosition(position).toString()));
        break;

    }
  }

  @Override
  public void onNothingSelected(AdapterView<?> parent) {
    //do something?
  }

  private TextView createNewTextView(String text) {
    final LinearLayout.LayoutParams lparams
            = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
    final TextView textView = new TextView(this);
    textView.setLayoutParams(lparams);
    textView.setText("New text: " + text);
    textView.setTextColor(R.color.colorPrimaryDark);
    return textView;
  }


}
