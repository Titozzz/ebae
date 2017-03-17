package ebaeapp.com.ebae;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.yelp.fusion.client.models.Location;

import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigDecimal;

import butterknife.BindView;
import butterknife.ButterKnife;



/**
 * Created by Hung on 3/9/2017.
 */

public class HistoryRestaurantView extends AppCompatActivity {
    @BindView(R.id.reroll_button)
    FloatingActionButton reroll_button;
    @BindView(R.id.restaurant_image)
    ImageView restaurant_image;
    @BindView(R.id.restaurant_rating)
    RatingBar restaurant_rating;
    @BindView(R.id.restaurant_name)
    TextView restaurant_name;
    @BindView(R.id.restaurant_price)
    TextView restaurant_price;


    JSONObject _businness;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        try {
            _businness = new JSONObject(getIntent().getStringExtra("business"));
            updateActivity();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        reroll_button.setVisibility(View.INVISIBLE);
        /* layout = (ViewGroup) reroll_button.getParent();
        if(null!=layout) //for safety only  as you are doing onClick
            layout.removeView(reroll_button);*/

    }


    private void updateActivity() throws JSONException {
        Picasso.with(getApplicationContext())
                .load(_businness.getString("image_url").replace("ms.jpg", "l.jpg"))
                .into(restaurant_image);
        restaurant_price.setText(_businness.getString("price"));
        restaurant_rating.setRating(((BigDecimal.valueOf(_businness.getDouble("rating")).floatValue())));
        restaurant_name.setText(_businness.getString("name"));
    }


    public void onMapsClick(View view) {
        String uri = null;
        try {
            uri = "geo:"+ "0,0?q=" + _businness.getString("name") + " " + _businness.getString("location");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Intent myMapIntent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse(uri));
        startActivity(myMapIntent);
    }

    public void onYelpClick(View view) {
        Intent myYelpIntent = null;
        try {
            myYelpIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(_businness.getString("url")));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        startActivity(myYelpIntent);
    }

    public void onShareClick(View view) {
        Intent myIntent = new Intent(Intent.ACTION_SEND);
        myIntent.setType("text/plain"); // font
        String shareBody = null; // url, cuisine, rating, $$, dist, address?
        String shareSubject = "";
        try {
            shareBody = _businness.getString("url");
            shareSubject = _businness.getString("name");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        myIntent.putExtra(Intent.EXTRA_SUBJECT, shareSubject);
        myIntent.putExtra(Intent.EXTRA_TEXT, shareBody);
        startActivity(Intent.createChooser(myIntent, "Share With")); // title of popup
    }

}
