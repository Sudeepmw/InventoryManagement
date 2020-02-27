package com.bawp.myapplication.Classes;


import com.google.gson.annotations.SerializedName;

public class Profile {
    @SerializedName("name")
    private String name ;

    @SerializedName("phone")
    private
    String phone ;

    @SerializedName("emailid")
    private
    String emailid ;



    @SerializedName("pwd")
    private
    String pwd ;

    @SerializedName("pincode")
    private
    String pincode ;

    @SerializedName("locality")
    private
    String locality ;

    @SerializedName("shipping_address")
    private
    String shipping_address ;



    Profile(String name, String phone, String emailid, String pwd){

        this.setName(name);
        this.setPhone(phone);
        this.setEmailid(emailid);
        this.setPwd(pwd);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmailid() {
        return emailid;
    }

    public void setEmailid(String emailid) {
        this.emailid = emailid;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public String getShipping_address() {
        return shipping_address;
    }

    public void setShipping_address(String shipping_address) {
        this.shipping_address = shipping_address;
    }
}
