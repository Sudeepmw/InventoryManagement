package com.inventorymanagementsystem.adapterView;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;

import com.inventorymanagementsystem.InventoryEndURL;
import com.inventorymanagementsystem.JavaClasses.ViewOrdersAdminActivity;
import com.inventorymanagementsystem.R;
import com.inventorymanagementsystem.ResponseData;
import com.inventorymanagementsystem.RetrofitInstance;
import com.inventorymanagementsystem.JavaClasses.ViewOrdersStatusAdmin;
import com.inventorymanagementsystem.JavaClasses.ViewOrders;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewOrdersAdapter extends BaseAdapter {
    List<ViewOrders> ar;
    Context cnt;

    public ViewOrdersAdapter(List<ViewOrders> ar, Context cnt)
    {
        this.ar=ar;
        this.cnt=cnt;
    }
    @Override
    public int getCount() {
        return ar.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int pos, View view, ViewGroup viewGroup)
    {
        LayoutInflater obj1 = (LayoutInflater)cnt.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View obj2=obj1.inflate(R.layout.list_view_orders,null);


        TextView tv_order_id=(TextView)obj2.findViewById(R.id.tv_order_id);
        tv_order_id.setText("Order ID  :"+ar.get(pos).getId());

        TextView tv_order_created_by=(TextView)obj2.findViewById(R.id.tv_order_created_by);
        tv_order_created_by.setText("Ordered By  :"+ar.get(pos).getOrder_created_by());



        CardView card_view=(CardView)obj2.findViewById(R.id.card_view);
        card_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(cnt, ViewOrdersStatusAdmin.class);
                intent.putExtra("OrderID",ar.get(pos).getId());
                intent.putExtra("IteamName",ar.get(pos).getItems());
                intent.putExtra("Quantity",ar.get(pos).getQuantity());
                intent.putExtra("Price",ar.get(pos).getPrice());
                intent.putExtra("RestaurentName",ar.get(pos).getRestaurant_name());
                intent.putExtra("OrderDate",ar.get(pos).getOrder_date());
                intent.putExtra("CreatedBy",ar.get(pos).getOrder_created_by());
                cnt.startActivity(intent);

            }
        });
        TextView tv_item_name=(TextView)obj2.findViewById(R.id.tv_item_name);
        tv_item_name.setText("Item Name  :"+ar.get(pos).getItems());


        TextView tv_quantity=(TextView)obj2.findViewById(R.id.tv_quantity);
        tv_quantity.setText("Quantity  :"+ar.get(pos).getQuantity());

        TextView tv_price=(TextView)obj2.findViewById(R.id.tv_price);
        tv_price.setText("Price  :"+"$"+ar.get(pos).getPrice());


        TextView tv_order_date=(TextView)obj2.findViewById(R.id.tv_order_date);
        tv_order_date.setText(" Date  :"+ar.get(pos).getOrder_date());

        TextView tv_restaurent_name=(TextView)obj2.findViewById(R.id.tv_restaurent_name);
        tv_restaurent_name.setText("Restaurent Name  :"+ar.get(pos).getRestaurant_name());





        Button btn_deliver=(Button)obj2.findViewById(R.id.btn_deliver);
        btn_deliver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitData(ar.get(pos).getId(),"Delivered");
                Toast.makeText(cnt,"Delivered status is updated successfully.",Toast.LENGTH_LONG).show();
                ((ViewOrdersAdminActivity)cnt).finish();
            }
        });

        Button btn_not_deliver=(Button)obj2.findViewById(R.id.btn_not_deliver);
        btn_not_deliver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitData(ar.get(pos).getId(),"Out of Stock");
                Toast.makeText(cnt,"Out Of Stock status is updated successfully.",Toast.LENGTH_LONG).show();
                ((ViewOrdersAdminActivity)cnt).finish();
            }
        });


        Typeface fontstyle=Typeface.createFromAsset(cnt.getAssets(),"fonts/Lato-Medium.ttf");
        btn_not_deliver.setTypeface(fontstyle);
        btn_deliver.setTypeface(fontstyle);
        tv_item_name.setTypeface(fontstyle);
        tv_price.setTypeface(fontstyle);
        tv_order_date.setTypeface(fontstyle);

        return obj2;

    }
    ProgressDialog progressDialog;
    private void submitData(String id,String status) {
        progressDialog = new ProgressDialog(cnt);
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
                    Toast.makeText(cnt, response.body().message, Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<ResponseData> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(cnt, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
  
}