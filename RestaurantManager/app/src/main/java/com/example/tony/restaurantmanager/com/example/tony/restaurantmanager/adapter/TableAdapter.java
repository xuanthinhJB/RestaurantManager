package com.example.tony.restaurantmanager.com.example.tony.restaurantmanager.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tony.restaurantmanager.R;
import com.example.tony.restaurantmanager.com.example.tony.restaurantmanager.models.TableModel;

import java.util.List;

/**
 * Created by phanx on 25/12/2017.
 */

public class TableAdapter extends BaseAdapter{
    private Context context;
    private int layout;
    private List<TableModel> tablesList;

    public TableAdapter(Context context, int layout, List<TableModel> tablesList) {
        this.context = context;
        this.layout = layout;
        this.tablesList = tablesList;
    }

    @Override
    public int getCount() {
        return tablesList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    private class ViewHolder {
        ImageView imgTable;
        TextView txtTableName;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;

        if (view == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout, null);
            viewHolder.imgTable = view.findViewById(R.id.imgTable);
            viewHolder.txtTableName = view.findViewById(R.id.txtTableName);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        TableModel table = tablesList.get(i);
        String t = table.getTableName();

        if (table.isTableActive()) {
            viewHolder.imgTable.setImageResource(R.drawable.icon_meal_color);
        } else {
            viewHolder.imgTable.setImageResource(R.drawable.icon_meal_grey);
        }
        viewHolder.txtTableName.setText(t);
        return view;
    }
}
