package com.bawp.myapplication;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.inventorymanagementsystem.EndPointUrl;
import com.inventorymanagementsystem.ResponseData;
import com.inventorymanagementsystem.RetrofitInstance;
import com.inventorymanagementsystem.Utils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class UserLoginActivity extends AppCompatActivity {
    TextView tv_username,tv_password,tv_forgetpwd,tv_newuser,tv_signup;
    EditText et_USERNAME,et_PWD;
    Button btn_add;
    SharedPreferences sharedPreferences;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userloin);

        getSupportActionBar().setTitle("User Login");

       sharedPreferences = getSharedPreferences(Utils.SHREF, Context.MODE_PRIVATE);


        tv_username=(TextView)findViewById(R.id.tv_username);
        tv_password=(TextView)findViewById(R.id.tv_password);
        tv_forgetpwd=(TextView)findViewById(R.id.tv_forgetpwd);
        tv_newuser=(TextView)findViewById(R.id.tv_newuser);
        tv_signup=(TextView)findViewById(R.id.tv_signup);
        tv_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(UserLoginActivity.this, UserRegistrationActivity.class);
                 startActivity(intent);

            }
        });


        et_USERNAME=(EditText) findViewById(R.id.et_USERNAME);
        et_PWD=(EditText)findViewById(R.id.et_PWD);

        btn_add=(Button) findViewById(R.id.btn_add);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(et_USERNAME.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(),"Enter your User Name",Toast.LENGTH_LONG).show();
                    return;
                }
                if(et_PWD.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(),"Enter your Password",Toast.LENGTH_LONG).show();
                    return;
                }
                submitData();

            }
        });


        Typeface basicdt=Typeface.createFromAsset(getApplicationContext().getAssets(),"fonts/Lato-Medium.ttf");
        tv_username.setTypeface(basicdt);
        tv_password.setTypeface(basicdt);
        tv_forgetpwd.setTypeface(basicdt);
        tv_newuser.setTypeface(basicdt);
        tv_signup.setTypeface(basicdt);
        et_PWD.setTypeface(basicdt);
        btn_add.setTypeface(basicdt);

    }

    }
}
