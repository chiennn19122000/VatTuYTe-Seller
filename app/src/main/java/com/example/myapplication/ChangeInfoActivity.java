package com.example.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.Detail.DetailOrder;
import com.example.myapplication.Seller.InfoSeller;
import com.example.myapplication.SendDataToServer.ApiClient;
import com.example.myapplication.SendDataToServer.ApiInterface;
import com.example.myapplication.SendDataToServer.Payment;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.myapplication.Constans.BaseUrlSeller;

public class ChangeInfoActivity extends BaseActivity {
    @BindView(R.id.change_name)
    EditText changename;
    @BindView(R.id.change_sdt)
    EditText changesdt;
    @BindView(R.id.change_email)
    EditText changemail;


    @BindView(R.id.save)
    Button save;

    String username,name,sdt,email;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_change_info;
    }

    @Override
    protected void setupListener() {
        Save();
    }

    @Override
    protected void populateData() {
        callback();
        setTitle("Thay đổi thông tin");
    }
    private void Save()
    {
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendData();
                Toast.makeText(ChangeInfoActivity.this,"Thay đổi thành công",Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

    private void sendData() {
        SharedPreferences preferences = getSharedPreferences("data_seller",MODE_PRIVATE);
        username = preferences.getString("username","");
        if (TextUtils.isEmpty(changename.getText().toString()))
        {
            name = preferences.getString("name","");
        }
        else
        {
            name = changename.getText().toString();
        }
        if (TextUtils.isEmpty(changesdt.getText().toString()))
        {
            sdt = preferences.getString("sdt","");
        }
        else
        {
            sdt =changesdt.getText().toString();
        }

        if (TextUtils.isEmpty(changemail.getText().toString()))
        {
            email = preferences.getString("email","");
        }
        else
        {
            email = changemail.getText().toString();
        }


        ApiInterface apiInterface = ApiClient.getApiClient(BaseUrlSeller).create(ApiInterface.class);
        Call<InfoSeller> call = apiInterface.updateinfo( username,name,sdt,email);

        call.enqueue(new Callback<InfoSeller>() {
            @Override
            public void onResponse(Call<InfoSeller> call, Response<InfoSeller> response) {

                InfoSeller infoSeller = response.body();

                Log.d("Server Response",infoSeller.getResponse());


            }

            @Override
            public void onFailure(Call<InfoSeller> call, Throwable t) {
                Log.d("Fail Response",""+username+name+sdt+email);
            }
        });
    }
}
