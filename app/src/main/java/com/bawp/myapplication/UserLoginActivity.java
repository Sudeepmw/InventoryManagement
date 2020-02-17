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


            }
        });

// Data Base Login authentication
        // Sudeep Working on database

    }
}
