package com.center;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.example.apple.shopowner.R;

public class CenterActivity extends Activity
        implements TitleFragment.OnFragmentInteractionListener,
        ShopFragment.OnFragmentInteractionListener,
        OrderFragment.OnFragmentInteractionListener,
        MeFragment.OnFragmentInteractionListener,
        View.OnClickListener {

    public static CenterActivity instance=null;

    private OrderFragment orderFragment;
    private ShopFragment shopFragment;
    private MeFragment meFragment;

    private Button shopButton;
    private Button orderButton;
    private Button meButton;

    private Button.OnClickListener onClickListener=new Button.OnClickListener(){

        @Override
        public void onClick(View v) {
            FragmentManager fm=getFragmentManager();
            FragmentTransaction transaction=fm.beginTransaction();

            switch (v.getId())
            {
                case R.id.center_shop_button:
                    if(shopFragment==null)
                    {
                        shopFragment=new ShopFragment();
                    }
                    transaction.replace(R.id.content, shopFragment);
                    break;
                case R.id.center_order_button:
                    if(orderFragment==null)
                    {
                        orderFragment=new OrderFragment();
                    }
                    transaction.replace(R.id.content,orderFragment);
                    break;
                case R.id.center_me_button:
                    if(meFragment==null)
                    {
                        meFragment=new MeFragment();
                    }
                    transaction.replace(R.id.content,meFragment);
                    break;
            }
            transaction.commit();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_center);
        setDefaultFragment();

        instance=this;
        Log.d("asdfg","f1");

        shopButton=(Button)findViewById(R.id.center_shop_button);
        shopButton.setOnClickListener(onClickListener);
        orderButton=(Button)findViewById(R.id.center_order_button);
        orderButton.setOnClickListener(onClickListener);
        meButton=(Button)findViewById(R.id.center_me_button);
        meButton.setOnClickListener(onClickListener);
    }


    private void setDefaultFragment()
    {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        shopFragment = new ShopFragment();
        transaction.replace(R.id.content, shopFragment);
        transaction.commit();
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
