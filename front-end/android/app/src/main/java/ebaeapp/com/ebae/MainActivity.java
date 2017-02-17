package ebaeapp.com.ebae;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

  @BindView(R.id.history_button)
  FloatingActionButton history_button;
  @BindView(R.id.history_button)
  FloatingActionButton settings_button;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ButterKnife.bind(this);


  }

  public void onHistoryButtonClick(View view) {

  }

  public void onSettingsButtonClick(View view) {

  }
}
