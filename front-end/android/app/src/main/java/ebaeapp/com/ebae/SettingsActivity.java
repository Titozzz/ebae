package ebaeapp.com.ebae;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.ButterKnife;

import static ebaeapp.com.ebae.R.id.listView;

/**
 * Created by thiba on 14/02/2017.
 */

public class SettingsActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

  //Stuff for dropdown menu. The string array can be moved elsewhere.
  private Spinner spinner;
  private static final String[]paths = {"Please select an item:", "item 1", "item 2"};

  private LinearLayout myLayout;
  private ListView myListView;
  public ArrayList<String> list;
  public PreferenceSingleton prefs = null;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    prefs = PreferenceSingleton.getInstance();
    LoadPreferenceAction.loadPrefs(this);

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
    myListView = (ListView) findViewById(R.id.listView);

    list = new ArrayList<String>();

    final ArrayAdapter adapterList = new ArrayAdapter(this, android.R.layout.simple_list_item_1, list);
    myListView.setAdapter(adapterList);

    myListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
      @Override
      public boolean onItemLongClick(AdapterView<?> av, View v, int pos, long id) {
        String selectedItem = list.get(pos);
        list.remove(selectedItem);
        adapterList.notifyDataSetChanged();
        return true;
      }
    });

    spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

      public void onItemSelected(AdapterView<?> parent, View view, int position, long id){
        if(position != 0) {
          String s = spinner.getItemAtPosition(position).toString();
          list.add(s);
          adapterList.notifyDataSetChanged();
        }
      }

      @Override
      public void onNothingSelected(AdapterView<?> parent) {
        //Do nothing?
      }
    });
  }



  /** Called when the user clicks the roll button */
  public void onCheckClick(View view) {
      // Is the view now checked?
      prefs = PreferenceSingleton.getInstance();

      boolean checked = ((CheckBox) view).isChecked();

      // Check which checkbox was clicked
      switch(view.getId()) {
        case R.id.vegetarian_check:
          if (checked) {
            prefs.lifestyles[Constants.VEGETARIAN_INDEX] = true;
          } //Set lifestyles to true
          else {
            prefs.lifestyles[Constants.VEGETARIAN_INDEX] = false;
          } //Set lifestyle to false
          break;
        case R.id.vegan_check:
          if (checked) {
            prefs.lifestyles[Constants.VEGAN_INDEX] = true;
          } //Set lifestyles to true
          else {
            prefs.lifestyles[Constants.VEGAN_INDEX] = false;
          } //Set lifestyles to false
          break;
        case R.id.gluten_free_check:
          if (checked) {
            prefs.lifestyles[Constants.GLUTEN_FREE_INDEX] = true;
          } //Set lifestyles to true
          else {
            prefs.lifestyles[Constants.GLUTEN_FREE_INDEX] = false;
          } //Set lifestyles to false
          break;
      }
  }

  public void onSubmitClick(View view) {
    Log.i("SettingsActivity", "Prefs: " + prefs.toString());
    SavePreferenceAction.savePrefs(prefs, this);
  }

  //For dropdown menu
  public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {

    /*switch (position) {
      case 0:
        // Zeroth item selected, default
        break;
      case 1:
        // Second item selected
        //myListView.addView(createNewTextView(parent.getItemAtPosition(position).toString()));
        list.add(parent.getItemAtPosition(position).toString());
        break;
      case 2:
        // Third item selected
        //myListView.addView(createNewTextView(parent.getItemAtPosition(position).toString()));
        list.add(parent.getItemAtPosition(position).toString());
        break;

    }*/
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
    textView.setText(text);
    textView.setTextColor(R.color.colorPrimaryDark);
    return textView;
  }





}
