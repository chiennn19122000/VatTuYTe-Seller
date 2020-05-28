package com.example.myapplication.Detail;

import android.content.Intent;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.BaseActivity;
import com.example.myapplication.Product.Product;
import com.example.myapplication.R;
import com.squareup.picasso.Picasso;

import butterknife.BindView;

import static com.example.myapplication.Constans.BaseUrlUpload;
import static com.example.myapplication.Constans.SEND_DATA;

public class DetailProduct extends BaseActivity {

    @BindView(R.id.show_product)
    ImageView image;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.price)
    TextView price;
    @BindView(R.id.info)
    TextView info;






    @Override
    protected int getLayoutRes() {
        return R.layout.detail_product;
    }

    @Override
    protected void setupListener() {

    }

    @Override
    protected void populateData() {
        HideTitle();
        SetData();
    }

    private void SetData() {
        Intent intent = getIntent();
        Product product = (Product) intent.getSerializableExtra(SEND_DATA);
        Picasso.with(DetailProduct.this).load(BaseUrlUpload+product.getImage()).into(image);

        name.setText(product.getName());
        price.setText(product.getPrice() + " VNĐ");
        info.setText(product.getInformation());
    }






}