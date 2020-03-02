package com.inventorymanagementsystem.JavaClasses;

import android.app.ProgressDialog;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.inventorymanagementsystem.InventoryEndURL;
import com.inventorymanagementsystem.R;
import com.inventorymanagementsystem.ResponseData;
import com.inventorymanagementsystem.RetrofitInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewOrdersStatusAdmin extends AppCompatActivity {
    TextView tv_order_id,tv_item_name,tv_quantity,tv_price,tv_restaurent_name,tv_order_date,tv_order_created_by;
    Button btn_deliver,btn_not_deliver;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_orders_description);

        getSupportActionBar().setTitle("View Orders");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tv_order_id=(TextView)findViewById(R.id.tv_order_id);
         tv_item_name=(TextView)findViewById(R.id.tv_item_name);
        tv_quantity=(TextView)findViewById(R.id.tv_quantity);
        tv_price=(TextView)findViewById(R.id.tv_price);
        tv_restaurent_name=(TextView)findViewById(R.id.tv_restaurent_name);
        tv_order_date=(TextView)findViewById(R.id.tv_order_date);
        tv_order_created_by=(TextView)findViewById(R.id.tv_order_created_by);

        btn_deliver=(Button)findViewById(R.id.btn_deliver);
        btn_not_deliver=(Button)findViewById(R.id.btn_not_deliver);

        Typeface fontstyle=Typeface.createFromAsset(ViewOrdersStatusAdmin.this.getAssets(),"fonts/Lato-Medium.ttf");
        btn_not_deliver.setTypeface(fontstyle);
        btn_deliver.setTypeface(fontstyle);
        tv_item_name.setTypeface(fontstyle);
        tv_price.setTypeface(fontstyle);
        tv_order_date.setTypeface(fontstyle);
        tv_restaurent_name.setTypeface(fontstyle);
        tv_order_created_by.setTypeface(fontstyle);

        Typeface orderid=Typeface.createFromAsset(ViewOrdersStatusAdmin.this.getAssets(),"fonts/Roboto-Bold.ttf");
        tv_order_id.setTypeface(fontstyle);

        tv_order_id.setText("Order ID  :"+getIntent().getStringExtra("OrderID"));
        tv_item_name.setText("Item Name  :"+getIntent().getStringExtra("IteamName"));
        tv_quantity.setText("Quantity  :"+getIntent().getStringExtra("Quantity"));
        tv_price.setText("Price  :"+"$"+getIntent().getStringExtra("Price"));
        tv_order_date.setText("Price  :"+getIntent().getStringExtra("OrderDate"));
        tv_restaurent_name.setText("Restaurent Name  :"+getIntent().getStringExtra("RestaurentName"));
        tv_order_created_by.setText("Ordered By  :"+getIntent().getStringExtra("CreatedBy"));



        btn_deliver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitData(getIntent().getStringExtra("OrderID"),"Delivered");
                Toast.makeText(ViewOrdersStatusAdmin.this,"Delivered status is updated successfully.",Toast.LENGTH_LONG).show();
                //((ViewOrdersActivity)cnt).finish();
                ViewOrdersStatusAdmin.this.finish();
            }
        });


        btn_not_deliver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitData(getIntent().getStringExtra("OrderID"),"Out of Stock");
                Toast.makeText(ViewOrdersStatusAdmin.this,"Out Of Stock status is updated successfully.",Toast.LENGTH_LONG).show();
                //((ViewOrdersActivity)cnt).finish();
                ViewOrdersStatusAdmin.this.finish();
            }
        });
    }
    ProgressDialog progressDialog;
    private void submitData(String id,String status) {
        progressDialog = new ProgressDialog(ViewOrdersStatusAdmin.this);
        progressDialog.setMessage("Loading....");
        progressDialog.show();

        InventoryEndURL service = RetrofitInstance.getRetrofitInstance().create(InventoryEndURL.class);
        Call<ResponseData> call = service.updateOrderStatus(id,status);
        call.enqueue(new Callback<ResponseData>() {
            @Override
            public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {
                if (response.body().status.equals("true")) {
                    progressDialog.dismiss();
                }
                else {
                    Toast.makeText(ViewOrdersStatusAdmin.this, response.body().message, Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<ResponseData> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(ViewOrdersStatusAdmin.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
    @Override
    //add this method in your program
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}
