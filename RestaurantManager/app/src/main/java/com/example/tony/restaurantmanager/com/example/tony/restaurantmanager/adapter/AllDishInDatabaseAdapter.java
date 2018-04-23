package com.example.tony.restaurantmanager.com.example.tony.restaurantmanager.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.tony.restaurantmanager.R;
import com.example.tony.restaurantmanager.com.example.tony.restaurantmanager.models.DishesNameOnChosing;

import java.util.List;

/**
 * Created by phanx on 28/01/2018.
 */

public class AllDishInDatabaseAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<DishesNameOnChosing> listAllDishInDatabase;

    public AllDishInDatabaseAdapter(Context context, int layout, List<DishesNameOnChosing> listAllDishInDatabase) {
        this.context = context;
        this.layout = layout;
        this.listAllDishInDatabase = listAllDishInDatabase;
    }
    @Override
    public int getCount() {
        return listAllDishInDatabase.size();
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
        private RadioButton rdOnSelectDish;
        private TextView txtDishName;
        private EditText txtInputNumberOfOneDish;
        private Button btnIncreaseDish, btnDecreaseDish;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        final ViewHolder viewHolder;
        if (view == null) {
            viewHolder = new ViewHolder();
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(layout, null);
            viewHolder.rdOnSelectDish = view.findViewById(R.id.rdSelectDish);
            viewHolder.txtDishName = view.findViewById(R.id.txtDishNameInChosing);
            viewHolder.txtInputNumberOfOneDish = view.findViewById(R.id.txtNumberOfDishToAdd);
            viewHolder.btnIncreaseDish = view.findViewById(R.id.btnIncreaseDish);
            viewHolder.btnDecreaseDish = view.findViewById(R.id.btnDecreaseDish);
        } else viewHolder = (ViewHolder) view.getTag();

        DishesNameOnChosing dish = listAllDishInDatabase.get(position);

        viewHolder.rdOnSelectDish.setChecked(false);
        viewHolder.txtDishName.setText(dish.getDishName());
        viewHolder.txtInputNumberOfOneDish.setText("1");

        viewHolder.btnIncreaseDish.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                viewHolder.txtInputNumberOfOneDish.setText(Integer.parseInt(viewHolder.txtInputNumberOfOneDish.getText().toString()) + 1);
            }
        });

        viewHolder.btnDecreaseDish.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                viewHolder.txtInputNumberOfOneDish.setText(Integer.parseInt(viewHolder.txtInputNumberOfOneDish.getText().toString()) - 1);
            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (viewHolder.rdOnSelectDish.isChecked()) {
                    viewHolder.rdOnSelectDish.setChecked(false);
                } else viewHolder.rdOnSelectDish.setChecked(true);
            }
        });

        return view;
    }
}
