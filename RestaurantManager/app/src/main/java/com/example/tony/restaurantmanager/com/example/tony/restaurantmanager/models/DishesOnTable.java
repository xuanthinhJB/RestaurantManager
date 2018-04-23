package com.example.tony.restaurantmanager.com.example.tony.restaurantmanager.models;

/**
 * Created by phanx on 26/01/2018.
 */

public class DishesOnTable {
    private String dishName;
    private int dishAmount;
    private long dishBillMember;

    public DishesOnTable() {
    }

    public DishesOnTable(String dishName, int dishAmount, long dishBillMember) {
        this.dishName = dishName;
        this.dishAmount = dishAmount;
        this.dishBillMember = dishBillMember;
    }

    public String getDishName() {
        return dishName;
    }

    public void setDishName(String dishName) {
        this.dishName = dishName;
    }

    public int getDishAmount() {
        return dishAmount;
    }

    public void setDishAmount(int dishAmount) {
        this.dishAmount = dishAmount;
    }

    public long getDishBillMember() {
        return dishBillMember;
    }

    public void setDishBillMember(long dishBillMember) {
        this.dishBillMember = dishBillMember;
    }
}
