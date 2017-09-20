package con.gionee.bloodsoulnote.layout;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import con.gionee.bloodsoulnote.R;

public class LayoutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout);

        ImageView img = (ImageView) findViewById(R.id.img);
    }
}
