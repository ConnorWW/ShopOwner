package com.center;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.avos.avoscloud.AVCloudQueryResult;
import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.CloudQueryCallback;
import com.center.shop.AddProductActivity;
import com.center.shop.Product;
import com.center.shop.ProductAdapter;
import com.example.apple.shopowner.R;

import java.util.ArrayList;
import java.util.List;

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

                for(int i=0;i<results.size();i++){
                    AVObject result=results.get(i);
                    productList.add(new Product(result));

                    Product midProduct=new Product(result);
                    if(midProduct.getType2()!=null){
                        Log.d("midProduct",midProduct.getType2());
                        if(!productListLeft.contains(midProduct.getType2())){
                            productListLeft.add(midProduct.getType2());
                        }
                    }

                }
                Log.d("midProductSize",Integer.toString(productListLeft.size()));



            }
        });

        /*
        Product p0=new Product("asdasdfdsfasdfdasf");
        Product p2=new Product("asdfadsfsdafasdf");
        Product p3=new Product("asdfasdfsadfasfsdf");
        productList.add(p0);
        productList.add(p2);
        productList.add(p3);
        */

    }

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


        ProductAdapter adapter=new ProductAdapter(getActivity(),R.layout.product_item,productList);
        ListView listView=(ListView)view.findViewById(R.id.shop_products);
        listView.setAdapter(adapter);




        String[] array=(String[]) productListLeft.toArray(new String[productListLeft.size()]);
        //String[] array={"1","2","3","4","5"};
        ArrayAdapter<String> adapterLeft = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, array);
        ListView listViewLeft = (ListView) view.findViewById(R.id.shop_left_products);
        listViewLeft.setAdapter(adapterLeft);

        return view;
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
