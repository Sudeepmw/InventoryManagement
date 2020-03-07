package com.inventorymanagementsystem.JavaClasses;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.inventorymanagementsystem.InventoryEndURL;
import com.inventorymanagementsystem.R;
import com.inventorymanagementsystem.ResponseData;
import com.inventorymanagementsystem.RetrofitInstance;

import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PaymentActivity extends AppCompatActivity {
    TextView tvItem,tvQuantity,tvPrice,tvDate,tv_restaurent_name;
    Button btn_submit;
    TextView tv_dob;
    String date;
    EditText et_restaurent;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        getSupportActionBar().setTitle("Payment");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        et_restaurent=(EditText)findViewById(R.id.et_restaurent);
        tv_dob=(TextView)findViewById(R.id.tv_dob);
        tv_dob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timedatepicker();
            }
        });

        tvItem=(TextView)findViewById(R.id.tvItem);
        tvItem.setText("Item : "+getIntent().getStringExtra("item_name"));
        tvPrice=(TextView)findViewById(R.id.tvPrice);
        tvPrice.setText("Total Price : "+"$"+getIntent().getStringExtra("price"));



        btn_submit=(Button)findViewById(R.id.btn_submit);
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (tv_dob.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please Select Order Date", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (et_restaurent.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please Enter Restaurant Name",Toast.LENGTH_SHORT).show();
                    return;
                }



                final AlertDialog.Builder builder = new AlertDialog.Builder(PaymentActivity.this);
                builder.setMessage("Your Cart :"+" "+getIntent().getStringExtra("item_name")+"Total: $"+getIntent().getStringExtra("price"));

                builder.setCancelable(true);
                builder.setNegativeButton("Place order!", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        submitData();
                        Intent i=new Intent(getApplicationContext(),UserDashBoardActivity.class);
                        startActivity(i);
                        finish();
                    }
                });
                builder.setPositiveButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();

                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();



            }
        });
    }
    ProgressDialog progressDialog;
    private void submitData() {


        progressDialog = new ProgressDialog(PaymentActivity.this);
        progressDialog.setMessage("Placing order....");
        progressDialog.show();

        InventoryEndURL service = RetrofitInstance.getRetrofitInstance().create(InventoryEndURL.class);
        Call<ResponseData> call = service.add_orders1(getIntent().getStringExtra("item_name"),getIntent().getStringExtra("price"),tv_dob.getText().toString(),getIntent().getStringExtra("session"),"0",et_restaurent.getText().toString(),getIntent().getStringExtra("update_str"));
        call.enqueue(new Callback<ResponseData>() {
            @Override
            public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {
                if (response.body().status.equals("true")) {
                    progressDialog.dismiss();
                    Toast.makeText(PaymentActivity.this, response.body().message, Toast.LENGTH_LONG).show();
                    Intent intent=new Intent(PaymentActivity.this,OrderConfirmationSplash.class);
                    startActivity(intent);
                    //finish();

                } else {
                    Toast.makeText(PaymentActivity.this, response.body().message, Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseData> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(PaymentActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    public void timedatepicker() {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        Log.d(String.valueOf(PaymentActivity.this), "onDateSet: mm/dd/yyy: " + month + "/" + day + "/" + year);
        month = month +1;
        date = day + "/" + month + "/" + year;
        tv_dob.setText(date);



    }
    @Override
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
