package com.inventorymanagementsystem.adapterView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.inventorymanagementsystem.R;
import com.inventorymanagementsystem.JavaClasses.UpdateItems;

import java.util.List;

public class UpdateItemsAdapter extends BaseAdapter {
    List<UpdateItems> ar;
    Context cnt;
    public UpdateItemsAdapter(List<UpdateItems> ar, Context cnt)
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
        View obj2=obj1.inflate(R.layout.list_update_items,null);


        TextView tv_goods_name=(TextView)obj2.findViewById(R.id.tv_goods_name);
        tv_goods_name.setText("Goods Name  :"+ar.get(pos).getGoods_name());


        TextView tv_quantity=(TextView)obj2.findViewById(R.id.tv_quantity);
        tv_quantity.setText("Quantity  :"+ar.get(pos).getQuantity());


       // btn_Delete=(Button)obj2.findViewById(R.id.btn_Delete);

        Button btn_Update=(Button)obj2.findViewById(R.id.btn_Update);
        btn_Update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 Toast.makeText(cnt,"Succussfully Updated",Toast.LENGTH_LONG).show();

              /*  AlertDialog.Builder builder = new AlertDialog.Builder(cnt);
                builder.setTitle("Test");
                builder.setMessage("test");
                builder.append("Id :"+ res.getString(0)+"\n");
                builder.setPositiveButton("Update",
                        new DialogInterface.OnClickListener()
                        {
                            public void onClick(DialogInterface dialog, int id)
                            {
                                //dialog.cancel();
                                cnt.startActivity(new Intent(cnt, AddItemsActivity.class));
                            }
                        });

                builder.setNeutralButton("Cancle",
                        new DialogInterface.OnClickListener()
                        {
                            public void onClick(DialogInterface dialog, int id)
                            {
                                //cnt.startActivity(new Intent(cnt, AddItemsActivity.class));
                                dialog.cancel();
                            }
                        });

             *//*   builder.setNegativeButton("Exit",
                        new DialogInterface.OnClickListener()
                        {
                            public void onClick(DialogInterface dialog, int id)
                            {
                                dialog.cancel();
                            }
                        });*//*
                builder.create().show();


         */
            }
        });

        return obj2;

    }
  
}
