package com.inventorymanagementsystem.JavaClasses;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.inventorymanagementsystem.InventoryEndURL;
import com.inventorymanagementsystem.R;
import com.inventorymanagementsystem.ResponseData;
import com.inventorymanagementsystem.RetrofitInstance;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserRegistrationActivity extends AppCompatActivity {
    EditText et_name, et_phno, et_uname, et_password,et_email,et_restaurent;
    TextView tv1,tv2,tv4,tv5,tv3,tv_restaurent;
    Button btn_reg;
    ProgressDialog progressDialog;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_registration);

        getSupportActionBar().setTitle("Registration");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tv1=(TextView)findViewById(R.id.tv1);
        tv2=(TextView)findViewById(R.id.tv2);
        tv4=(TextView)findViewById(R.id.tv4);
        tv5=(TextView)findViewById(R.id.tv5);
        tv3=(TextView)findViewById(R.id.tv3);
        tv_restaurent=(TextView)findViewById(R.id.tv3);


        btn_reg = (Button) findViewById(R.id.btn_reg);
        et_name = (EditText) findViewById(R.id.et_name);
        et_phno = (EditText) findViewById(R.id.et_phno);
        et_uname = (EditText) findViewById(R.id.et_uname);
        et_password = (EditText) findViewById(R.id.et_password);
        et_email = (EditText) findViewById(R.id.et_email);
        et_restaurent= (EditText) findViewById(R.id.et_restaurent);

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
        et_email.setTypeface(fontstyle);
        tv3.setTypeface(fontstyle);
        et_restaurent.setTypeface(fontstyle);
        tv_restaurent.setTypeface(fontstyle);



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
                    Toast.makeText(getApplicationContext(), "Password Should have 8 charaters", Toast.LENGTH_SHORT).show();
                    return;
                }
                else if (et_email.getText().toString().trim().matches(emailPattern)) {
                    // Toast.makeText(getApplicationContext(),"valid email address",Toast.LENGTH_SHORT).show();
                    submitData();

                }

                else {
                    Toast.makeText(getApplicationContext(),"Invalid email address", Toast.LENGTH_SHORT).show();
                }


                // finish();

            }
        });

    }

    public boolean isValidPassword(final String password){
        Pattern pattern;
        Matcher matcher;
        final String PASSWORD_PATTERN = "^(?=.[0-9])(?=.[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{4,}$";
        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);

        return matcher.matches();
    }

    private void submitData() {
        String name = et_name.getText().toString();
        String phno = et_phno.getText().toString();
        String email = et_email.getText().toString();
        String uname = et_uname.getText().toString();
        String pwd = et_password.getText().toString();
        String rest_name = et_restaurent.getText().toString();

        // String studtech = spinner_studteach.getSelectedItem().toString();

//        progressDialog = new ProgressDialog(UserRegistrationActivity.this);
//        progressDialog.setMessage("Loading....");
//        progressDialog.show();

        InventoryEndURL service = RetrofitInstance.getRetrofitInstance().create(InventoryEndURL.class);
        Call<ResponseData> call = service.user_registration(name, phno, email, uname, pwd,rest_name);
        call.enqueue(new Callback<ResponseData>() {
            @Override
            public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {
                if (response.body().message.equals("exists")) {
                    // progressDialog.dismiss();
                    Toast.makeText(UserRegistrationActivity.this, "Username already exists", Toast.LENGTH_LONG).show();
                    //finish();
                    Intent i=new Intent(getApplicationContext(),UserRegistrationActivity.class);
                    startActivity(i);

                }

                else  {
                    // progressDialog.dismiss();
                    Toast.makeText(UserRegistrationActivity.this, "Registered successfully.", Toast.LENGTH_LONG).show();
                    Intent i=new Intent(getApplicationContext(),UserLoginActivity.class);
                    startActivity(i);

                }

//                else {
//                    Toast.makeText(UserRegistrationActivity.this, "Something went wrong", Toast.LENGTH_LONG).show();
//                }
            }

            @Override
            public void onFailure(Call<ResponseData> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(UserRegistrationActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
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