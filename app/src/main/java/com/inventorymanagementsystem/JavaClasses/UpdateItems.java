package com.inventorymanagementsystem.JavaClasses;

import com.google.gson.annotations.SerializedName;

public class UpdateItems {




        @SerializedName("goods_name")
        private String goods_name;

        @SerializedName("quantity")
        private String quantity;

        public UpdateItems(String goods_name, String quantity) {
            this.setGoods_name(goods_name);
            this.setQuantity(quantity);



        }

        public String getQuantity() {
            return quantity;
        }

        public void setQuantity(String quantity) {
            this.quantity = quantity;
        }

        public String getGoods_name() {
            return goods_name;
        }

        public void setGoods_name(String goods_name) {
            this.goods_name = goods_name;
        }

    }
