package com.inventorymanagementsystem.JavaClasses;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.inventorymanagementsystem.InventoryEndURL;
import com.inventorymanagementsystem.R;
import com.inventorymanagementsystem.ResponseData;
import com.inventorymanagementsystem.RetrofitInstance;
import com.inventorymanagementsystem.Utils;

import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderGoodsActivity extends AppCompatActivity {
    TextView tv_goods_name, tv_price, tv_date, tv_dis_date, tv_restaurent;
    EditText et_goods_name, et_price, et_quantity, et_restaurent;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    SharedPreferences sharedPreferences;
    String session;
    List<AddItems> a3;
    String date;
    ProgressDialog progressDialog;
    Button btn_submit;
    Spinner spin_iteams;
    String[] additem;
    int[] price;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_goods);

        sharedPreferences = getSharedPreferences(Utils.SHREF, Context.MODE_PRIVATE);
        session = sharedPreferences.getString("user_name", "");

        getSupportActionBar().setTitle("Order Goods");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tv_goods_name = (TextView) findViewById(R.id.tv_goods_name);
        tv_price = (TextView) findViewById(R.id.tv_price);
        tv_dis_date = (TextView) findViewById(R.id.tv_dis_date);
        tv_restaurent = (TextView) findViewById(R.id.tv_restaurent);

        //et_goods_name=(EditText)findViewById(R.id.et_goods_name);
        et_price = (EditText) findViewById(R.id.et_price);
        et_quantity = (EditText) findViewById(R.id.et_quantity);
        et_restaurent = (EditText) findViewById(R.id.et_restaurent);
        et_quantity.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                if (spin_iteams.getSelectedItemPosition() > 0) {
                    if (cs != null) {
                        if (cs.length() > 0) {
                            int tot = item_price * Integer.parseInt(cs + "");
                            et_price.setText("" + tot);
                        } else {
                            et_price.setText("0");
                        }
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Please select item.", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
                //Toast.makeText(getApplicationContext(),"before text change",Toast.LENGTH_LONG).show();
            }

            @Override
            public void afterTextChanged(Editable arg0) {
                //Toast.makeText(getApplicationContext(),"after text change",Toast.LENGTH_LONG).show();
            }
        });
        tv_date = (TextView) findViewById(R.id.tv_date);
        spin_iteams = (Spinner) findViewById(R.id.spin_iteams);


        Typeface fontstyle = Typeface.createFromAsset(getApplication().getAssets(), "fonts/Lato-Medium.ttf");
        tv_goods_name.setTypeface(fontstyle);
        tv_price.setTypeface(fontstyle);
        tv_dis_date.setTypeface(fontstyle);
        et_price.setTypeface(fontstyle);
        tv_date.setTypeface(fontstyle);
        tv_restaurent.setTypeface(fontstyle);


        tv_date = (TextView) findViewById(R.id.tv_date);
        tv_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timedatepicker();

            }
        });
        btn_submit = (Button) findViewById(R.id.btn_submit);
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //submitData();
                String price = et_price.getText().toString();
                String date = tv_date.getText().toString();
                String quantity = et_quantity.getText().toString();
                String goodname = spin_iteams.getSelectedItem().toString();
                String rest_name = et_restaurent.getText().toString();
                Intent intent = new Intent(getApplicationContext(), PaymentActivity.class);
                intent.putExtra("goodname", goodname);
                intent.putExtra("price", price);
                intent.putExtra("date", date);
                intent.putExtra("session", session);
                intent.putExtra("quantity", quantity);
                intent.putExtra("restaurant_name", rest_name);
                //restaurant_name
                startActivity(intent);

            }
        });
        addItems();

    }

    private void submitData() {
        //String goodname = et_goods_name.getText().toString();
        String price = et_price.getText().toString();
        String date = tv_date.getText().toString();
        String quantity = et_quantity.getText().toString();
        String goodname = spin_iteams.getSelectedItem().toString();
        String rest_name = et_restaurent.getText().toString();

        progressDialog = new ProgressDialog(OrderGoodsActivity.this);
        progressDialog.setMessage("Loading....");
        progressDialog.show();

        InventoryEndURL service = RetrofitInstance.getRetrofitInstance().create(InventoryEndURL.class);
        Call<ResponseData> call = service.add_orders(goodname, price, date, session, quantity, rest_name);
        call.enqueue(new Callback<ResponseData>() {
            @Override
            public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {
                if (response.body().status.equals("true")) {
                    progressDialog.dismiss();
                    Toast.makeText(OrderGoodsActivity.this, response.body().message, Toast.LENGTH_LONG).show();
                    finish();

                } else {
                    Toast.makeText(OrderGoodsActivity.this, response.body().message, Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseData> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(OrderGoodsActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    public void timedatepicker() {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                Log.d(String.valueOf(OrderGoodsActivity.this), "onDateSet: mm/dd/yyy: " + month + "/" + day + "/" + year);
                date = month + "/" + day + "/" + year;
                tv_date.setText(date);


            }
        };
        DatePickerDialog dialog = new DatePickerDialog(
                OrderGoodsActivity.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth, mDateSetListener, year, month, day);

        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();

    }

    int item_price = 0;

    private void addItems() {
        InventoryEndURL apiService = RetrofitInstance.getRetrofitInstance().create(InventoryEndURL.class);
        Call<List<AddItems>> call = apiService.getiteams();
        call.enqueue(new Callback<List<AddItems>>() {
            @Override
            public void onResponse(Call<List<AddItems>> call, Response<List<AddItems>> response) {
                a3 = response.body();
                additem = new String[a3.size() + 1];
                price = new int[a3.size() + 1];
                additem[0] = "Select Item";
                price[0] = 0;
                for (int i = 0; i < a3.size(); i++) {
                    additem[i + 1] = a3.get(i).getItem_name();
                    price[i + 1] = Integer.parseInt(a3.get(i).getPrice());
                }
                ArrayAdapter aa = new ArrayAdapter(OrderGoodsActivity.this, android.R.layout.simple_spinner_item, additem);
                aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spin_iteams.setAdapter(aa);
                spin_iteams.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long l) {
                        if (pos > 0) {
                            item_price = price[pos];
                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });
            }

            @Override
            public void onFailure(Call<List<AddItems>> call, Throwable t) {
                Log.d("TAG", "Response = " + t.toString());
            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu) {
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
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
                finish();

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}