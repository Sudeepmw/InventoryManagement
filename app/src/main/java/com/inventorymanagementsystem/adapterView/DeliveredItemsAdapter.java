package com.inventorymanagementsystem.adapterView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.inventorymanagementsystem.R;
import com.inventorymanagementsystem.JavaClasses.DeliveredItems;

import java.util.List;


public class DeliveredItemsAdapter extends BaseAdapter {
    List<DeliveredItems> ar;
    Context cnt;
    public DeliveredItemsAdapter(List<DeliveredItems> ar, Context cnt)
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
        View obj2=obj1.inflate(R.layout.row_delivered_items,null);

        TextView tv_order_id=(TextView)obj2.findViewById(R.id.tv_order_id);
        tv_order_id.setText("Order ID  :"+ar.get(pos).id);

        TextView tv_items=(TextView)obj2.findViewById(R.id.tv_items);
        tv_items.setText("Item  :"+ar.get(pos).items);


        TextView tv_quantity=(TextView)obj2.findViewById(R.id.tv_quantity);
        tv_quantity.setText("Quantity  :"+ar.get(pos).quantity);

        TextView tv_price=(TextView)obj2.findViewById(R.id.tv_price);
        tv_price.setText("Price  :"+"$"+ar.get(pos).price);

        TextView tv_order_date=(TextView)obj2.findViewById(R.id.tv_order_date);
        tv_order_date.setText("Date  :"+ar.get(pos).order_date);


        TextView tv_order_created_by=(TextView)obj2.findViewById(R.id.tv_order_created_by);
        tv_order_created_by.setText("Order By  :"+ar.get(pos).order_created_by);



        return obj2;

    }

}
