package ebaeapp.com.ebae;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.ButterKnife;

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
    LoadPreferenceAction.loadPrefs(this); //changes the singleton object to the file's data
    prefs = PreferenceSingleton.getInstance(); //updates singleton.

    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_settings);
    ButterKnife.bind(this);
    Intent intent = getIntent();

    final RatingBar ratingBar = (RatingBar) findViewById(R.id.ratingBar);
    final SeekBar seekBar = (SeekBar) findViewById(R.id.distance_bar);
    final SeekBar seekBar2 = (SeekBar) findViewById(R.id.price_bar);

    spinner = (Spinner)findViewById(R.id.spinner);
    ArrayAdapter<String> adapter = new ArrayAdapter<String>(SettingsActivity.this,
            android.R.layout.simple_spinner_item,paths);

    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    spinner.setAdapter(adapter);
    spinner.setOnItemSelectedListener(this);

    myLayout = (LinearLayout) findViewById(R.id.linearLayout);
    myListView = (ListView) findViewById(R.id.listView);

    list = new ArrayList<String>();

    final ArrayAdapter adapterList = new ArrayAdapter(this, R.layout.list_view_text_style, list);
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

    ratingBar.setOnTouchListener(new View.OnTouchListener() {
      @Override
      public boolean onTouch(View v, MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_UP) {
          float touchPositionX = event.getX();
          float width = ratingBar.getWidth();
          float starsf = (touchPositionX / width) * 5.0f;
          int stars = (int)starsf + 1;
          ratingBar.setRating(stars);

          v.setPressed(false);
        }
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
          v.setPressed(true);
        }

        if (event.getAction() == MotionEvent.ACTION_CANCEL) {
          v.setPressed(false);
        }

        return true;
      }});

    //Distance bar
    seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
      private int mProgressAtStartTracking;
      private final int SENSITIVITY = 30;

      @Override
      public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
        // handle progress change
      }

      @Override
      public void onStartTrackingTouch(SeekBar seekBar) {
        mProgressAtStartTracking = seekBar.getProgress();
      }

      @Override
      public void onStopTrackingTouch(SeekBar seekBar) {
        if(Math.abs(mProgressAtStartTracking - seekBar.getProgress()) <= SENSITIVITY){
          // react to thumb click
        }
      }
    });

    //Price bar
    seekBar2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
      private int mProgressAtStartTracking;
      private final int SENSITIVITY = 30;

      @Override
      public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
        // handle progress change
      }

      @Override
      public void onStartTrackingTouch(SeekBar seekBar) {
        mProgressAtStartTracking = seekBar.getProgress();
      }

      @Override
      public void onStopTrackingTouch(SeekBar seekBar) {
        if(Math.abs(mProgressAtStartTracking - seekBar.getProgress()) <= SENSITIVITY){
          // react to thumb click
        }
      }
    });


    updateSettingsPage();//method to fix lists

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

  private void updateSettingsPage() {
    if(prefs.lifestyles[Constants.VEGETARIAN_INDEX] == true) {
      CheckBox vegBox = (CheckBox)findViewById(R.id.vegetarian_check);
      vegBox.setChecked(true);
    }
    if(prefs.lifestyles[Constants.VEGAN_INDEX] == true) {
      CheckBox veganBox = (CheckBox)findViewById(R.id.vegan_check);
      veganBox.setChecked(true);
    }
    if(prefs.lifestyles[Constants.GLUTEN_FREE_INDEX] == true) {
      CheckBox glutBox = (CheckBox)findViewById(R.id.gluten_free_check);
      glutBox.setChecked(true);
    }
  }





}
