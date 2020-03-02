package com.inventorymanagementsystem.JavaClasses;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.inventorymanagementsystem.InventoryEndURL;
import com.inventorymanagementsystem.R;
import com.inventorymanagementsystem.ResponseData;
import com.inventorymanagementsystem.RetrofitInstance;
import com.inventorymanagementsystem.Utils;
import com.inventorymanagementsystem.adapterView.NewOrderAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewOrdersActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    NewOrderAdapter newOrderAdapter;
    List<AddItems> a1;
    public static int[] cnt;
    TextView tvBill,tvTotal;
    int total=0;
    TextView btnClose,btnPrint,btnClear,tv_submit;
    ProgressDialog progressDialog;
    SharedPreferences sharedPreferences;
    String session;
    //Button btn_submit;
    String st;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_neworers);
        getSupportActionBar().hide();

        sharedPreferences = getSharedPreferences(Utils.SHREF, Context.MODE_PRIVATE);
        session = sharedPreferences.getString("user_name", "");
        tv_submit=(TextView) findViewById(R.id.tv_submit);
        tv_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //submitData();
                if (tvTotal.getText().toString().equals("Total : $"+"0")) {
                    Toast.makeText(getApplicationContext(), "Please add Items to the Cart", Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent intent=new Intent(NewOrdersActivity.this,PaymentActivity.class);
                intent.putExtra("item_name",tvBill.getText().toString());
                intent.putExtra("price",total+"");
                intent.putExtra("date",tvBill.getText().toString());
                intent.putExtra("rest_name",tvBill.getText().toString());
                intent.putExtra("session",session);
                startActivity(intent);
            }
        });
        tvBill=(TextView)findViewById(R.id.tvBill);
        tvTotal=(TextView)findViewById(R.id.tvTotal);

        tvTotal.setText("Total : $"+"0");

        btnClose=(TextView)findViewById(R.id.btnClose);
        btnClear=(TextView)findViewById(R.id.btnClear);

        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(tvBill.getVisibility()==View.VISIBLE){
                    tvBill.setVisibility(View.GONE);
                }else{
                    tvBill.setVisibility(View.VISIBLE);
                }

            }
        });
       /* btnPrint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                printBill();
            }
        });*/
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for(int i=0;i<cnt.length;i++){
                    cnt[i]=0;
                }
                recyclerView.setAdapter(newOrderAdapter);
                tvBill.setText("");
                tvTotal.setText("Total : $"+"0");
            }
        });
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        recyclerView=(RecyclerView)findViewById(R.id.recyclerView);


        GridLayoutManager gridLayoutManager = new GridLayoutManager(NewOrdersActivity.this, 3);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(3, dpToPx(4), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());


        a1= new ArrayList<>();
        serverData();
    }

    public void serverData() {
        progressDialog = new ProgressDialog(NewOrdersActivity.this);
        progressDialog.setMessage("Loading....");
        progressDialog.show();

        InventoryEndURL service = RetrofitInstance.getRetrofitInstance().create(InventoryEndURL.class);
        Call<List<AddItems>> call = service.getiteams();
        call.enqueue(new Callback<List<AddItems>>() {
            @Override
            public void onResponse(Call<List<AddItems>> call, Response<List<AddItems>> response) {
                progressDialog.dismiss();
                if (response.body() == null) {
                    Toast.makeText(NewOrdersActivity.this, "No data found", Toast.LENGTH_SHORT).show();
                } else {
                    a1 = response.body();
                    //recyclerView.setAdapter(new DisplayInfoAdapter(a1, DisplayInfoActivity.this));
                    cnt = new int[a1.size()];
                    newOrderAdapter = new NewOrderAdapter(NewOrdersActivity.this, a1);
                    recyclerView.setAdapter(newOrderAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<AddItems>> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(NewOrdersActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    String str="",str1="";
    int i;
    public void addToCart(){
        total=0;
        str="";
        for(i=0;i<cnt.length;i++){
            if(cnt[i]>0) {
                str = str +cnt[i]+ "  x "+ a1.get(i).getItem_name()+"\n";
                str1=str1+cnt[i]+" x "+ a1.get(i).getItem_name()+",";
                total = total + (cnt[i]*Integer.parseInt(a1.get(i).getPrice()));
            }
        }
        tvTotal.setText("Total : $"+total);
        tvBill.setText(str);
        tvBill.setVisibility(View.VISIBLE);
        tvTotal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvBill.setText(str);
                tvBill.setVisibility(View.VISIBLE);
            }
        });

    }
    private void submitData() {

        progressDialog = new ProgressDialog(NewOrdersActivity.this);
        progressDialog.setMessage("Loading....");
        progressDialog.show();

        InventoryEndURL service = RetrofitInstance.getRetrofitInstance().create(InventoryEndURL.class);

        Call<ResponseData> call = service.add_orders(tvBill.getText().toString(),total+"","25/02/2020",session,"0","Bawarchi");
        call.enqueue(new Callback<ResponseData>() {
            @Override
            public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {
                if (response.body().status.equals("true")) {
                    progressDialog.dismiss();
                    Toast.makeText(NewOrdersActivity.this, response.body().message, Toast.LENGTH_LONG).show();
                    Intent intent=new Intent(NewOrdersActivity.this, OrderConfirmationSplash.class);
                    st = getIntent().getExtras().getString("Welcome");

                    startActivity(intent);
                    //finish();

                } else {
                    Toast.makeText(NewOrdersActivity.this, response.body().message, Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseData> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(NewOrdersActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {
        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }

    /**
     * Converting dp to pixel
     */
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }
}