package com.center;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.avos.avoscloud.AVCloudQueryResult;
import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.CloudQueryCallback;
import com.center.shop.AddProductActivity;
import com.center.shop.Product;
import com.center.shop.ProductAdapter;
import com.center.shop.ProductAdapter3;
import com.center.shop.linkedlistview.PinnedHeaderListView;
import com.center.shop.linkedlistview.SectionedBaseAdapter;
import com.example.apple.shopowner.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ShopFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ShopFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ShopFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public ShopFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ShopFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ShopFragment newInstance(String param1, String param2) {
        ShopFragment fragment = new ShopFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }






    private Button addProduct;
    String id;
    View view;

    private List<Product> productList=new ArrayList<Product>();
    private List<String> productListLeft=new ArrayList<String>();
    private List<ArrayList<Product>> productLists=new ArrayList<ArrayList<Product>>();
    private boolean[] productFlag;

    //private String[] productListLeft;
    void initProducts(){


        //Log.d("initProducts","ID"+id);

        String cql = "select * from Product where idnumber = '"+id+"'";
        AVQuery.doCloudQueryInBackground(cql, new CloudQueryCallback<AVCloudQueryResult>() {
            @Override
            public void done(AVCloudQueryResult avCloudQueryResult, AVException e) {

                List<AVObject> results= (List<AVObject>) avCloudQueryResult.getResults();
                Log.d("initProducts","SIZE:"+results.size());

                productList.clear();

                productLists.clear();

                for(int i=0;i<results.size();i++){
                    AVObject result=results.get(i);

                    productList.add(new Product(result));

                    Product midProduct=new Product(result);

                    if(midProduct.getType2()!=null){
                        Log.d("midProduct",midProduct.getType2());

                        if(!productListLeft.contains(midProduct.getType2())){
                            productListLeft.add(midProduct.getType2());
                        }

                        if(productLists.size()<productListLeft.size()){

                            productLists.add(new ArrayList<Product>());

                        }
                        productLists.get(productListLeft.indexOf(midProduct.getType2())).add(midProduct);


                    }
                    else{
                        if(!productListLeft.contains("其他")){
                            productListLeft.add("其他");
                        }
                        if(productLists.size()<productListLeft.size()){

                            productLists.add(new ArrayList<Product>());

                        }
                        productLists.get(productListLeft.indexOf("其他")).add(midProduct);

                    }

                }
                Log.d("midProductSize",Integer.toString(productListLeft.size()));


                productFlag=new boolean[productListLeft.size()];
                productFlag[0]=true;
                for(int flag=1;flag<productFlag.length;flag++){
                    productFlag[flag]=false;
                }

            }
        });



        /*
        Product p0=new Product("123");
        Product p2=new Product("456");
        Product p3=new Product("789");
        productList.add(p0);
        productList.add(p2);
        productList.add(p3);
        */


    }

    private boolean isScroll = true;
    private MyAdapter adapter;
    private ListView left_listView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_shop, container, false);

        Intent intent2=getActivity().getIntent();
        id=intent2.getStringExtra("id");
        //Log.d("fuc",id);



        addProduct=(Button)view.findViewById(R.id.shop_addproduct_button);
        addProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),AddProductActivity.class);
                intent.putExtra("id",id);
                startActivity(intent);
            }
        });


        initProducts();


/*
        ProductAdapter adapter=new ProductAdapter(getActivity(),R.layout.product_item,productList);
        ListView listView=(ListView)view.findViewById(R.id.shop_products);
        listView.setAdapter(adapter);
*/

        /*
        ProductAdapter2 adapter=new ProductAdapter2(getActivity());
        ListView listView=(ListView)view.findViewById(R.id.shop_products);
        listView.setAdapter(adapter);
        */



        final PinnedHeaderListView right_listview = (PinnedHeaderListView) view.findViewById(R.id.shop_products);
        final ProductAdapter3 sectionedAdapter = new ProductAdapter3(getActivity(), productListLeft, productLists);
        right_listview.setAdapter(sectionedAdapter);

        left_listView = (ListView) view.findViewById(R.id.shop_left_products);
        adapter = new MyAdapter();
        left_listView.setAdapter(adapter);
        left_listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View view, int position, long arg3) {
                isScroll = false;


                for (int i = 0; i < productListLeft.size(); i++) {

                    if (i == position) {
                        productFlag[i] = true;
                    } else {
                        productFlag[i] = false;
                    }

                }

                adapter.notifyDataSetChanged();
                int rightSection = 0;
                for (int i = 0; i < position; i++) {
                    rightSection += sectionedAdapter.getCountForSection(i) + 1;
                }
                right_listview.setSelection(rightSection);

            }

        });

        right_listview.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView arg0, int scrollState) {
                switch (scrollState) {
                    // 当不滚动时
                    case AbsListView.OnScrollListener.SCROLL_STATE_IDLE:
                        // 判断滚动到底部
                        if (right_listview.getLastVisiblePosition() == (right_listview.getCount() - 1)) {
                            left_listView.setSelection(ListView.FOCUS_DOWN);
                        }

                        // 判断滚动到顶部
                        if (right_listview.getFirstVisiblePosition() == 0) {
                        }

                        break;
                }
            }

            int y = 0;
            int x = 0;
            int z = 0;

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if (isScroll) {

                    for (int i = 0; i < productLists.size(); i++) {

                        if (i == sectionedAdapter.getSectionForPosition(right_listview.getFirstVisiblePosition())) {
                            productFlag[i] = true;
                            x = i;
                        } else {
                            productFlag[i] = false;
                        }
                    }

                    if (x != y) {
                        adapter.notifyDataSetChanged();
                        y = x;
                        if (y == left_listView.getLastVisiblePosition()) {
                            z = z + 3;
                            left_listView.setSelection(z);
                        }
                        if (x == left_listView.getFirstVisiblePosition()) {
                            z = z - 1;
                            left_listView.setSelection(z);
                        }
                        if (firstVisibleItem + visibleItemCount == totalItemCount - 1) {
                            left_listView.setSelection(ListView.FOCUS_DOWN);
                        }
                    }
                } else {
                    isScroll = true;
                }
            }
        });



        /*
        String[] array=(String[]) productListLeft.toArray(new String[productListLeft.size()]);
        //String[] array={"1","2","3","4","5"};
        ArrayAdapter<String> adapterLeft = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, array);
        ListView listViewLeft = (ListView) view.findViewById(R.id.shop_left_products);
        listViewLeft.setAdapter(adapterLeft);

        */
        return view;
    }

    public class ProductAdapter2 extends BaseAdapter {

        private LayoutInflater mInflater;

        public ProductAdapter2(Context context) {
            this.mInflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return productList.size();
        }

        @Override
        public Object getItem(int position) {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public long getItemId(int position) {
            // TODO Auto-generated method stub
            return 0;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {

            ViewHolder holder = null;
            if (convertView == null) {

                holder = new ViewHolder();

                convertView = mInflater.inflate(R.layout.product_item, null);

                if(view==null){

                    Log.d("view is null","view is null");
                }

                holder.productName = (TextView) convertView.findViewById(R.id.product_name);

                holder.productType=(TextView)convertView.findViewById(R.id.product_type);

                holder.productDis=(TextView)convertView.findViewById(R.id.product_dis);

                holder.productSell=(TextView)convertView.findViewById(R.id.product_sell);

                holder.productPrice=(TextView)convertView.findViewById(R.id.product_price);

                holder.productDelete=(Button)convertView.findViewById(R.id.product_delete);

                holder.productStopsell=(Button)convertView.findViewById(R.id.product_stopsell);


                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }


            holder.productName.setText(productList.get(position).getName()+"("+productList.get(position).getPid()+")");

            holder.productType.setText(productList.get(position).getType());

            holder.productDis.setText(productList.get(position).getDiscription());

            holder.productSell.setText("交易数："+productList.get(position).getSell()+"笔");

            holder.productPrice.setText("￥"+productList.get(position).getPrice());


            holder.productDelete.setTag(position);


            holder.productDelete.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    Log.d("ButtonTest","fuc");
                    deleteProduct(position);
                }
            });


            holder.productStopsell.setTag(position);
            holder.productStopsell.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    stopSellProduct(position);
                }
            });

            //holder.viewBtn.setOnClickListener(MyListener(position));

            return convertView;
        }
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

    public void deleteProduct(int position) {


        Log.d("ButtonTest",productList.get(position).getName());


        new AlertDialog.Builder(getActivity())
                .setMessage("confirm delete!")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .show();



    }
    public void stopSellProduct(int position) {


        Log.d("ButtonTest",productList.get(position).getName());
        /*
        ImageView img = new ImageView(getActivity());
        img.setImageResource(R.drawable.b);
        new AlertDialog.Builder(this).setView(img)
                .setTitle("����" + position)
                .setMessage("����" + title[position] + "   �۸�:" + info[position])
                .setPositiveButton("ȷ��", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .show();

        */

    }



    public class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return productListLeft.size();
        }

        @Override
        public Object getItem(int arg0) {
            return productListLeft.get(arg0);
        }

        @Override
        public long getItemId(int arg0) {
            return arg0;
        }

        @Override
        public View getView(int arg0, View arg1, ViewGroup arg2) {
            Holder holder = null;
            if (arg1 == null) {
                holder = new Holder();
                arg1 = LayoutInflater.from(getActivity()).inflate(R.layout.left_list_item, null);
                holder.left_list_item = (TextView) arg1.findViewById(R.id.left_list_item);
                arg1.setTag(holder);
            } else {
                holder = (Holder) arg1.getTag();
            }
            holder.updataView(arg0);
            return arg1;
        }

        private class Holder {
            private TextView left_list_item;

            public void updataView(final int position) {
                left_list_item.setText(productListLeft.get(position));


                if (productFlag[position]) {
                    left_list_item.setBackgroundColor(Color.rgb(100, 100, 100));
                } else {
                    left_list_item.setBackgroundColor(Color.TRANSPARENT);
                }

            }

        }
    }

















    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
