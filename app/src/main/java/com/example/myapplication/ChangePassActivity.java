package com.example.myapplication;

import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.Seller.InfoSeller;
import com.example.myapplication.SendDataToServer.ApiClient;
import com.example.myapplication.SendDataToServer.ApiInterface;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.myapplication.Constans.BaseUrlSeller;

public class ChangePassActivity extends BaseActivity {
    @BindView(R.id.old_pass)
    EditText oldpass;
    @BindView(R.id.new_pass1)
    EditText newpass1;
    @BindView(R.id.new_pass2)
    EditText newpass2;
    @BindView(R.id.save_pass)
    Button savepass;
    @Override
    protected int getLayoutRes() {
        return R.layout.activity_change_pass;
    }

    @Override
    protected void setupListener() {
        SavePass();
    }

    @Override
    protected void populateData() {
        callback();
        setTitle("Thay đổi mật khẩu");
    }
    private void SavePass()
    {
        SharedPreferences preferences = getSharedPreferences("data_seller",MODE_PRIVATE);
        savepass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (oldpass.getText().toString().equals(preferences.getString("pass","")))
                {
                    if (newpass1.getText().toString().equals(newpass2.getText().toString()) && TextUtils.isEmpty(newpass1.getText().toString()) == false)
                    {
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putString("pass",newpass1.getText().toString());
                        editor.commit();
                        UpDatePass(preferences.getString("username",""),preferences.getString("pass",""));
                        Toast.makeText(ChangePassActivity.this,"Thay đổi mật khẩu thành công",Toast.LENGTH_LONG).show();
                        finish();
                    }
                    else if (newpass1.getText().toString().equals(newpass2.getText().toString()) == false )
                    {
                        Toast.makeText(ChangePassActivity.this,"Mật khẩu không khớp",Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(ChangePassActivity.this,"Không được bỏ trống",Toast.LENGTH_SHORT).show();
                    }
                }
                else
                {
                    Toast.makeText(ChangePassActivity.this,"Sai mật khẩu cũ",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void UpDatePass(String user,String pass) {
        ApiInterface apiInterface = ApiClient.getApiClient(BaseUrlSeller).create(ApiInterface.class);
        Call<DataLogin> call = apiInterface.updatepass( user,pass);

        call.enqueue(new Callback<DataLogin>() {
            @Override
            public void onResponse(Call<DataLogin> call, Response<DataLogin> response) {

                DataLogin dataLogin = response.body();

                Log.d("Server Response",dataLogin.getResponse());

            }

            @Override
            public void onFailure(Call<DataLogin> call, Throwable t) {
                Log.d("Fail Response",t.getMessage());
            }
        });
    }
}