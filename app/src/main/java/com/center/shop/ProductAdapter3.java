package com.center.shop;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.center.CenterActivity;
import com.center.shop.linkedlistview.SectionedBaseAdapter;
import com.example.apple.shopowner.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ProductAdapter3 extends SectionedBaseAdapter {

    private Context mContext;
    List<String> productListLeft =  new ArrayList<String>();
    private List<ArrayList<Product>> productLists = new ArrayList<ArrayList<Product>>();

    public ProductAdapter3(Context mContext, List<String> productListLeft, List<ArrayList<Product>> productLists) {
        this.mContext = mContext;
        this.productListLeft = productListLeft;
        this.productLists = productLists;
    }


    @Override
    public Object getItem(int section, int position) {
        return productLists.get(section).get(position);
    }

    @Override
    public long getItemId(int section, int position) {
        return position;
    }

    @Override
    public int getSectionCount() {
        return productListLeft.size();
    }

    @Override
    public int getCountForSection(int section) {
        return productLists.get(section).size();
    }


    @Override
    public View getItemView(final int section, final int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        LinearLayout layout = null;
        if (convertView== null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.product_item, null);
            holder.productName = (TextView) convertView.findViewById(R.id.product_name);

            holder.productType=(TextView)convertView.findViewById(R.id.product_type);

            holder.productDis=(TextView)convertView.findViewById(R.id.product_dis);

            holder.productSell=(TextView)convertView.findViewById(R.id.product_sell);

            holder.productPrice=(TextView)convertView.findViewById(R.id.product_price);

            holder.productDelete=(Button)convertView.findViewById(R.id.product_delete);

            holder.productStopsell=(Button)convertView.findViewById(R.id.product_stopsell);
//缓存
            convertView.setTag(holder);
        } else {
            //得到缓存
            holder = (ViewHolder) convertView.getTag();
        };

        if (productListLeft.size() != 0) {

            holder.productName.setText(productLists.get(section).get(position).getName()+"("+productLists.get(section).get(position).getPid()+")");

            holder.productType.setText(productLists.get(section).get(position).getType());

            holder.productDis.setText(productLists.get(section).get(position).getDiscription());

            holder.productSell.setText("交易数："+productLists.get(section).get(position).getSell()+"笔");

            holder.productPrice.setText("￥"+productLists.get(section).get(position).getPrice());

            holder.productDelete.setTag(position);
            holder.productDelete.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    Log.d("ButtonTest","fuc");
                    deleteProduct(section,position);
                }
            });


            holder.productStopsell.setTag(position);
            holder.productStopsell.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    stopSellProduct(section,position);
                }
            });

        }
//		layout.setOnClickListener(new OnClickListener() {
//			@Override
//			public void onClick(View arg0) {
//				Toast.makeText(mContext, rightStr.get(section).get(position), Toast.LENGTH_SHORT).show();
//			}
//		});
        return convertView;
    }

    @Override
    public View getSectionHeaderView(int section, View convertView, ViewGroup parent) {
        LinearLayout layout = null;
        if (convertView == null) {
            LayoutInflater inflator = (LayoutInflater) parent.getContext()
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            layout = (LinearLayout) inflator.inflate(R.layout.header_item, null);
        } else {
            layout = (LinearLayout) convertView;
        }
        layout.setClickable(false);
        ((TextView) layout.findViewById(R.id.textItem)).setText(productListLeft.get(section));
        return layout;
    }


    public final class ViewHolder {
        public TextView productName ;

        public TextView productType;

        public TextView productDis;

        public TextView productSell;

        public TextView productPrice;

        public Button productDelete;

        public Button productStopsell;
    }


    public void deleteProduct(int section, int position) {


        Log.d("ButtonTest",productLists.get(section).get(position).getName());


        new AlertDialog.Builder(CenterActivity.instance)
                .setMessage("确认删除该服务？")
                .setPositiveButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .setNegativeButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .show();



    }
    public void stopSellProduct(int section, int position) {


        Log.d("ButtonTest",productLists.get(section).get(position).getName());
        new AlertDialog.Builder(CenterActivity.instance)
                .setMessage("确认暂停该服务？")
                .setPositiveButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .setNegativeButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .show();

    }
}