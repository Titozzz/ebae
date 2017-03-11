package ebaeapp.com.ebae;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.squareup.picasso.Picasso;
import com.yelp.fusion.client.models.Business;

/**
 * Created by thiba on 14/02/2017.
 */

public class RestaurantActivity extends AppCompatActivity {
  private ARoll _roll;
  private Business _businness;

  @BindView(R.id.reroll_button)
  FloatingActionButton reroll_button;
  @BindView(R.id.restaurant_image)
  ImageView restaurant_image;
  @BindView(R.id.restaurant_rating)
  ImageView restaurant_rating;
  @BindView(R.id.restaurant_name)
  TextView restaurant_name;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_restaurant);
    ButterKnife.bind(this);
    Intent intent = getIntent();

    _roll = ((CustomApplication)getApplicationContext()).getRoll();
    displayNextRestaurant();
  }

  private void updateActivity() {
    Picasso.with(getApplicationContext())
        .load(_businness.getImageUrl().replace("ms.jpg", "l.jpg"))
        .into(restaurant_image);
    /*Picasso.with(getApplicationContext())
        .load(_businness.getRating())
        .into(restaurant_rating);*/

    restaurant_name.setText(_businness.getName());
  }

  private void displayNextRestaurant() {
    _roll.getNextRestaurant(
        business->{
          _businness = business;
          updateActivity();
          //save business file for history functionality
          SaveBusinessAction.saveBusiness(business, this);
    }, ()->{
      Log.e("Business get", "Failed :(");
    });
  }

  public void onRerollButtonClick(View view) {
    displayNextRestaurant();
  }

  public void onMapsClick(View view) {
    String uri = "geo:"+ "0,0?q=" + _businness.getName() + " " + _businness.getLocation().getCity();
    Intent myMapIntent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse(uri));
    startActivity(myMapIntent);
  }

  public void onYelpClick(View view) {
    Intent myYelpIntent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse(_businness.getUrl()));
    startActivity(myYelpIntent);
  }

  public void onShareClick(View view) {
    Intent myIntent = new Intent(Intent.ACTION_SEND);
    myIntent.setType("text/plain"); // font
    String shareBody = _businness.getUrl(); // url, cuisine, rating, $$, dist, address?
    String shareSubject = _businness.getName();
    myIntent.putExtra(Intent.EXTRA_SUBJECT, shareSubject);
    myIntent.putExtra(Intent.EXTRA_TEXT, shareBody);
    startActivity(Intent.createChooser(myIntent, "Share With")); // title of popup
  }
}
