package ebaeapp.com.ebae;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;

import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.squareup.picasso.Picasso;
import com.yelp.clientlib.entities.Business;

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
        .load(_businness.imageUrl().replace("ms.jpg", "l.jpg"))
        .into(restaurant_image);
    Picasso.with(getApplicationContext())
        .load(_businness.ratingImgUrlLarge())
        .into(restaurant_rating);

    restaurant_name.setText(_businness.name());
  }

  private void displayNextRestaurant() {
    _roll.getNextRestaurant(
        business->{
          _businness = business;
          updateActivity();
    }, ()->{

    });
  }

  public void onRerollButtonClick(View view) {
    displayNextRestaurant();
  }
}
