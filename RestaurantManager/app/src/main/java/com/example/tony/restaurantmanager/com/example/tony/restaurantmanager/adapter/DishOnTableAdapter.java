package com.example.tony.restaurantmanager.com.example.tony.restaurantmanager.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.tony.restaurantmanager.R;
import com.example.tony.restaurantmanager.com.example.tony.restaurantmanager.interfaces.ActionOnShowTableDetail;
import com.example.tony.restaurantmanager.com.example.tony.restaurantmanager.models.DishesOnTable;

import java.util.List;

/**
 * Created by phanx on 26/01/2018.
 */

public class DishOnTableAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<DishesOnTable> listDishesOnTable;
    private ActionOnShowTableDetail myAction;

    public DishOnTableAdapter(Context context, int layout, List<DishesOnTable> listDishesOnTable) {
        this.context = context;
        this.layout = layout;
        this.listDishesOnTable = listDishesOnTable;
    }
    @Override
    public int getCount() {
        return listDishesOnTable.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    private class ViewHolder {
        private TextView txtDishName;
        private TextView txtDishAmount;
        private TextView txtDishPrice;
        private TextView txtCashType;
        private Button btnUpDish, btnDownDish, btnDeleteDishInTable;
    }

    @SuppressLint({"SetTextI18n", "CutPasteId"})
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        final ViewHolder viewHolder;
        final int itemPositon = position;
        if (view == null) {
            viewHolder = new ViewHolder();
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(layout, null);
            viewHolder.txtDishName = view.findViewById(R.id.txtDishName2);
            viewHolder.txtDishAmount = view.findViewById(R.id.txtDishNumber2);
            viewHolder.txtDishPrice = view.findViewById(R.id.txtDishPrice2);
            viewHolder.txtCashType = view.findViewById(R.id.txtCashType);
            viewHolder.btnUpDish = view.findViewById(R.id.btnUpDish);
            viewHolder.btnDownDish = view.findViewById(R.id.btnUpDish);
            viewHolder.btnDeleteDishInTable = view.findViewById(R.id.btnDeleteDishInTable);
            view.setTag(viewHolder);
        } else viewHolder = (ViewHolder) view.getTag();

        DishesOnTable dish = listDishesOnTable.get(position);
        viewHolder.txtDishName.setText(dish.getDishName());
        viewHolder.txtDishAmount.setText(dish.getDishAmount());
        viewHolder.txtDishPrice.setText(dish.getDishBillMember() + "");

        viewHolder.btnUpDish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myAction.increaseOneDish(itemPositon, Integer.parseInt(viewHolder.txtDishAmount.getText().toString()));
            }
        });

        viewHolder.btnDownDish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myAction.decreaseOneDish(itemPositon, Integer.parseInt(viewHolder.txtDishAmount.getText().toString()));
            }
        });

        viewHolder.btnDeleteDishInTable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myAction.deleteDish(itemPositon, viewHolder.txtDishName.getText().toString());
            }
        });
        return view;
    }
}
