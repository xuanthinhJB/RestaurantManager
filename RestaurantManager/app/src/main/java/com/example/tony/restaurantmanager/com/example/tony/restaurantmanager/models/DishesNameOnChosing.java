package com.example.tony.restaurantmanager.com.example.tony.restaurantmanager.models;

/**
 * Created by phanx on 01/02/2018.
 */

public class DishesNameOnChosing {
    private String dishName;
    private boolean dishOnChosing;
    private int dishAmount;

    public DishesNameOnChosing(String dishName, boolean dishOnChosing, int dishAmount) {
        this.dishName = dishName;
        this.dishOnChosing = dishOnChosing;
    }

    public DishesNameOnChosing() {}

    public String getDishName() {
        return this.dishName;
    }

    public void setDishName(String dishName) {
        this.dishName = dishName;
    }

    public boolean isDishOnChosing() {
        return this.dishOnChosing;
    }

    public void setDishOnChosing(boolean dishOnChosing) {
        this.dishOnChosing = dishOnChosing;
    }

    public int getDishAmount() {
        return dishAmount;
    }

    public void setDishAmount(int dishAmount) {
        this.dishAmount = dishAmount;
    }
}

