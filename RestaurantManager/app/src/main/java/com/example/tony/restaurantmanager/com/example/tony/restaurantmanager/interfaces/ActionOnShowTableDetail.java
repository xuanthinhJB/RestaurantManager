package com.example.tony.restaurantmanager.com.example.tony.restaurantmanager.interfaces;

/**
 * Created by phanx on 02/02/2018.
 */

public interface ActionOnShowTableDetail {
    void increaseOneDish(int itemPosition, int amountOfDish);
    void decreaseOneDish(int itemPosition, int amountOfDish);
    void deleteDish(int itemPosition, String dishName);
}
