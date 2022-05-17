package com.example.quanlythucdon.entity;

public class Thucdon {
    private int id;
    private String name;
    private int categoryId;
    private int price;

    public Thucdon(int id, String name, int categoryId, int price) {
        this.id = id;
        this.name = name;
        this.categoryId = categoryId;
        this.price = price;
    }

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

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
