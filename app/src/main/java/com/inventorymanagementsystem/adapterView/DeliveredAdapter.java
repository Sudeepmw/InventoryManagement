package com.inventorymanagementsystem.adapterView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.inventorymanagementsystem.R;
import com.inventorymanagementsystem.JavaClasses.Delivered;

import java.util.List;

public class DeliveredAdapter extends BaseAdapter {
    List<Delivered> ar;
    Context cnt;
    public DeliveredAdapter(List<Delivered> ar, Context cnt)
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
        View obj2=obj1.inflate(R.layout.list_delivered,null);


        TextView tv_name=(TextView)obj2.findViewById(R.id.tv_name);
        tv_name.setText("Name  :"+ar.get(pos).getName());


        TextView tv_phno=(TextView)obj2.findViewById(R.id.tv_phno);
        tv_phno.setText("Phone Number  :"+ar.get(pos).getPhno());

        TextView tv_goods_name=(TextView)obj2.findViewById(R.id.tv_goods_name);
        tv_goods_name.setText("Goods Name  :"+ar.get(pos).getGoods_name());


        TextView tv_quantity=(TextView)obj2.findViewById(R.id.tv_quantity);
        tv_quantity.setText("Quantity  :"+ar.get(pos).getQuantity());





        return obj2;

    }
  
}
