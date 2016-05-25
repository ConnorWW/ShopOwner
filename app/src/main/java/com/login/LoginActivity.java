package com.login;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.FindCallback;
import com.center.CenterActivity;
import com.example.apple.shopowner.R;
import com.register.RegisterActivity;

import java.util.List;

public class LoginActivity extends Activity {

    private Button login_button;
    private Button register_button;
    private EditText accout_text;
    private EditText password_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_login);

        login_button=(Button)findViewById(R.id.login_button);
        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                accout_text=(EditText)findViewById(R.id.account);
                String account=accout_text.getText().toString();

                password_text=(EditText)findViewById(R.id.password);
                final String password=password_text.getText().toString();

                Toast.makeText(LoginActivity.this, account+":"+password,
                        Toast.LENGTH_SHORT).show();


                final AVObject[] shopOwners = {new AVObject()};
                AVQuery<AVObject> query=new AVQuery<AVObject>("ShopOwner");
                query.whereEqualTo("account",account);
                query.findInBackground(new FindCallback<AVObject>() {
                    @Override
                    public void done(List<AVObject> list, AVException e) {


                        shopOwners[0] =list.get(0);

                        if(password.equals(shopOwners[0].getString("password"))) {
                            Toast.makeText(LoginActivity.this, "succeed!!!",
                                    Toast.LENGTH_SHORT).show();
                            Intent intent=new Intent(LoginActivity.this,CenterActivity.class);
                            intent.putExtra("id",shopOwners[0].getString("idnumber"));
                            startActivity(intent);
                        }
                        else{

                            Toast.makeText(LoginActivity.this, "Account or password is wrong!",
                                    Toast.LENGTH_SHORT).show();
                        }

                    }
                });

            }
        });


        register_button=(Button)findViewById(R.id.register_button);
        register_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);

            }
        });


    }
}
