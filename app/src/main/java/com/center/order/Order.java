package com.center.order;

import com.center.shop.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by apple on 16/5/13.
 */
public class Order {
    private List<Product> products=new ArrayList<Product>();
    private Double price;
    private Double discount;
    private Double extraFee;

    private String seller;
    private String seller_id;

    private String receiver;
    private String receiver_id;

    private String phone;
    private String address;
    private String note;
    private int status;

    public Order(List<Product> products, Double price, Double discount, Double extraFee, String receiver, String phone, String address, String note, int status) {
        this.products = products;
        this.price = price;
        this.discount = discount;
        this.extraFee = extraFee;


        this.receiver = receiver;
        this.phone = phone;
        this.address = address;

        this.note = note;
        this.status = status;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Double getExtraFee() {
        return extraFee;
    }

    public void setExtraFee(Double extraFee) {
        this.extraFee = extraFee;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
