package com.example.tony.restaurantmanager.com.example.tony.restaurantmanager.models;


import java.io.Serializable;

/**
 * Created by phanx on 22/12/2017.
 */

public class TableModel implements Serializable {
    private int tableID;
    private String tableName;
    private int tableNumberOfPeople;
    private String tableDishes;
    private long tableTimeIn;
    private long tableTimeOut;
    private boolean tableActive = false;

    public TableModel() {
    }

    public TableModel(int tableID, String tableName, int tableNumberOfPeople, String tableDishes, long tableTimeIn, long tableTimeOut, boolean tableActive) {
        this.tableID = tableID;
        this.tableName = tableName;
        this.tableNumberOfPeople = tableNumberOfPeople;
        this.tableDishes = tableDishes;
        this.tableTimeIn = tableTimeIn;
        this.tableTimeOut = tableTimeOut;
        this.tableActive = tableActive;
    }

    public int getTableID(){
        return tableID;
    }

    public void setTableID(int tableID){
        this.tableID = tableID;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public int getTableNumberOfPeople() {
        return tableNumberOfPeople;
    }

    public void setTableNumberOfPeople(int tableNumberOfPeople) {
        this.tableNumberOfPeople = tableNumberOfPeople;
    }

    public String getTableDishes() {
        return tableDishes;
    }

    public void setTableDishes(String tableDishes) {
        this.tableDishes = tableDishes;
    }

    public long getTableTimeIn() {
        return tableTimeIn;
    }

    public void setTableTimeIn(long tableTimeIn) {
        this.tableTimeIn = tableTimeIn;
    }

    public long getTableTimeOut() {
        return tableTimeOut;
    }

    public void setTableTimeOut(long tableTimeOut) {
        this.tableTimeOut = tableTimeOut;
    }

    public boolean isTableActive() {
        return tableActive;
    }

    public void setTableActive(boolean tableActive) {
        this.tableActive = tableActive;
    }
}
