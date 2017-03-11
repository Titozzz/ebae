package ebaeapp.com.ebae;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;

import static ebaeapp.com.ebae.R.id.restaurant_image;
import static ebaeapp.com.ebae.R.id.restaurant_name;
import static ebaeapp.com.ebae.R.id.restaurant_rating;

/**
 * Created by Hung on 3/9/2017.
 */

public class HistoryRestaurantActivity extends AppCompatActivity {
    @BindView(R.id.reroll_button)
    FloatingActionButton reroll_button;
    @BindView(R.id.restaurant_image)
    ImageView restaurant_image;
    @BindView(R.id.restaurant_rating)
    ImageView restaurant_rating;
    @BindView(R.id.restaurant_name)
    TextView restaurant_name;


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
        Log.i("History Restaurant", "Trying to update the Screen");
        Picasso.with(getApplicationContext())
               .load(_businness.getString("image_url").replace("ms.jpg", "l.jpg"))
               .into(restaurant_image);

        Picasso.with(getApplicationContext())
                .load(_businness.getString("rating_img_url"))
                .into(restaurant_rating);

        restaurant_name.setText(_businness.getString("name"));
    }
}
