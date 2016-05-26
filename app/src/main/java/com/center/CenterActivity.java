package com.center;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.media.Image;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

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

    /*
    private Button shopButton;
    private Button orderButton;
    private Button meButton;
*/
    private LinearLayout[] linearLayouts=new LinearLayout[3];
    private LinearLayout shopButton;
    private LinearLayout orderButton;
    private LinearLayout meButton;

    private ImageView[] imgs=new ImageView[3];
    private ImageView shopImg;
    private ImageView orderImg;
    private ImageView meImg;

    /*
    private TextView[] texts=new TextView[3];
    private TextView shopText;
    private TextView orderText;
    private TextView meText;

    private int[] textsID={R.id.center_shop_text,R.id.center_order_text,R.id.center_me_text};
    private String[] textsString={"服务中心","订单中心","个人中心"};
*/

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
                    shopImg.setImageResource(R.drawable.shopon);
                    orderImg.setImageResource(R.drawable.orderoff);
                    meImg.setImageResource(R.drawable.meoff);


                    /*
                    shopText.setTextColor(Color.rgb(41,182,246));
                    orderText.setTextColor(Color.rgb(238,238,238));
                    meText.setTextColor(Color.rgb(238,238,238));
                    shopText.setText("服务中心");
                    orderText.setText("订单中心");
                    meText.setText("个人中心");
*/

                    transaction.replace(R.id.content, shopFragment);
                    break;
                case R.id.center_order_button:
                    if(orderFragment==null)
                    {
                        orderFragment=new OrderFragment();
                    }
                    shopImg.setImageResource(R.drawable.shopoff);
                    orderImg.setImageResource(R.drawable.orderon);
                    meImg.setImageResource(R.drawable.meoff);
/*
                    shopText.setText("服务中心");
                    orderText.setText("订单中心");
                    meText.setText("个人中心");
                    shopText.setTextColor(Color.rgb(238,238,238));
                    orderText.setTextColor(Color.rgb(41,182,246));
                    meText.setTextColor(Color.rgb(238,238,238));
*/
                    transaction.replace(R.id.content,orderFragment);
                    break;
                case R.id.center_me_button:
                    if(meFragment==null)
                    {
                        meFragment=new MeFragment();
                    }
                    transaction.replace(R.id.content,meFragment);
                    shopImg.setImageResource(R.drawable.shopoff);
                    orderImg.setImageResource(R.drawable.orderoff);
                    meImg.setImageResource(R.drawable.meon);
/*
                    shopText.setText("服务中心");
                    orderText.setText("订单中心");
                    meText.setText("个人中心");
                    shopText.setTextColor(Color.rgb(238,238,238));
                    orderText.setTextColor(Color.rgb(238,238,238));
                    meText.setTextColor(Color.rgb(41,182,246));

*/
                    transaction.replace(R.id.content,meFragment);
                    break;
            }


            /*
            for(int tt=0;tt<texts.length;tt++){
                texts[tt].setTextColor(Color.rgb(238,238,238));
                if(v.getId()==textsID[tt]){
                    texts[tt].setTextColor(Color.rgb(41,182,246));
                }
                texts[tt].setText(textsString[tt]);
            }
            */
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

        shopImg=(ImageView)findViewById(R.id.center_shop_img);
        orderImg=(ImageView)findViewById(R.id.center_order_img);
        meImg=(ImageView)findViewById(R.id.center_me_img);


        /*
        shopText=(TextView)findViewById(R.id.center_shop_text);
        orderText=(TextView)findViewById(R.id.center_order_text);
        meText=(TextView)findViewById(R.id.center_me_text);

        for(int t=0;t<texts.length;t++){
            texts[t]=(TextView)findViewById(textsID[t]);
        }
*/


        /*
        shopButton=(Button)findViewById(R.id.center_shop_button);
        shopButton.setOnClickListener(onClickListener);
        orderButton=(Button)findViewById(R.id.center_order_button);
        orderButton.setOnClickListener(onClickListener);
        meButton=(Button)findViewById(R.id.center_me_button);
        meButton.setOnClickListener(onClickListener);
        */
        shopButton= (LinearLayout) findViewById(R.id.center_shop_button);
        shopButton.setOnClickListener(onClickListener);
        orderButton= (LinearLayout) findViewById(R.id.center_order_button);
        orderButton.setOnClickListener(onClickListener);
        meButton= (LinearLayout) findViewById(R.id.center_me_button);
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
