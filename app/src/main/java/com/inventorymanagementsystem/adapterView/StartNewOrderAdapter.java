package com.inventorymanagementsystem.adapterView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.inventorymanagementsystem.R;
import com.inventorymanagementsystem.JavaClasses.AddItems;

import java.util.List;

public class StartNewOrderAdapter extends BaseAdapter {
    List<AddItems> ar;
    Context cnt;
    int counter=0;

    public StartNewOrderAdapter(List<AddItems> ar, Context cnt)
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
        final View obj2=obj1.inflate(R.layout.list_new_orders,null);


        TextView tv_itemname=(TextView)obj2.findViewById(R.id.tv_itemname);
        tv_itemname.setText("Goods Name  :"+ar.get(pos).getItem_name());


        TextView tv_price=(TextView)obj2.findViewById(R.id.tv_price);
        tv_price.setText("Price  :"+ar.get(pos).getPrice());

        final Spinner spin_quantity=(Spinner)obj2.findViewById(R.id.spin_quantity);
        final TextView tvtot=(TextView)obj2.findViewById(R.id.tvtot);
        final TextView tvtotbil=(TextView)obj2.findViewById(R.id.tvtotbil);


        ImageView btn_inc=(ImageView)obj2.findViewById(R.id.btn_inc);

        btn_inc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvtot.setText("Iteam Name"+ar.get(pos).getItem_name()+" --> "+spin_quantity.getSelectedItem().toString()+" * " +ar.get(pos).getPrice());

                int tot = Integer.parseInt(ar.get(pos).getPrice()) * Integer.parseInt(spin_quantity.getSelectedItem().toString());
                tvtotbil.setText("Total Price  =" + tot);


            }
        });
        return obj2;
    }
  
}
