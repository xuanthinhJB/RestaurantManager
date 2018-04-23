package com.example.tony.restaurantmanager.com.example.tony.restaurantmanager.dialog;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.tony.restaurantmanager.R;
import com.example.tony.restaurantmanager.com.example.tony.restaurantmanager.adapter.AllDishInDatabaseAdapter;
import com.example.tony.restaurantmanager.com.example.tony.restaurantmanager.adapter.DishAdapter;
import com.example.tony.restaurantmanager.com.example.tony.restaurantmanager.helper_classes.DatabaseHelper;
import com.example.tony.restaurantmanager.com.example.tony.restaurantmanager.models.Dishes;
import com.example.tony.restaurantmanager.com.example.tony.restaurantmanager.models.DishesNameOnChosing;

import java.util.ArrayList;

/**
 * Created by phanx on 28/01/2018.
 */

public class ShowListDishDialog extends Activity{
    private Button btnConfirm, btnCancel;
    private ListView lvDialogListDish;
    private ArrayList<Dishes> arrAllDishes;
    private ArrayList<DishesNameOnChosing> arrAllDishesName, arrListDishesToSendBack;
    private AllDishInDatabaseAdapter allDishInDatabaseAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_add_dish);

        addUI();
        addEvents();
    }

    public void addUI() {
        btnConfirm = findViewById(R.id.btnConfirmAddDishToTable);
        btnCancel = findViewById(R.id.btnCancelAddDishToTable);
        lvDialogListDish = findViewById(R.id.lvDialogListDish);

        arrAllDishes = new ArrayList<>();
        arrAllDishesName = new ArrayList<>();
        DatabaseHelper myDatabase = new DatabaseHelper(this);
        arrAllDishes = (ArrayList<Dishes>) myDatabase.getAllDishes();
        for (int i = 0; i < arrAllDishes.size(); i++) {
            DishesNameOnChosing dishesName = new DishesNameOnChosing();
            dishesName.setDishName(arrAllDishes.get(i).getDishName());
            dishesName.setDishOnChosing(false);
            arrAllDishesName.add(dishesName);
        }
        allDishInDatabaseAdapter = new AllDishInDatabaseAdapter(ShowListDishDialog.this, R.layout.dialog_add_dish_adapter_item, arrAllDishesName);
        lvDialogListDish.setAdapter(allDishInDatabaseAdapter);
    }

    public void addEvents() {
        lvDialogListDish.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (arrAllDishesName.get(position).isDishOnChosing()) {
                    arrAllDishesName.get(position).setDishOnChosing(false);
                } else arrAllDishesName.get(position).setDishOnChosing(true);
            }
        });

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < arrAllDishesName.size(); i++) {
                    if (arrAllDishesName.get(i).isDishOnChosing()) {
                        arrListDishesToSendBack.add(arrAllDishesName.get(i));
                    }
                }
                Intent returnIntent = new Intent();
                returnIntent.putExtra("add_dish_result", arrListDishesToSendBack);
                setResult(Activity.RESULT_OK, returnIntent);
                finish();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent returnIntent = new Intent();
                setResult(Activity.RESULT_CANCELED, returnIntent);
                finish();
            }
        });
    }
}
