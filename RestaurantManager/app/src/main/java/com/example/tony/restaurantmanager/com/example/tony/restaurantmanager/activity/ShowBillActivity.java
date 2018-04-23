package com.example.tony.restaurantmanager.com.example.tony.restaurantmanager.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.tony.restaurantmanager.R;
import com.example.tony.restaurantmanager.com.example.tony.restaurantmanager.models.Dishes;

import java.util.ArrayList;

/**
 * Created by phanx on 05/02/2018.
 */

public class ShowBillActivity extends Activity {
    private TextView txtTablePropertiesShowBill, txtTableTimeInShowBill, txtTotalMoneyShowBill;
    private ListView lvListDishShowBill;
    private Button btnCloseBill;

    private ArrayList<Dishes> a;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_bill);

        addUI();
        addEvent();
    }

    public void addUI() {

    }

    public void addEvent() {

    }
}
