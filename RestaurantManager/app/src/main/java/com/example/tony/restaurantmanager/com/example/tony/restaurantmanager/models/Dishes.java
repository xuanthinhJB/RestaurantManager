package com.example.tony.restaurantmanager.com.example.tony.restaurantmanager.models;

import android.graphics.Bitmap;

/**
 * Created by phanx on 03/01/2018.
 */

public class Dishes {
    private byte[] dishImage;
    private String dishName;
    private long dishPrice;
    private String dishUnit;

    public Dishes(byte[] dishImage, String dishName, long dishPrice, String dishUnit) {
        this.dishImage = dishImage;
        this.dishName = dishName;
        this.dishPrice = dishPrice;
        this.dishUnit = dishUnit;
    }

    public Dishes() {
    }

    public byte[] getDishImage() {
        return dishImage;
    }

    public void setDishImage(byte[] dishImage) {
        this.dishImage = dishImage;
    }

    public String getDishName() {
        return dishName;
    }

    public void setDishName(String dishName) {
        this.dishName = dishName;
    }

    public long getDishPrice() {
        return dishPrice;
    }

    public void setDishPrice(long dishPrice) {
        this.dishPrice = dishPrice;
    }

    public String getDishUnit() {
        return dishUnit;
    }

    public void setDishUnit(String dishUnit) {
        this.dishUnit = dishUnit;
    }
}
