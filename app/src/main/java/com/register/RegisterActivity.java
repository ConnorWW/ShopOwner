package com.register;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.avos.avoscloud.AVObject;
import com.example.apple.shopowner.R;

import java.util.Calendar;

public class RegisterActivity extends Activity {

    private Button openShop;
    private Button skip;

    private EditText account_e;
    private EditText password_e;
    private EditText passwordagain_e;
    private EditText id_e;
    private EditText name_e;
    private EditText email_e;

    String account;
    String password;
    String passwordagain;
    String id;
    String name;
    String email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_register);



        skip=(Button)findViewById(R.id.register_skip_button);
        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(RegisterActivity.this,OpenShopActivity.class);
                startActivity(intent);
            }
        });

        openShop=(Button)findViewById(R.id.register_openshop_button);
        openShop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                account_e=(EditText)findViewById(R.id.register_account);
                password_e=(EditText)findViewById(R.id.register_password);
                passwordagain_e=(EditText)findViewById(R.id.register_passwordagain);
                id_e=(EditText)findViewById(R.id.register_id);
                name_e=(EditText)findViewById(R.id.register_name);
                email_e=(EditText)findViewById(R.id.register_email);

                account=account_e.getText().toString();
                password=password_e.getText().toString();
                passwordagain=passwordagain_e.getText().toString();
                id=id_e.getText().toString();
                name=name_e.getText().toString();
                email=email_e.getText().toString();

                Calendar time=Calendar.getInstance();
                int year=Calendar.YEAR;
                int month=Calendar.MONTH+1;
                int day=Calendar.DAY_OF_MONTH;
                int hour=Calendar.HOUR_OF_DAY;
                int minute=Calendar.MINUTE;


                Toast.makeText(RegisterActivity.this, account+"."+
                                password+"."+passwordagain+"."+id+"."+name+"."+email,
                        Toast.LENGTH_SHORT).show();

                if(password.equals(passwordagain)){
                    AVObject shopOwner=new AVObject("ShopOwner");
                    shopOwner.put("account",account);
                    shopOwner.put("password",password);
                    //shopOwner.put("passwordagain",passwordagain);
                    shopOwner.put("idnumber",id);
                    shopOwner.put("name",name);
                    shopOwner.put("email",email);
                    shopOwner.saveInBackground();

                    Intent intent=new Intent(RegisterActivity.this,OpenShopActivity.class);
                    intent.putExtra("idnumber",id);
                    //intent.putExtra("shopOwner",shopOwner);
                    startActivity(intent);

                    Toast.makeText(RegisterActivity.this, "succeed!",
                            Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(RegisterActivity.this, "Two passwords is not equal!",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
