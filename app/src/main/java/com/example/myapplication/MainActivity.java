package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.SendDataToServer.ApiClient;
import com.example.myapplication.SendDataToServer.ApiInterface;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.myapplication.Constans.BaseUrlSeller;

public class MainActivity extends BaseActivity{
    @BindView(R.id.userSeller)
    EditText user;

    @BindView(R.id.passSeller)
    EditText pass;

    @BindView(R.id.login)
    Button login;
    @Override
    protected int getLayoutRes() {
        return R.layout.activity_main;
    }

    @Override
    protected void setupListener() {

        Login();
    }

    @Override
    protected void populateData() {
        HideTitle();
    }

    private void Login()
    {
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TheLogin();
            }
        });
    }

    private void TheLogin()
    {
        String username = user.getText().toString();
        String password = pass.getText().toString();

        ApiInterface apiInterface = ApiClient.getApiClient(BaseUrlSeller).create(ApiInterface.class);
        Call<DataLogin> call = apiInterface.loginacc( username,password);

        call.enqueue(new Callback<DataLogin>() {
            @Override
            public void onResponse(Call<DataLogin> call, Response<DataLogin> response) {

                DataLogin dataLogin = response.body();
                Log.d("Server Response",""+dataLogin.getResponse());
                if(dataLogin.getResponse().equals("Successfully"))
                {
                    SharedPreferences preferences = getSharedPreferences("data_seller",MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("username",username);
                    editor.putString("pass",password);
                    editor.commit();
                    user.setText("");
                    pass.setText("");
                    startActivity(new Intent(MainActivity.this,SystemActivity.class));

                }
                else {
                    Toast.makeText(MainActivity.this,"Sai tài khoản hoặc mật khẩu",Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<DataLogin> call, Throwable t) {
                Log.d("Server Response",""+t.toString());

            }
        });
    }

}