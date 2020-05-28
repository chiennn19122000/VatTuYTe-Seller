package com.example.myapplication.Home;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myapplication.Detail.DetailOrder;
import com.example.myapplication.Detail.DetailProduct;
import com.example.myapplication.GetProduct.APIService;
import com.example.myapplication.InformationOrder;
import com.example.myapplication.R;
import com.example.myapplication.Seller.Seller;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.content.ContentValues.TAG;
import static android.content.Context.MODE_PRIVATE;
import static com.example.myapplication.Constans.BaseUrlGet;
import static com.example.myapplication.Constans.SEND_DATA;

public class OrderProductFragment extends Fragment {

    String placeSeller;
    List<String> arr;
    ListView lv;
    ArrayList<InformationOrder> informationOrdersList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_order_product,container,false);

        lv = (ListView) view.findViewById(R.id.lvOrder);

        setData();
        return view;
    }

    private void setData() {
        SharedPreferences preferences = getActivity().getSharedPreferences("data_seller",MODE_PRIVATE);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BaseUrlGet)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        APIService apiService = retrofit.create(APIService.class);
        Call<List<Seller>> call = apiService.getAllSeller();
        call.enqueue(new Callback<List<Seller>>() {
            @Override
            public void onResponse(Call<List<Seller>> call, Response<List<Seller>> response) {
                List<Seller> sellerList = response.body();
                for (int i = 0; i<sellerList.size() ; i++) {
                    if (sellerList.get(i).getUsername().equals(preferences.getString("username","")))
                    {
                        placeSeller = sellerList.get(i).getPlace();

                    }
                    Log.d(TAG, "onResponse" + placeSeller);
                }
                AllOrder(placeSeller);
            }

            @Override
            public void onFailure(Call<List<Seller>> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
            }
        });




    }

    private void AllOrder(String place)
    {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BaseUrlGet)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        APIService apiService = retrofit.create(APIService.class);
        informationOrdersList = new ArrayList<>();
        arr = new ArrayList<>();
        Call<List<InformationOrder>> call = apiService.getAllOrder();
        call.enqueue(new Callback<List<InformationOrder>>() {
            @Override
            public void onResponse(Call<List<InformationOrder>> call, Response<List<InformationOrder>> response) {
                List<InformationOrder> orderList = response.body();
                for (int i = 0; i<orderList.size() ; i++) {
                    if (orderList.get(i).getPlace().equals(place))
                    {
                        informationOrdersList.add(orderList.get(i));
                        arr.add(orderList.get(i).toString());
                        Log.d(TAG, "onResponse" + orderList.get(i).toString());
                    }

                }
                ArrayAdapter adapter= new ArrayAdapter(getActivity(),android.R.layout.simple_expandable_list_item_1,arr);
                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        InformationOrder informationOrder = informationOrdersList.get(position);

                        Intent intent = new Intent();
                        intent.setClass(getActivity(), DetailOrder.class);
                        intent.putExtra(SEND_DATA,informationOrder);
                        startActivity(intent);
                    }
                });
                lv.setAdapter(adapter);
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<List<InformationOrder>> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
            }
        });


    }
}
