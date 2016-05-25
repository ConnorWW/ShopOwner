package com.register;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.avos.avoscloud.AVGeoPoint;
import com.avos.avoscloud.AVObject;
import com.center.CenterActivity;
import com.example.apple.shopowner.R;

import java.util.List;

public class OpenShopActivity extends Activity {
    EditText eshopName;
    EditText eshopIntro;
    EditText eshopLocation;
    EditText eid;

    Button openShop_button;

    String shopName;
    String shopIntro;
    String shopLocation;
    String id;

    private LocationManager locationManager;
    private String locationProvider;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_open_shop);

        Intent intent = getIntent();
        String idnumber = intent.getStringExtra("idnumber");
        eid = (EditText) findViewById(R.id.openshop_id);
        eid.setText(idnumber);

        eshopName = (EditText) findViewById(R.id.openshop_shopname);
        eshopIntro = (EditText) findViewById(R.id.openshop_shopinfo);
        eshopLocation = (EditText) findViewById(R.id.openshop_shoplocation);
        openShop_button = (Button) findViewById(R.id.openshop_button);

        //获取地理位置管理器
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        //获取所有可用的位置提供器
        List<String> providers = locationManager.getProviders(true);
        if (providers.contains(LocationManager.GPS_PROVIDER)) {
            //如果是GPS
            locationProvider = LocationManager.GPS_PROVIDER;
        } else if (providers.contains(LocationManager.NETWORK_PROVIDER)) {
            //如果是Network
            locationProvider = LocationManager.NETWORK_PROVIDER;
        } else {

            Toast.makeText(this, "没有可用的位置提供器", Toast.LENGTH_SHORT).show();
            return;
        }
        //获取Location
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }

        final Location location = locationManager.getLastKnownLocation(locationProvider);
        if(location!=null){
            //不为空,显示地理位置经纬度
            //showLocation(location);
            Log.d("location",location.getLatitude()+":-------------"+location.getLongitude());
            Toast.makeText(this,location.getLatitude()+":-------------"+location.getLongitude(), Toast.LENGTH_SHORT).show();

        }


        openShop_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                shopName = eshopName.getText().toString();
                shopIntro = eshopIntro.getText().toString();
                shopLocation = eshopLocation.getText().toString();
                id = eid.getText().toString();


                AVGeoPoint point = new AVGeoPoint(location.getLatitude(), location.getLongitude());

                AVObject shop = new AVObject("Shop");
                shop.put("idnumber", id);
                shop.put("shopname", shopName);
                shop.put("shopintro", shopIntro);
                shop.put("shoplocation", shopLocation);
                shop.put("point", point);
                shop.saveInBackground();

                Intent intent = new Intent(OpenShopActivity.this, CenterActivity.class);
                startActivity(intent);
            }
        });

    }
}
