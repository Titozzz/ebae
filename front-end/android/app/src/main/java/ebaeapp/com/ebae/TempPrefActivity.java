package ebaeapp.com.ebae;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.ButterKnife;

/* Made by Tuan on 2/27/17 */

public class TempPrefActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    //Stuff for dropdown menu. The string array can be moved elsewhere.
    private Spinner spinner;
    private static final String[] paths = {"Please select an item:", "American", "Asian", "Bakeries/Cafes",
            "Bars", "BBQ", "Brunch", "Fast Food", "Indian", "Italian", "Mediterranean", "Mexican", "Seafood"};

    private ListView myListView;
    public ArrayList<String> list;
    public PreferenceSingleton prefs = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //LoadPreferenceAction.loadPrefs(this); //changes the singleton object to the file's data
        prefs = PreferenceSingleton.getInstance(); //updates singleton.

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temp_pref);
        ButterKnife.bind(this);
        Intent intent = getIntent();

        final RatingBar ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        final SeekBar seekBar = (SeekBar) findViewById(R.id.distance_bar);
        final SeekBar seekBar2 = (SeekBar) findViewById(R.id.price_bar);

        final TextView ratingBarValue = (TextView) findViewById(R.id.rating_label);
        final TextView distanceBarValue = (TextView) findViewById(R.id.distance_label);
        final TextView priceBarValue = (TextView) findViewById(R.id.price_label);

        spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(TempPrefActivity.this,
                android.R.layout.simple_spinner_item, paths);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        myListView = (ListView) findViewById(R.id.listView);
        myListView.setSelector(R.drawable.list_selector);

        list = new ArrayList<String>();

        final ArrayAdapter adapterList = new ArrayAdapter<String>(this, R.layout.list_view_text_style,
                R.id.text1, list);
        myListView.setAdapter(adapterList);

        myListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> av, View v, int pos, long id) {
                String selectedItem = list.get(pos);

                //remove item from preferences singleton list
                int spinnerPosition = adapter.getPosition(selectedItem);
                prefs.dislikes[spinnerPosition - 1] = false;

                list.remove(selectedItem);
                adapterList.notifyDataSetChanged();
                return true;
            }
        });

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (position != 0) {

                    String s = spinner.getItemAtPosition(position).toString();
                    boolean inList = false;

                    //check if the item is already in the list
                    if (list.size() != 0) {
                        for (int i = 0; i < list.size(); i++) {
                            if (list.get(i).toString().equals(s)) {
                                inList = true;
                            }
                        }
                    }

                    if (!inList) {
                        list.add(s);
                        adapterList.notifyDataSetChanged();
                        prefs.dislikes[position - 1] = true;
                    }
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
                    int stars = (int) starsf + 1;
                    if (stars > 5) {
                        stars = 5;
                    }
                    if (stars < 1) {
                        stars = 1;
                    }
                    ratingBar.setRating(stars);
                    ratingBarValue.setText("Restaurant Rating: " + String.valueOf(stars) + " star(s)");
                    prefs.sliders[0] = stars;

                    v.setPressed(false);
                }
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    v.setPressed(true);
                }

                if (event.getAction() == MotionEvent.ACTION_CANCEL) {
                    v.setPressed(false);
                }

                return true;
            }
        });

        //Distance bar
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            private int mProgressAtStartTracking;
            private final int SENSITIVITY = 30;

            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if (i < 1) {
                    seekBar.setProgress(1);
                    i = 1;
                }
                distanceBarValue.setText("Restaurant Distance: " + String.valueOf(i * 5) + " miles");
                prefs.sliders[1] = i;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                mProgressAtStartTracking = seekBar.getProgress();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                if (Math.abs(mProgressAtStartTracking - seekBar.getProgress()) <= SENSITIVITY) {
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
                if (i < 1) {
                    seekBar.setProgress(1);
                    i = 1;
                }
                String priceLevel = "";
                switch (i) {
                    case 1:
                        priceLevel = "Cheap";
                        break;
                    case 2:
                        priceLevel = "Semi-Cheap";
                        break;
                    case 3:
                        priceLevel = "Moderate";
                        break;
                    case 4:
                        priceLevel = "Semi-Expensive";
                        break;
                    case 5:
                        priceLevel = "Expensive";
                        break;
                }

                priceBarValue.setText("Restaurant Price: " + priceLevel);
                prefs.sliders[2] = i;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                mProgressAtStartTracking = seekBar.getProgress();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                if (Math.abs(mProgressAtStartTracking - seekBar.getProgress()) <= SENSITIVITY) {
                    // react to thumb click
                }
            }
        });


        updateSettingsPage();//method to fix lists

    }


    /**
     * Called when the user clicks the roll button
     */
    public void onCheckClick(View view) {
        // Is the view now checked?
        prefs = PreferenceSingleton.getInstance();

        boolean checked = ((CheckBox) view).isChecked();

        // Check which checkbox was clicked
        switch (view.getId()) {
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
        //Intent intent = new Intent(this, MainActivity.class);
        //startActivity(intent);
        finish();
    }

    //For dropdown menu
    public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {
        //do nothing?
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        //do something?
    }


    private void updateSettingsPage() {

        int i;

        CheckBox vegBox = (CheckBox) findViewById(R.id.vegetarian_check);
        CheckBox veganBox = (CheckBox) findViewById(R.id.vegan_check);
        CheckBox glutBox = (CheckBox) findViewById(R.id.gluten_free_check);

        if (prefs.lifestyles[Constants.VEGETARIAN_INDEX] == true) {
            vegBox.setChecked(true);
        } else {
            vegBox.setChecked(false);
        }
        if (prefs.lifestyles[Constants.VEGAN_INDEX] == true) {

            veganBox.setChecked(true);
        } else {
            veganBox.setChecked(false);
        }
        if (prefs.lifestyles[Constants.GLUTEN_FREE_INDEX] == true) {
            glutBox.setChecked(true);
        } else {
            glutBox.setChecked(false);
        }


        myListView = (ListView) findViewById(R.id.listView);
        ArrayAdapter listAdapter = (ArrayAdapter) myListView.getAdapter();

        //Clear dislikes list, then update it.
        while (!list.isEmpty()) {
            list.remove(0);
        }

        listAdapter.notifyDataSetChanged();

        for (i = 1; i < paths.length; i++) {
            if (prefs.dislikes[i - 1]) {
                String s = spinner.getItemAtPosition(i).toString();
                list.add(s);
                prefs.dislikes[i - 1] = true;
            }
        }
        listAdapter.notifyDataSetChanged();


        //Update the sliders.
        RatingBar ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        SeekBar seekBar = (SeekBar) findViewById(R.id.distance_bar);
        SeekBar seekBar2 = (SeekBar) findViewById(R.id.price_bar);

        ratingBar.setProgress(prefs.sliders[0]);
        seekBar.setProgress(prefs.sliders[1]);
        seekBar2.setProgress(prefs.sliders[2]);

        //Update slider text.
        final TextView ratingBarValue = (TextView) findViewById(R.id.rating_label);
        final TextView distanceBarValue = (TextView) findViewById(R.id.distance_label);
        final TextView priceBarValue = (TextView) findViewById(R.id.price_label);

        ratingBarValue.setText("Restaurant Rating: " + String.valueOf(ratingBar.getProgress()) + " star(s)");
        distanceBarValue.setText("Restaurant Distance: " + String.valueOf(seekBar.getProgress() * 5) + " miles");
        String priceSet = "";
        switch (seekBar2.getProgress()) {
            case 1:
                priceSet = "Cheap";
                break;
            case 2:
                priceSet = "Semi-Cheap";
                break;
            case 3:
                priceSet = "Moderate";
                break;
            case 4:
                priceSet = "Semi-Expensive";
                break;
            case 5:
                priceSet = "Expensive";
                break;
        }
        priceBarValue.setText("Restaurant Price: " + priceSet);


    }

    public void onSelectAllClick(View view) {

        int i;
        //Select lifestyle choices
        /*for (i = 0; i < prefs.lifestyles.length; i++) {
            prefs.lifestyles[i] = true;
        }*/

        //Select category choices
        for (i = 0; i < prefs.dislikes.length; i++) {
            prefs.dislikes[i] = true;
        }

        updateSettingsPage();
        Log.i("SettingsActivity", "Prefs: " + prefs.toString());
    }

    public void onClearAllClick(View view) {
        int i;

        //Clear lifestyle choices
        for (i = 0; i < prefs.lifestyles.length; i++) {
            prefs.lifestyles[i] = false;
        }

        //Clear category choices
        for (i = 0; i < prefs.dislikes.length; i++) {
            prefs.dislikes[i] = false;
        }

        //Reset sliders
        prefs.sliders[0] = 2;
        prefs.sliders[1] = 1;
        prefs.sliders[2] = 3;

        updateSettingsPage();
        Log.i("SettingsActivity", "Prefs: " + prefs.toString());
    }
}