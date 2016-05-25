package com.welcome;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.avos.avoscloud.AVAnalytics;
import com.avos.avoscloud.AVOSCloud;
import com.avos.avoscloud.AVObject;
import com.example.apple.shopowner.R;
import com.login.LoginActivity;

public class Welcome extends Activity {

    private Button welcome_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        AVOSCloud.initialize(this, "C0LYqex56GYzDV7PCFPCXpUF-gzGzoHsz", "SXhQE3g6oGpAbaB9o1Qrb6Bo");
        AVAnalytics.trackAppOpened(getIntent());
        AVObject testObject = new AVObject("RRRRRRRR");
        testObject.put("foo", "bar");
        testObject.saveInBackground();

        welcome_button=(Button)findViewById(R.id.welcome_button);
        welcome_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Welcome.this,LoginActivity.class);
                startActivity(intent);
                finish();

            }
        });
    }
}
