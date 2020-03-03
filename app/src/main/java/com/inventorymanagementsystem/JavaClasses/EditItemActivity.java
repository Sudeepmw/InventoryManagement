package com.inventorymanagementsystem.JavaClasses;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class EditItemActivity extends AppCompatActivity {
    EditText et_item_name,et_price,et_quantity;
    Button btn_submit;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);

        getSupportActionBar().setTitle("Edit Item");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        et_item_name=(EditText)findViewById(R.id.et_item_name);
        et_item_name.setText(getIntent().getStringExtra("item_name"));


        et_price=(EditText)findViewById(R.id.et_price);
        btn_submit=(Button)findViewById(R.id.btn_submit);
        et_price.setText(getIntent().getStringExtra("item_price"));

        et_quantity=(EditText)findViewById(R.id.et_quantity);
        et_quantity.setText(getIntent().getStringExtra("quantity"));

        //Toast.makeText(getApplicationContext(),getIntent().getStringExtra("item_id"),Toast.LENGTH_SHORT).show();


        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                serverData();
            }
        });
    }
    ProgressDialog progressDialog;
    public void serverData(){
        progressDialog = new ProgressDialog(EditItemActivity.this);
        progressDialog.setMessage("Loading....");
        progressDialog.show();

        InventoryEndURL service = RetrofitInstance.getRetrofitInstance().create(InventoryEndURL.class);
        Call<ResponseData> call = service.edit_items(et_item_name.getText().toString(),et_price.getText().toString(),et_quantity.getText().toString(),getIntent().getStringExtra("item_id"));
        call.enqueue(new Callback<ResponseData>() {
            @Override
            public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {
                progressDialog.dismiss();
                startActivity(new Intent(getApplicationContext(),UpdateItemsActivity.class));
                finish();
                Toast.makeText(EditItemActivity.this,"Item is updated successfully.",Toast.LENGTH_SHORT).show();


            }

            @Override
            public void onFailure(Call<ResponseData> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(EditItemActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);//Menu Resource, Menu
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;

            case R.id.idlogout:
                // Toast.makeText(getApplicationContext(),"logout Selected",Toast.LENGTH_LONG).show();
                Intent i=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);
                finish();

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}
