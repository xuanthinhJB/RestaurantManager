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
import com.example.tony.restaurantmanager.com.example.tony.restaurantmanager.adapter.GoodsAdapter;
import com.example.tony.restaurantmanager.com.example.tony.restaurantmanager.dialog.AddGoodsDialog;
import com.example.tony.restaurantmanager.com.example.tony.restaurantmanager.helper_classes.DatabaseHelper;
import com.example.tony.restaurantmanager.com.example.tony.restaurantmanager.models.Goods;

import java.util.ArrayList;

/**
 * Created by tony on 31/12/2017.
 */

public class GoodsFragment extends ListFragment {
    private ArrayList<Goods> arrGoods;
    private GoodsAdapter goodsAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_goods, container, false);

        FloatingActionButton fabAddGoods = view.findViewById(R.id.fabAddMoreGoods);
        fabAddGoods.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AddGoodsDialog.class);
                startActivity(intent);
            }
        });
        // get goods from database
        DatabaseHelper myDatabase = new DatabaseHelper(getActivity());

        arrGoods = new ArrayList<>();
        arrGoods = (ArrayList<Goods>) myDatabase.getAllGoods();
        goodsAdapter = new GoodsAdapter(getActivity(), R.layout.goods_adapter_item, arrGoods);
        setListAdapter(goodsAdapter);
        return view;
    }

    @Override
    public void onPause() {
        super.onPause();
        SharedPreferences setFragmentTag = getActivity().getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = setFragmentTag.edit();
        editor.putString("fragment_tag", "goods");
        editor.apply();
    }
}
