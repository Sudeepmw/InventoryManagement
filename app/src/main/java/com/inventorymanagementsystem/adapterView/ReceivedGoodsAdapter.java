package com.inventorymanagementsystem.adapterView;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.inventorymanagementsystem.R;
import com.inventorymanagementsystem.JavaClasses.ReorderActivity;
import com.inventorymanagementsystem.JavaClasses.ReceivedGoods;

import java.util.List;

public class ReceivedGoodsAdapter extends BaseAdapter {
    List<ReceivedGoods> ar;
    Context cnt;
    public ReceivedGoodsAdapter(List<ReceivedGoods> ar, Context cnt)
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
        View obj2=obj1.inflate(R.layout.list_received_goods,null);


        TextView tv_order_id=(TextView)obj2.findViewById(R.id.tv_order_id);
        tv_order_id.setText("Order ID  :"+ar.get(pos).getId());

        TextView tv_item_name=(TextView)obj2.findViewById(R.id.tv_item_name);
        tv_item_name.setText("Item Name  :"+ar.get(pos).getItems());


        TextView tv_quantity=(TextView)obj2.findViewById(R.id.tv_quantity);
        tv_quantity.setText("Quantity  :"+ar.get(pos).quantity);

        TextView tv_price=(TextView)obj2.findViewById(R.id.tv_price);
        tv_price.setText("Price  :"+"$"+ar.get(pos).getPrice());

        TextView tv_restaurant_name=(TextView)obj2.findViewById(R.id.tv_restaurant_name);
        tv_restaurant_name.setText("Restaurent Name  :"+ar.get(pos).getRestaurant_name());




        TextView tv_order_date=(TextView)obj2.findViewById(R.id.tv_order_date);
        tv_order_date.setText(" Date  :"+ar.get(pos).getOrder_date());

        TextView tv_status=(TextView)obj2.findViewById(R.id.tv_status);
        tv_status.setText(" Status Of Order  :"+ar.get(pos).getStatus());

        Button btn_reorder=(Button)obj2.findViewById(R.id.btn_reorder);
        btn_reorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(cnt, ReorderActivity.class);
                intent.putExtra("iteam_name",ar.get(pos).getItems());
                intent.putExtra("price",ar.get(pos).getPrice());
                intent.putExtra("quantity",ar.get(pos).getItems());
                intent.putExtra("restaurent_name",ar.get(pos).getRestaurant_name());
                cnt.startActivity(intent);

            }
        });

        Typeface fontstyle=Typeface.createFromAsset(cnt.getAssets(),"fonts/Lato-Medium.ttf");
        tv_item_name.setTypeface(fontstyle);
        tv_price.setTypeface(fontstyle);
        tv_order_date.setTypeface(fontstyle);
        tv_status.setTypeface(fontstyle);



        return obj2;

    }
  
}
