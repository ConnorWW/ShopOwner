package com.center.shop;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.avos.avoscloud.AVCloudQueryResult;
import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.CloudQueryCallback;
import com.center.CenterActivity;
import com.example.apple.shopowner.R;
import com.register.IDGenerator;

import java.util.ArrayList;
import java.util.List;

public class AddProductActivity extends Activity {
    EditText eproductName;
    EditText eproductPrice;
    EditText eproductDis;
    EditText eproductType2;

    String productName;
    String productPrice;
    String productDis;
    String productType2;

    String id;
    String type;
    String productNumber;

    Spinner typeSpinner;
    ArrayAdapter<String> adapter;
    Button add;
    Button back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_add_product);

        typeSpinner=(Spinner)findViewById(R.id.addproduct_type);
        adapter=new ArrayAdapter<String>(AddProductActivity.this,android.R.layout.simple_spinner_dropdown_item,getDataSource());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        typeSpinner.setAdapter(adapter);


        eproductName=(EditText)findViewById(R.id.addproduct_name);
        eproductPrice=(EditText)findViewById(R.id.addproduct_price);
        eproductDis=(EditText)findViewById(R.id.addproduct_discription);
        eproductType2=(EditText)findViewById(R.id.addproduct_type2);


        back=(Button)findViewById(R.id.addproduct_back_button);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        add=(Button)findViewById(R.id.addproduct_add_button);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                productName=eproductName.getText().toString();
                productPrice=eproductPrice.getText().toString();
                productDis=eproductDis.getText().toString();
                productType2=eproductType2.getText().toString();

                id=getIntent().getStringExtra("id");

                IDGenerator idg=new IDGenerator();
                productNumber=idg.getID();
                Log.d("productNumber", productNumber);

                type=typeSpinner.getSelectedItem().toString();

                AVObject product=new AVObject("Product");
                product.put("productname",productName);
                product.put("productprice",productPrice);
                product.put("productdis",productDis);
                product.put("idnumber",id);
                product.put("productnumber", productNumber);
                product.put("sell","0");
                product.put("score","0");
                product.put("producttype",type);
                product.put("producttype2",productType2);
                product.put("onsell","1");
                product.saveInBackground();

                CenterActivity.instance.finish();
                Intent intent=new Intent(AddProductActivity.this,CenterActivity.class);
                intent.putExtra("id",id);
                startActivity(intent);

                finish();

            }
        });
    }
    public List<String> getDataSource(){

        final List<String> list=new ArrayList<String>();
        String cql = "select * from ProductType";
        AVQuery.doCloudQueryInBackground(cql, new CloudQueryCallback<AVCloudQueryResult>() {
            @Override
            public void done(AVCloudQueryResult avCloudQueryResult, AVException e) {

                List<AVObject> results= (List<AVObject>) avCloudQueryResult.getResults();
                for(int i=0;i<results.size();i++){
                    AVObject result=results.get(i);
                    list.add(result.getString("producttype"));
                }

            }
        });

        /*
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
        list.add("6");
        */
        list.add("fuc");
        return list;
    }
}
