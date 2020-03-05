package com.inventorymanagementsystem.adapterView;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.inventorymanagementsystem.R;
import com.inventorymanagementsystem.JavaClasses.NewOrdersActivity;
import com.inventorymanagementsystem.JavaClasses.AddItems;

import java.util.List;

public class NewOrderAdapter extends RecyclerView.Adapter<NewOrderAdapter.MyviewHolder> {
    Context context;
    int counter=0;
    String url="http://inventoryandorderingapp.com/InventoryManagementSystem/";
    List<AddItems> a1;

    public NewOrderAdapter(Context context, List<AddItems> movieList) {
        this.context = context;
        this.a1 = movieList;
    }

    @Override
    public MyviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_neworders,parent,false);
        return new MyviewHolder(view);
    }

    @Override
    public void onBindViewHolder(final @NonNull MyviewHolder holder,final int position) {
        counter=0;


        holder.tv_item_name.setText(a1.get(position).getItem_name());
        holder.tv_price.setText("$"+a1.get(position).getPrice());
        if(Integer.parseInt(a1.get(position).getQuantity())>0 && Integer.parseInt(a1.get(position).getQuantity())<10 ) {
            holder.tv_quantity.setText("Only " + a1.get(position).getQuantity() + " left in stock");
        } else if(Integer.parseInt(a1.get(position).getQuantity())==0){
            holder.tv_quantity.setText("Out of stock");
        }else {
            holder.tv_quantity.setTextColor(Color.parseColor("#010203"));
            holder.tv_quantity.setText("Available: "+ a1.get(position).getQuantity());

        }

        holder.tv_count.setText("" + NewOrdersActivity.cnt[position]);

        if(NewOrdersActivity.cnt[position]>0) {
            holder.ll.setBackgroundColor(Color.parseColor("#FF9891"));
        }else{
            holder.ll.setBackgroundColor(Color.parseColor("#FFFFFF"));
        }

        holder.btn_inc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                NewOrdersActivity.cnt[position] = NewOrdersActivity.cnt[position] + 1;

                if(NewOrdersActivity.cnt[position]<=Integer.parseInt(a1.get(position).getQuantity())){
                    holder.tv_count.setText(String.valueOf(NewOrdersActivity.cnt[position]));
                    ((NewOrdersActivity)context).addToCart();


                }

                else {
                    Toast.makeText(context, "Please Select a lower Quantity", Toast.LENGTH_SHORT).show();
                    String.valueOf(NewOrdersActivity.cnt[position]=0);

                }


                if(NewOrdersActivity.cnt[position]>0) {
                    holder.ll.setBackgroundColor(Color.parseColor("#0009ff"));
                }else{
                    holder.ll.setBackgroundColor(Color.parseColor("#FFFFFF"));
                }
            }
        });


        holder.btn_dec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(NewOrdersActivity.cnt[position]>0) {
                    NewOrdersActivity.cnt[position] = NewOrdersActivity.cnt[position] - 1;
                    holder.tv_count.setText(String.valueOf(NewOrdersActivity.cnt[position]));
                    ((NewOrdersActivity) context).addToCart();
                }else if(NewOrdersActivity.cnt[position]==0) {
                    holder.tv_count.setText(String.valueOf(NewOrdersActivity.cnt[position]));
                    ((NewOrdersActivity) context).addToCart();
                }
                if(NewOrdersActivity.cnt[position]>0) {
                    holder.ll.setBackgroundColor(Color.parseColor("#0009ff"));
                }else{
                    holder.ll.setBackgroundColor(Color.parseColor("#FFFFFF"));
                }

            }

        });



        Glide.with(context).load(url+a1.get(position).getItem_img_url()).apply(RequestOptions.centerCropTransform()).into(holder.image_view);

    }


    @Override
    public int getItemCount() {
        if(a1 != null){
            return a1.size();
        }
        return 0;

    }

    public class MyviewHolder extends RecyclerView.ViewHolder {
        TextView tv_item_name,tv_price,tv_quantity;
        TextView tv_count;
        ImageView image_view;
        LinearLayout ll;
        ImageView btn_inc,btn_dec;

        public MyviewHolder(View itemView) {
            super(itemView);

            image_view = (ImageView)itemView.findViewById(R.id.image_view);
            tv_item_name=(TextView)itemView.findViewById(R.id.tv_item_name);
            tv_price=(TextView)itemView.findViewById(R.id.tv_price);
            tv_quantity=(TextView)itemView.findViewById(R.id.tv_quantity1);

            tv_count=(TextView) itemView.findViewById(R.id.tv_count);
            btn_inc=(ImageView)itemView.findViewById(R.id.btn_inc);
            btn_dec=(ImageView)itemView.findViewById(R.id.btn_dec);
            ll=(LinearLayout)itemView.findViewById(R.id.ll);

        }
    }
}