package com.inventorymanagementsystem.adapterView;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.inventorymanagementsystem.InventoryEndURL;
import com.inventorymanagementsystem.R;
import com.inventorymanagementsystem.ResponseData;
import com.inventorymanagementsystem.RetrofitInstance;
import com.inventorymanagementsystem.JavaClasses.EditItemActivity;
import com.inventorymanagementsystem.JavaClasses.UpdateItemsActivity;
import com.inventorymanagementsystem.JavaClasses.Items;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditItemAdapter extends BaseAdapter {
    List<Items> ar;
    Context cnt;
    String id;
    String url="http://inventoryandorderingapp.com/InventoryManagementSystem/";
    public EditItemAdapter(List<Items> ar, Context cnt)
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
        View obj2=obj1.inflate(R.layout.row_edit_item,null);


        TextView tv_item_name=(TextView)obj2.findViewById(R.id.tv_item_name);
        tv_item_name.setText("Item Name  :"+ ar.get(pos).getItem_name());


        final TextView tv_price=(TextView)obj2.findViewById(R.id.tv_price);
        tv_price.setText("Price  :"+"$"+ ar.get(pos).getPrice());

        final TextView tv_quantity=(TextView)obj2.findViewById(R.id.tv_quantity);
        tv_quantity.setText("Quantity  :"+ar.get(pos).getQuantity());

        final ImageView image_view=(ImageView)obj2.findViewById(R.id.image_view);
        Glide.with(cnt).load(url+ar.get(pos).getItem_img_url()).into(image_view);

        tv_price.setTag(ar.get(pos).getId());


        Button btn_Update=(Button)obj2.findViewById(R.id.btn_Update);
        btn_Update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(cnt, EditItemActivity.class);
                intent.putExtra("item_name", ar.get(pos).getItem_name());
                intent.putExtra("item_price", ar.get(pos).getPrice());
                intent.putExtra("item_id", ar.get(pos).getId());
                intent.putExtra("quantity", ar.get(pos).getQuantity());
                intent.putExtra("image",url+ar.get(pos).getItem_img_url());
                cnt.startActivity(intent);
                ((UpdateItemsActivity)cnt).finish();
            }
        });


       Button btn_Delete=(Button)obj2.findViewById(R.id.btn_Delete);
        btn_Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                id=tv_price.getTag().toString();
                submitdata(id);
            }
        });

        return obj2;

    }

    public  void submitdata(String id)
    {

        InventoryEndURL apiService = RetrofitInstance.getRetrofitInstance().create(InventoryEndURL.class);
        Call<ResponseData> call = apiService.delete_items(id);
        call.enqueue(new Callback<ResponseData>() {
            @Override
            public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {
                if (response.body().message.equals("true")) {
                    Toast.makeText(cnt, response.body().message, Toast.LENGTH_LONG).show();
                    Log.i("msg", "" + response.body().message);
                    ((UpdateItemsActivity)cnt).finish();

                } else {
                    Toast.makeText(cnt, response.body().message, Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseData> call, Throwable t) {
                Log.d("TAG","Response = "+t.toString());
            }
        });
    }

}