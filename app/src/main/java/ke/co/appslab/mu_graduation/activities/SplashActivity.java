package ke.co.appslab.mu_graduation.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;


/**
 * Created by root on 4/14/16.
 */
public class SplashActivity extends Activity {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //start the mainactivity after showing the splash screen
        Intent mainact = new Intent(this, MainActivity.class);
        startActivity(mainact);
        finish();
    }
}
