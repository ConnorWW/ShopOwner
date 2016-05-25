package com.center.shop;

import com.avos.avoscloud.AVObject;

/**
 * Created by apple on 16/5/13.
 */
public class Product {

    private String name;
    private String price;
    private String discription;
    private String type;
    private String type2;
    private String sell;
    private String score;
    private String id;
    private String pid;
    private String onsell;


    public Product(String name) {
        this.name = name;
    }

    public Product(AVObject product) {
        this.name = product.getString("productname");
        this.price = product.getString("productprice");
        this.discription = product.getString("productdis");
        this.type = product.getString("producttype");
        this.type2 = product.getString("producttype2");
        this.sell = product.getString("sell");
        this.score = product.getString("score");
        this.id = product.getString("idnumber");
        this.pid = product.getString("productnumber");
        this.onsell = product.getString("onsell");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSell() {
        return sell;
    }

    public void setSell(String sell) {
        this.sell = sell;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getType2() {
        return type2;
    }

    public void setType2(String type2) {
        this.type2 = type2;
    }

    public String getOnsell() {
        return onsell;
    }

    public void setOnsell(String onsell) {
        this.onsell = onsell;
    }
}
