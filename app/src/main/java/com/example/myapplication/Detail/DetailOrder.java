package com.example.myapplication.Detail;

import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.BaseActivity;
import com.example.myapplication.DataLogin;
import com.example.myapplication.InformationOrder;
import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.SendDataToServer.ApiClient;
import com.example.myapplication.SendDataToServer.ApiInterface;
import com.example.myapplication.SendDataToServer.Payment;
import com.example.myapplication.SystemActivity;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.myapplication.Constans.BaseUrlSeller;
import static com.example.myapplication.Constans.SEND_DATA;

public class DetailOrder extends BaseActivity {

    @BindView(R.id.ma_order)
    TextView maOrder;

    @BindView(R.id.ten_khach_hang)
    TextView tenKhachHang;

    @BindView(R.id.sdt)
    TextView sdt;

    @BindView(R.id.ma_product)
    TextView maProduct;

    @BindView(R.id.ten_product)
    TextView tenProduct;

    @BindView(R.id.don_gia)
    TextView donGia;

    @BindView(R.id.so_luong)
    TextView soLuong;

    @BindView(R.id.gia)
    TextView gia;

    @BindView(R.id.thu_tien)
    Button thuTien;

    private int orderid,amount;
    private String username;
    @Override
    protected int getLayoutRes() {
        return R.layout.detail_order;
    }

    @Override
    protected void setupListener() {

        ThuTien();
    }

    @Override
    protected void populateData() {
        HideTitle();
        SetData();
    }

    private void SetData() {

        Intent intent = getIntent();
        InformationOrder informationOrder = (InformationOrder) intent.getSerializableExtra(SEND_DATA);

        maOrder.setText("- Mã đơn hàng: " + informationOrder.getOrderId());
        tenKhachHang.setText("- Tên khách hàng: " + informationOrder.getName());
        sdt.setText("- Số điện thoại: " + informationOrder.getSdt());
        maProduct.setText("- Mã sản phẩm: " + informationOrder.getProductId());
        tenProduct.setText("- Tên sản phẩm: " + informationOrder.getNameProduct());
        donGia.setText("- Đơn giá: " + informationOrder.getPriceProduct() + " VNĐ");
        soLuong.setText("- Số lượng: " + informationOrder.getQuantity());
        gia.setText("- Thành tiền: " + informationOrder.getPay() + " VNĐ");

        orderid = informationOrder.getOrderId();
        amount = informationOrder.getPay();

        SharedPreferences preferences = getSharedPreferences("data_seller",MODE_PRIVATE);
        username = preferences.getString("username","");
    }

    private void ThuTien()
    {
        thuTien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkPayment();
            }
        });
    }

    private void checkPayment() {

        ApiInterface apiInterface = ApiClient.getApiClient(BaseUrlSeller).create(ApiInterface.class);
        Call<Payment> call = apiInterface.pay( orderid,username,amount);

        call.enqueue(new Callback<Payment>() {
            @Override
            public void onResponse(Call<Payment> call, Response<Payment> response) {

                Payment payment = response.body();
                Log.d("Server Response",""+payment.getResponse());


            }

            @Override
            public void onFailure(Call<Payment> call, Throwable t) {
                Log.d("Server Response",""+t.toString());
            }
        });
    }
}
