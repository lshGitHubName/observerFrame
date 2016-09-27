package com.xj.yns.entity;

/**
 * Created by Administrator on 2016/9/8 0008.
 */
public class Goods {


    /**
     * id : 4
     * name : 测试商品11111
     * price : 10000.0
     * picture : /images/f13fa78f-03ae-4eef-977f-dd28c8c73ce3.jpg
     */

    private int id;
    private String name;
    private double price;
    private String picture;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    @Override
    public String toString() {
        return "Goods{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", picture='" + picture + '\'' +
                '}';
    }
}
