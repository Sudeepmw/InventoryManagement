package com.inventorymanagementsystem.adapterView;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.inventorymanagementsystem.R;
import com.inventorymanagementsystem.JavaClasses.ViewAllMyOrders;

import java.util.List;

public class ViewAllMyOrdersAdapter extends BaseAdapter {
    List<ViewAllMyOrders> ar;
    Context cnt;
    public ViewAllMyOrdersAdapter(List<ViewAllMyOrders> ar, Context cnt)
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
        View obj2=obj1.inflate(R.layout.list_all_my_orders,null);

        TextView tv_order_id=(TextView)obj2.findViewById(R.id.tv_order_id);
        tv_order_id.setText("Order ID  :"+ar.get(pos).getId());


        TextView tv_item_name=(TextView)obj2.findViewById(R.id.tv_item_name);
        tv_item_name.setText("Item Name  :"+ar.get(pos).getItems());


        TextView tv_quantity=(TextView)obj2.findViewById(R.id.tv_quantity);
        tv_quantity.setText("Quantity  :"+ar.get(pos).quantity);

        TextView tv_price=(TextView)obj2.findViewById(R.id.tv_price);
        tv_price.setText("Price  :"+"$"+ar.get(pos).getPrice());


        TextView tv_order_date=(TextView)obj2.findViewById(R.id.tv_order_date);
        tv_order_date.setText("Date  :"+ar.get(pos).getOrder_date());

        TextView tv_status=(TextView)obj2.findViewById(R.id.tv_status);
        tv_status.setText("Status Of Order  :"+ar.get(pos).getStatus());

        Typeface fontstyle=Typeface.createFromAsset(cnt.getAssets(),"fonts/Lato-Medium.ttf");
        tv_item_name.setTypeface(fontstyle);
        tv_price.setTypeface(fontstyle);
        tv_order_date.setTypeface(fontstyle);
        tv_status.setTypeface(fontstyle);
        tv_quantity.setTypeface(fontstyle);



        return obj2;

    }
  
}
