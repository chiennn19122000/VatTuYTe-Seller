package com.example.myapplication.Home;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.example.myapplication.ChangeInfoActivity;
import com.example.myapplication.ChangePassActivity;
import com.example.myapplication.GetData.APIService;
import com.example.myapplication.InfoAdmin;
import com.example.myapplication.MainActivity;
import com.example.myapplication.Product.Product;
import com.example.myapplication.R;
import com.example.myapplication.Seller.InfoSeller;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.content.ContentValues.TAG;
import static android.content.Context.MODE_PRIVATE;
import static com.example.myapplication.Constans.BaseUrlGet;

public class PersonalFragment extends Fragment {
     TextView name,sdt,email,place,namecompany,sdtcompany,emailcompany;
    Button changeinfo,changepass,logout,exit;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_personal,container,false);
        name = (TextView) view.findViewById(R.id.name_seller);
        sdt = (TextView) view.findViewById(R.id.sdt_seller);
        email = (TextView) view.findViewById(R.id.email_seller);
        place = (TextView) view.findViewById(R.id.place_seller);
        namecompany = (TextView) view.findViewById(R.id.company_name);
        sdtcompany = (TextView) view.findViewById(R.id.company_number_phone);
        emailcompany = (TextView) view.findViewById(R.id.company_email);
        changeinfo = (Button) view.findViewById(R.id.change_info);
        changepass = (Button) view.findViewById(R.id.change_pass);
        logout = (Button) view.findViewById(R.id.logout);
        exit = (Button) view.findViewById(R.id.exit);

        setData();
        Changeinfo();
        ChangePass();
        Logout();
        Exit();
        return view;
    }



    private void ChangePass() {
        changepass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), ChangePassActivity.class));
            }
        });
    }

    private void Changeinfo() {
        changeinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), ChangeInfoActivity.class));
            }
        });
    }

    private void setData() {
        SharedPreferences preferences = getActivity().getSharedPreferences("data_seller",MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BaseUrlGet)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        APIService apiService = retrofit.create(APIService.class);
        Call<List<InfoSeller>> call = apiService.getInfoSeller();
        call.enqueue(new Callback<List<InfoSeller>>() {
            @Override
            public void onResponse(Call<List<InfoSeller>> call, Response<List<InfoSeller>> response) {
                List<InfoSeller> infoSellers = response.body();
                for (int i = 0; i<infoSellers.size() ; i++) {
                    if(infoSellers.get(i).getUsername().equals(preferences.getString("username","")))
                        editor.putString("name",infoSellers.get(i).getName());
                        editor.putString("sdt",infoSellers.get(i).getSdt());
                        editor.putString("email",infoSellers.get(i).getEmail());
                        editor.putString("place",infoSellers.get(i).getPlace());
                        editor.commit();
                        name.setText("Tên: "+infoSellers.get(i).getName());
                        sdt.setText("Số điện thoại: "+infoSellers.get(i).getSdt());
                        email.setText("Email: "+infoSellers.get(i).getEmail());
                        place.setText("Địa chỉ: "+infoSellers.get(i).getPlace());
                        break;
                }
            }

            @Override
            public void onFailure(Call<List<InfoSeller>> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
            }
        });
        setInfoAdmin();

    }

    private void setInfoAdmin() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BaseUrlGet)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        APIService apiService = retrofit.create(APIService.class);
        Call<List<InfoAdmin>> call = apiService.getInfoAdmin();
        call.enqueue(new Callback<List<InfoAdmin>>() {
            @Override
            public void onResponse(Call<List<InfoAdmin>> call, Response<List<InfoAdmin>> response) {
                List<InfoAdmin> infoSellers = response.body();
                for (int i = 0; i<infoSellers.size() ; i++) {
                    namecompany.setText("Tên công ty: "+infoSellers.get(0).getName());
                    sdtcompany.setText("Số điện thoại công ty: "+infoSellers.get(0).getSdt());
                    emailcompany.setText("Email công ty : "+infoSellers.get(0).getEmail());
                    break;
                }
            }

            @Override
            public void onFailure(Call<List<InfoAdmin>> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
            }
        });
    }

    private void Logout() {
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), MainActivity.class));
            }
        });
    }

    private void Exit() {
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Question ?");
                builder.setIcon(R.mipmap.ic_launcher);
                builder.setMessage("are you sure you want to exit");
                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        System.exit(0);
                    }
                });
                builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.setCanceledOnTouchOutside(false);
                dialog.show();
            }
        });
    }
}
