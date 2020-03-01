package com.bawp.myapplication.DatabaseConnection;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class RetrofitInstance {

        private static Retrofit result;



        private static final String BASE_URL = "http://inventoryandorderingapp.com/";




        public static Retrofit getRetrofitInstance() {



            if (result == null) {
                result = new Retrofit.Builder()
                        .baseUrl(BASE_URL)

                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
            }
            return result;
        }
    }


