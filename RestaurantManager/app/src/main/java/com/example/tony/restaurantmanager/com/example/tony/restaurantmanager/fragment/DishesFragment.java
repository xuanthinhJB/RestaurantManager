package com.example.tony.restaurantmanager.com.example.tony.restaurantmanager.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tony.restaurantmanager.R;
import com.example.tony.restaurantmanager.com.example.tony.restaurantmanager.adapter.DishAdapter;
import com.example.tony.restaurantmanager.com.example.tony.restaurantmanager.dialog.AddDishDialog;
import com.example.tony.restaurantmanager.com.example.tony.restaurantmanager.helper_classes.DatabaseHelper;
import com.example.tony.restaurantmanager.com.example.tony.restaurantmanager.models.Dishes;

import java.util.ArrayList;

/**
 * Created by phanx on 31/12/2017.
 */

public class DishesFragment extends ListFragment {
    private ArrayList<Dishes> arrDish;
    private DishAdapter dishAdapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dishes, container, false);

        FloatingActionButton fabAddDish = view.findViewById(R.id.fabAddMoreDish);
        fabAddDish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AddDishDialog.class);
                startActivity(intent);
            }
        });

        DatabaseHelper databaseHelper = new DatabaseHelper(getActivity());
        arrDish = new ArrayList<>();
        arrDish = (ArrayList<Dishes>) databaseHelper.getAllDishes();
        dishAdapter = new DishAdapter(getActivity(), R.layout.dish_adapter_item, arrDish);
        setListAdapter(dishAdapter);
        return view;
    }

    @Override
    public void onPause() {
        super.onPause();
        SharedPreferences setFragmentTag = getActivity().getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = setFragmentTag.edit();
        editor.putString("fragment_tag", "dish");
        editor.apply();
    }
}
