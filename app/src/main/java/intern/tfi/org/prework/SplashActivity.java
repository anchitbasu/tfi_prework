package intern.tfi.org.prework;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class SplashActivity extends Activity {

    ImageView splashBackgroundImageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        splashBackgroundImageView=(ImageView)findViewById(R.id.splash_background_image_view);
        Glide.with(this).load(R.drawable.splash_background).into(splashBackgroundImageView);

        Thread a=new Thread(){
            @Override
            public void run() {
                super.run();
               try {
                   Thread.sleep(5000);
                   Intent intent=new Intent(getApplicationContext(),LoginActivity.class);
                   startActivity(intent);
               }
               catch (Exception e){}
            }
        };
        a.start();
    }
}
