package com.example.quanlythucdon.entity;

public class Nhanvien {
    private int ma;
    private String ten;
    private String diachi;
    private String email;
    private int luong;

    public Nhanvien(int ma, String ten, String diachi, String email, int luong) {
        this.ma = ma;
        this.ten = ten;
        this.diachi = diachi;
        this.email = email;
        this.luong = luong;
    }

    public int getMa() {
        return ma;
    }

    public void setMa(int ma) {
        this.ma = ma;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getLuong() {
        return luong;
    }

    public void setLuong(int luong) {
        this.luong = luong;
    }
}
