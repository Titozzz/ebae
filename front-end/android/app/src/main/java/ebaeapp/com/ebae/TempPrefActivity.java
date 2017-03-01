package ebaeapp.com.ebae;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import butterknife.ButterKnife;

/* Made by Tuan on 2/27/17 */

public class TempPrefActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temp_pref);
        ButterKnife.bind(this);
        Intent intent = getIntent();
    }
}
