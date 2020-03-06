package com.inventorymanagementsystem.JavaClasses;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.inventorymanagementsystem.InventoryEndURL;
import com.inventorymanagementsystem.InventoryEndURL;
import com.inventorymanagementsystem.JavaClasses.EditProfile;
import com.inventorymanagementsystem.R;
import com.inventorymanagementsystem.ResponseData;
import com.inventorymanagementsystem.RetrofitInstance;
import com.inventorymanagementsystem.Utils;


import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserEditProfileActivity extends AppCompatActivity {
    EditText et_name, et_phno, et_uname, et_password,et_email,et_pincode,et_locality,et_shippingadd,et_getres,et_cpassword;
    TextView tv1,tv2,tv4,tv5,tv3,tv8,tv7,tv6,tv9;
    Button btn_reg;
    List<EditProfile> a1;
    ProgressDialog progressDialog;
    SharedPreferences sharedPreferences;
    String session;
    ResponseData a2;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_edit_profile);


        getSupportActionBar().setTitle("Edit Profile");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        sharedPreferences = getSharedPreferences(Utils.SHREF, Context.MODE_PRIVATE);

        session = sharedPreferences.getString("user_name", "def-val");

        tv1=(TextView)findViewById(R.id.tv1);
        tv2=(TextView)findViewById(R.id.tv2);
        tv4=(TextView)findViewById(R.id.tv4);
        tv5=(TextView)findViewById(R.id.tv5);
        tv3=(TextView)findViewById(R.id.tv3);
        tv6=(TextView)findViewById(R.id.tv6);
        tv7=(TextView)findViewById(R.id.tv7);
        tv8=(TextView)findViewById(R.id.tv8);
        tv9=(TextView)findViewById(R.id.tv9);

        btn_reg = (Button) findViewById(R.id.btn_reg);
        et_name = (EditText) findViewById(R.id.et_name);
        et_phno = (EditText) findViewById(R.id.et_phno);
        et_uname = (EditText) findViewById(R.id.et_uname);
        et_password = (EditText) findViewById(R.id.et_password);
        et_cpassword=(EditText) findViewById(R.id.et_cpassword);
        et_pincode = (EditText) findViewById(R.id.et_pincode);
        et_locality = (EditText) findViewById(R.id.et_locality);
        et_shippingadd = (EditText) findViewById(R.id.et_shippingadd);
        et_getres=(EditText)findViewById(R.id.et_getres);


        et_email = (EditText) findViewById(R.id.et_email);
        et_uname.setText(session);
        Typeface fontstyle=Typeface.createFromAsset(getApplicationContext().getAssets(),"fonts/Lato-Medium.ttf");
        tv1.setTypeface(fontstyle);
        tv2.setTypeface(fontstyle);
        tv4.setTypeface(fontstyle);
        tv5.setTypeface(fontstyle);
        btn_reg.setTypeface(fontstyle);
        et_name.setTypeface(fontstyle);
        et_phno.setTypeface(fontstyle);
        et_uname.setTypeface(fontstyle);
        et_password.setTypeface(fontstyle);
        et_cpassword.setTypeface(fontstyle);
        et_email.setTypeface(fontstyle);
        tv6.setTypeface(fontstyle);
        tv7.setTypeface(fontstyle);
        tv8.setTypeface(fontstyle);
        et_shippingadd.setTypeface(fontstyle);
        et_locality.setTypeface(fontstyle);
        et_pincode.setTypeface(fontstyle);
        tv3.setTypeface(fontstyle);

        progressDialog = new ProgressDialog(UserEditProfileActivity.this);
        progressDialog.setMessage("Loading....");
        progressDialog.show();

        InventoryEndURL service = RetrofitInstance.getRetrofitInstance().create(InventoryEndURL.class);
        Call<List<EditProfile>> call = service.getUserProfile(session);

        call.enqueue(new Callback<List<EditProfile>>() {
            @Override
            public void onResponse(Call<List<EditProfile>> call, Response<List<EditProfile>> response) {

                progressDialog.dismiss();
                a1 = response.body();

                EditProfile user = a1.get(0);

                et_name.setText(user.getName());

                et_phno.setText(user.getPhone());

                et_email.setText(user.getEmailid());
                et_getres.setText(user.getRes());
                et_password.setText(user.getPwd());
                et_cpassword.setText(user.getPwd());
                et_locality.setText(user.getLocality());
                et_pincode.setText(user.getPincode());
                et_shippingadd.setText(user.getShipping_address());
            }

            @Override
            public void onFailure(Call<List<EditProfile>> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(UserEditProfileActivity.this, "Something went Wrong" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


        btn_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (et_name.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Name Should not be Empty", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (et_phno.getText().toString().isEmpty()|| et_phno.length()>10 ||et_phno.length()<10) {
                    Toast.makeText(getApplicationContext(), "Phone Number Should not be Empty or Invalid", Toast.LENGTH_SHORT).show();
                    return;
                }


                if (et_uname.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "User Name Should not be Empty", Toast.LENGTH_SHORT).show();
                    return;
                }


                if (et_password.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Password Should not be Empty", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (et_password.getText().toString().isEmpty()|| et_password.length()<8) {
                    Toast.makeText(getApplicationContext(), "Password Should have 8 characters", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (et_cpassword.getText().toString().isEmpty()|| et_password.length()<8) {
                    Toast.makeText(getApplicationContext(), "Passwords Should have 8 characters", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!et_password.getText().toString().equals( et_cpassword.getText().toString()) ) {
                    Toast.makeText(getApplicationContext(), "Passwords does not match", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (et_email.getText().toString().trim().matches(emailPattern)) {
                    //  Toast.makeText(getApplicationContext(),"Profile Updated Successfully",Toast.LENGTH_SHORT).show();


                }

                else {
                    Toast.makeText(getApplicationContext(),"Invalid email address", Toast.LENGTH_SHORT).show();
                }

                if (et_pincode.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Pincode Should not be Empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (et_locality.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Locality Should not be Empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (et_shippingadd.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Shipping Address Should not be Empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                submitData();
                finish();

            }
        });

    }



    private void submitData() {
        String name = et_name.getText().toString();
        String email = et_email.getText().toString();
        String phno = et_phno.getText().toString();
        String pwd = et_password.getText().toString();
        String pincode = et_pincode.getText().toString();
        String locality = et_locality.getText().toString();
        String shipingadd = et_shippingadd.getText().toString();
        String res = et_getres.getText().toString();

        progressDialog = new ProgressDialog(UserEditProfileActivity.this);
        progressDialog.setMessage("Loading....");
        progressDialog.show();

        session = sharedPreferences.getString("user_name", "def-val");

        InventoryEndURL service = RetrofitInstance.getRetrofitInstance().create(InventoryEndURL.class);
        Call<ResponseData> call = service.update_user_profile(name, phno, email, pwd,pincode,locality,shipingadd,session,res);

        call.enqueue(new Callback<ResponseData>() {
            @Override
            public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {

                //progressDialog.dismiss();
                a2 = response.body();


                if (response.body().status.equals("true")) {
                    Toast.makeText(UserEditProfileActivity.this, "Profile Updated Successfully", Toast.LENGTH_LONG).show();
                    finish();
                } else {
                    Toast.makeText(UserEditProfileActivity.this, "Please fill in all the fields", Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<ResponseData> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(UserEditProfileActivity.this, "" + t.getMessage(), Toast.LENGTH_SHORT).show();

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
                Intent i=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);
                finish();

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}